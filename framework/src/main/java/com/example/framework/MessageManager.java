package com.example.framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

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
    private  List<ShopcarMessage> shopcarMessageList;
    //创建一个全局的线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static final int PAY_TYPE = 1;//类型支付类型
    public static final int ADR_TYPE = 2;

    private MessageManager(){

    }

    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
        }
        return instance;
    }

    public void init(Context applicationContext) {
        this.applicationContext = applicationContext;
        //初始化数据库
        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(applicationContext,DB_NAME);
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        shopcarMessageDao = daoSession.getShopcarMessageDao();

        sharedPreferences = applicationContext.getSharedPreferences(MESSAGE_SP_NAME,Context.MODE_PRIVATE);
        messageEditor = sharedPreferences.edit();
    }

    public void upadateMesageCount(int count) {
        messageEditor.putInt(MESSAGE_SP_COUNT,count);
        messageEditor.commit();
    }

    public int getMessageCount() {
        return sharedPreferences.getInt(MESSAGE_SP_COUNT,0);
    }


    //使用线程池来存储数据，不能在主线程中操作数据库
    public void addMessage(@NonNull final ShopcarMessage shopcarMessage, final IMessageListener messageListener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
//                shopcarMessageDao.insert(shopcarMessage);

                upadateMesageCount(getMessageCount()+1);
                handler.post(new Runnable() {//在主线程里回调接口，这样UI收到回调时，可以直接刷新UI
                    @Override
                    public void run() {
                        if (messageListener!=null) {
                            messageListener.onResult(true, null);
                        }
                    }
                });
            }
        });
    }
    public void deleteMessage(@NonNull final ShopcarMessage shopcarMessage, final IMessageListener messageListener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shopcarMessageDao.delete(shopcarMessage);
                if (getMessageCount()>0) {
                    upadateMesageCount(getMessageCount() - 1);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (messageListener!=null) {
                            messageListener.onResult(true, null);
                        }
                    }
                });
            }
        });
    }
    public void updateMessage(@NonNull final ShopcarMessage shopcarMessage, final IMessageListener messageListener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shopcarMessageDao.update(shopcarMessage);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (messageListener!=null) {
                            messageListener.onResult(true, null);
                        }
                    }
                });
            }
        });
    }
    public void queryMessage(@NonNull final IMessageListener messageListener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
//                shopcarMessageList = shopcarMessageDao.queryBuilder().list();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        messageListener.onResult(true, shopcarMessageList);
                    }
                });
            }
        });
    }

    public interface IMessageListener{
        void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList);
    }

}
