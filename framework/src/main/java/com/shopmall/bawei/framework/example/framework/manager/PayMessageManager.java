package com.shopmall.bawei.framework.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.shopmall.bawei.framework.example.framework.dao.DaoMaster;
import com.shopmall.bawei.framework.example.framework.dao.DaoSession;
import com.shopmall.bawei.framework.example.framework.dao.PayMessage;
import com.shopmall.bawei.framework.example.framework.dao.PayMessageDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PayMessageManager {

    private static PayMessageManager instance;
    private Context context;
    private final String DB_NAME = "shopcar.db";
    private final String MESSAGE_SP_NAME = "shopcarCountSp";
    private final String MESSAGE_SP_COUNT = "shopcarCount";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor messageEditor;
    private PayMessageDao payMessageDao;
    private Handler handler = new Handler();
    //创建一个全局的线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static final int PAY_TYPE = 1;//类型支付类型
    public static final int ADR_TYPE = 2;

    private PayMessageManager(){

    }

    public static PayMessageManager getInstance() {
        if (instance == null){
            instance = new PayMessageManager();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
        //初始化数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDb());
        DaoSession daoSession = daoMaster.newSession();
        payMessageDao = daoSession.getPayMessageDao();

        sharedPreferences = context.getSharedPreferences(MESSAGE_SP_NAME, Context.MODE_PRIVATE);
        messageEditor = sharedPreferences.edit();

    }

    public void updataMessageCount(int count){
        messageEditor.putInt(MESSAGE_SP_COUNT,count);
        messageEditor.commit();
    }

    public int getMessageCount() {
        return sharedPreferences.getInt(MESSAGE_SP_COUNT,0);
    }

    //使用线程池来存储数据，不能在主线程中操作数据库
    public void addMessage(@NonNull final PayMessage payMessage,final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                payMessageDao.insert(payMessage);
                updataMessageCount(getMessageCount()+1);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (messageListener != null){
                            messageListener.onResult(true,null);
                        }
                    }
                });
            }
        });
    }

    public void deleteMessage(@NonNull final PayMessage payMessage, final IMessageListener messageListener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                payMessageDao.delete(payMessage);
                if (getMessageCount()>0){
                    updataMessageCount(getMessageCount() - 1);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (messageListener != null){
                            messageListener.onResult(true,null);
                        }
                    }

                });
            }
        });
    }

    public void updateMessage(@NonNull final PayMessage payMessage, final IMessageListener messageListener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                payMessageDao.update(payMessage);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (messageListener != null){
                            messageListener.onResult(true,null);
                        }
                    }
                });
            }
        });
    }

    public void queryMessage(@NonNull final IMessageListener messageListener){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final List<PayMessage> list = payMessageDao
                        .queryBuilder()
                        .orderDesc(PayMessageDao.Properties.Time)
                        .limit(50)
                        .list();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        messageListener.onResult(true,list);
                    }
                });
            }
        });
    }

    public interface IMessageListener{
        void onResult(boolean isSuccess, List<PayMessage> payMessageList);
    }
}
