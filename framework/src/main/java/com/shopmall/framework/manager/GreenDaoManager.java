package com.shopmall.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.shopmall.framework.daobean.DaoMaster;
import com.shopmall.framework.daobean.DaoSession;
import com.shopmall.framework.daobean.MessageBean;
import com.shopmall.framework.daobean.MessageBeanDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreenDaoManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private DaoSession daoSession;
    private MessageBeanDao messageBeanDao;
    private volatile static GreenDaoManager greenDaoManager = null;
    private List<IMessageChangeListener> islamicCalendars = new ArrayList<>();
    private Context context;

    public static GreenDaoManager getInstance(){
        if (null == greenDaoManager){
            synchronized (GreenDaoManager.class){
                if (greenDaoManager == null){
                    greenDaoManager = new GreenDaoManager();
                }
            }
        }
        return greenDaoManager;
    }

    private GreenDaoManager(){

    }

    private void init(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("message",0);
        editor = sharedPreferences.edit();
        editor.commit();

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "1805");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        messageBeanDao = daoSession.getMessageBeanDao();

    }

    public void setCount(int num){
        editor.putInt("count",num);
        editor.commit();
    }

    public int getCount(){
        int count = sharedPreferences.getInt("count",0);
        if (count != 0){
            return count;
        }else {
            return 0;
        }
    }

    public void insert(MessageBean messageBean){
        messageBeanDao.insert(messageBean);
        int count = sharedPreferences.getInt("count",0);
        editor.putInt("count",count+1);
        editor.commit();

        notifyMessageChange();
    }

    public void delete(MessageBean messageBean){
        messageBeanDao.delete(messageBean);
        int count = sharedPreferences.getInt("count",0);
        editor.putInt("count",count-1);
        editor.commit();

        notifyMessageChange();
    }

    public List<MessageBean> selectAll(){
        List<MessageBean> list = messageBeanDao.queryBuilder().list();
        Collections.reverse(list);

        return list;
    }

    public void notifyMessageChange(){
        for (IMessageChangeListener listener:islamicCalendars){
            listener.onUpdateMessage();
        }
    }

    public void unMessageChangeListener(IMessageChangeListener listener){
        if (islamicCalendars.contains(listener)){
            islamicCalendars.remove(listener);
        }
    }

    public void MessageChangeListener(IMessageChangeListener listener){
        if (!islamicCalendars.contains(listener)){
            islamicCalendars.add(listener);
        }
    }

    public interface IMessageChangeListener{
        void onUpdateMessage();
    }
}
