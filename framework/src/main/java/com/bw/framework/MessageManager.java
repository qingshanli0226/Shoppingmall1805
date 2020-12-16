package com.bw.framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.bw.framework.dao.DaoMaster;
import com.bw.framework.dao.DaoSession;
import com.bw.framework.dao.ShopcarMessage;
import com.bw.framework.dao.ShopcarMessageDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.annotations.NonNull;

public class MessageManager {

    private static MessageManager instance;
    private Context applicationContext;
    private final String DB_NAME = "shopcar.db";
    private final String MESSAGE_SP_NAME = "shopcarCountSp";
    private final String MESSAGE_SP_COUNT = "shopcarCount";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor messageEditor;
    private ShopcarMessageDao shopcarMessageDao;
    private Handler handler = new Handler();

    //创建一个全局的线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static final int PAY_TYPE = 1;//类型支付类型
    public static final int ADR_TYPE = 2;

    private MessageManager() {
    }

    public static MessageManager getInstance(){
        if (instance == null){
            instance = new MessageManager();
        }
        return instance;
    }

    public void init(Context applicationContext){
        this.applicationContext = applicationContext;

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(applicationContext, DB_NAME);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();

        shopcarMessageDao = daoSession.getShopcarMessageDao();
        sharedPreferences = applicationContext.getSharedPreferences(MESSAGE_SP_NAME,Context.MODE_PRIVATE);
        messageEditor = sharedPreferences.edit();

    }

    public void updateMessageCount(int count){
        messageEditor.putInt(MESSAGE_SP_COUNT,count);
        messageEditor.commit();
    }

    public int getMessageCount(){
        return sharedPreferences.getInt(MESSAGE_SP_COUNT,0);
    }

    //添加消息的方法
    public void addMessage(final ShopcarMessage shopcarMessage, final IMessageListener listener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("---", "run: addMessage："+shopcarMessage.getBody());
                shopcarMessageDao.insert(shopcarMessage);
                updateMessageCount(getMessageCount()+1);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null){
                            listener.onResult(true,null);
                        }
                    }
                });
            }
        });
    }

    //删除消息的方法
    public void deleteMessage(@NonNull final ShopcarMessage shopcarMessage, final IMessageListener listener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shopcarMessageDao.delete(shopcarMessage);
                updateMessageCount(getMessageCount() - 1);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null){
                            listener.onResult(true,null);
                        }
                    }
                });
            }
        });
    }

    //修改消息的方法
    public void updateMessage(@NonNull final ShopcarMessage shopcarMessage, final IMessageListener listener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shopcarMessageDao.update(shopcarMessage);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null){
                            listener.onResult(true,null);
                        }
                    }
                });
            }
        });
    }

    //查询数据库中的消息
    public void queryMessage(@NonNull final IMessageListener listener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final List<ShopcarMessage> shopcarMessageList = shopcarMessageDao.queryBuilder()
                        .orderDesc(ShopcarMessageDao.Properties.Time).limit(50).list();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null){
                            listener.onResult(true,shopcarMessageList);
                        }
                    }
                });
            }
        });
    }



    public interface IMessageListener {
        void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList);
    }
}
