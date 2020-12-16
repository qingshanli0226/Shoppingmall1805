package com.bawei.framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.bawei.framework.greendao.MessageBean;
import com.bawei.framework.greendao.dao.DaoMaster;
import com.bawei.framework.greendao.dao.DaoSession;
import com.bawei.framework.greendao.dao.MessageBeanDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageManager {

    private String DB_NAME = "message.db";
    private final String MESSAGE_SP_COUNT = "messageCount";

    private static MessageManager instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor messageEditor;

    private MessageBeanDao messageBeanDao;

    private Handler handler = new Handler();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static final int PAY_TYPE = 1;

    private MessageManager() {

    }

    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
        }
        return instance;
    }

    public void init(Context applicationContext) {
        sharedPreferences = applicationContext.getSharedPreferences("count", Context.MODE_PRIVATE);
        messageEditor = sharedPreferences.edit();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(applicationContext, DB_NAME);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        messageBeanDao = daoSession.getMessageBeanDao();
    }

    public void addMessage(final MessageBean messageBean, final IMessageListener messageListener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                messageBeanDao.insert(messageBean);
                updateMessageCount(getMessageCount() + 1);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (messageListener != null) {
                            messageListener.onResult(true, null);
                        }
                    }
                });
            }
        });
    }


    private int getMessageCount() {
        return sharedPreferences.getInt(MESSAGE_SP_COUNT, 0);
    }

    public void updateMessageCount(int count) {
        messageEditor.putInt(MESSAGE_SP_COUNT, count);
        messageEditor.commit();
    }

    public interface IMessageListener {
        void onResult(boolean isSuccess, List<MessageManager> messageManagerList);
    }
}
