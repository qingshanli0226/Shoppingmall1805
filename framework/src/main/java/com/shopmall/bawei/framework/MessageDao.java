package com.shopmall.bawei.framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.shopmall.bawei.framework.dao.DaoMaster;
import com.shopmall.bawei.framework.dao.DaoSession;
import com.shopmall.bawei.framework.dao.ShopCarMessage;
import com.shopmall.bawei.framework.dao.ShopCarMessageDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageDao {

    private static MessageDao instance;
    private Context applicationContext;

    private final String DB_NAME = "shop1805.db";
    private final String MESSAGE_SP_NAME = "shopCarCountSp";
    private final String MESSAGE_SP_COUNT = "shopCarCount";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;
    private ShopCarMessageDao shopCarMessageDao;

    private Handler handler = new Handler();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static final int PAY_TYPE = 1;
    public static final int ADR_TYPE = 1;

    private MessageDao(){

    }

    public static MessageDao getInstance() {
        if(instance == null){
            instance = new MessageDao();
        }
        return instance;
    }

    public void init(Context applicationContext){
        this.applicationContext = applicationContext;
        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(applicationContext,DB_NAME);
        DaoMaster daoMaster = new DaoMaster(openHelper.getReadableDb());
        DaoSession daoSession = daoMaster.newSession();

        shopCarMessageDao = daoSession.getShopCarMessageDao();

        sharedPreferences = applicationContext.getSharedPreferences(MESSAGE_SP_NAME,Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }

    public void updateMessageCount(int count){
        spEditor.putInt(MESSAGE_SP_COUNT,count);
        spEditor.commit();
    }

    public int getMessageCount(){
        return sharedPreferences.getInt(MESSAGE_SP_COUNT,0);
    }

    public void addMessage(final ShopCarMessage shopCarMessage, final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shopCarMessageDao.insert(shopCarMessage);
                updateMessageCount(getMessageCount()+1);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(messageListener != null){
                            messageListener.onResult(true,null);
                        }
                    }
                });
            }
        });
    }

    public void deleteMessage(final ShopCarMessage shopCarMessage, final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shopCarMessageDao.delete(shopCarMessage);
                if(getMessageCount() > 0){
                    updateMessageCount(getMessageCount() - 1);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(messageListener != null){
                            messageListener.onResult(true,null);
                        }
                    }
                });
            }
        });
    }
    public void updateMessage(final ShopCarMessage shopCarMessage, final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shopCarMessageDao.update(shopCarMessage);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(messageListener != null){
                            messageListener.onResult(true,null);
                        }
                    }
                });
            }
        });
    }
    public void queryMessage (final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final List<ShopCarMessage> shopCarMessageList = shopCarMessageDao
                        .queryBuilder()
                        .orderDesc(ShopCarMessageDao.Properties.Time)
                        .limit(10)
                        .list();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        messageListener.onResult(true,shopCarMessageList);
                    }
                });
            }
        });
    }

    public interface IMessageListener{
        void onResult(boolean isSuccess, List<ShopCarMessage> shopCarMessageList);
    }
}
