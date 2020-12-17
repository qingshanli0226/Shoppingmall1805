package com.shopmall.bawei.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.shopmall.bawei.framework.greendaobean.DaoMaster;
import com.shopmall.bawei.framework.greendaobean.DaoSession;
import com.shopmall.bawei.framework.greendaobean.MessageBean;
import com.shopmall.bawei.framework.greendaobean.MessageBeanDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreendaoManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private DaoSession daoSession;
    private MessageBeanDao messageBeanDao;
    private volatile static GreendaoManager greendaoManager=null;
    private List<ImessageChangeListener> islamicCalendars=new ArrayList<>();
    private Context context;
       public static GreendaoManager getInstance(){
             if (null==greendaoManager){
                 synchronized (GreendaoManager.class){
                     if (greendaoManager==null){
                         greendaoManager=new GreendaoManager();
                     }
                 }
             }
             return greendaoManager;
       }

       private GreendaoManager(){

       }

       public void init(Context context){
           sharedPreferences=context.getSharedPreferences("message",0);
           editor=sharedPreferences.edit();
           editor.commit();

           DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "1805A");
           DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
           daoSession=daoMaster.newSession();
           messageBeanDao= daoSession.getMessageBeanDao();
           this.context=context;


       }

       //存放消息个数
       public void setcount(int num){
           editor.putInt("count",num);
           editor.commit();
       }

       //获取sp存放的消息个数
       public int getcount(){
           int count = sharedPreferences.getInt("count", 0);
           if (count!=0){
               return count;
           }else {
               return 0;
           }

       }

       //增加
       public void insert(MessageBean messageBean){
           messageBeanDao.insert(messageBean);
           int count = sharedPreferences.getInt("count", 0);
           editor.putInt("count",count+1);
           editor.commit();

           notifymessagechange();
       }

       //删除
      public void delete(MessageBean messageBean){
            messageBeanDao.delete(messageBean);
           int count = sharedPreferences.getInt("count", 0);
           editor.putInt("count",count-1);
          editor.commit();
          notifymessagechange();
      }

      //查找
    public List<MessageBean> selectall(){
        List<MessageBean> list = messageBeanDao.queryBuilder().list();
        Collections.reverse(list);

        return list;
    }

    public interface ImessageChangeListener{
           void onupdatemessage();
    }

    public void registmessageChangeListener(ImessageChangeListener islamicCalendar){
             if (!islamicCalendars.contains(islamicCalendar)){
                  islamicCalendars.add(islamicCalendar);
             }
    }

    public void unmessageChangeListener(ImessageChangeListener islamicCalendar){
        if (islamicCalendars.contains(islamicCalendar)){
            islamicCalendars.remove(islamicCalendar);
        }
    }
    
    //刷新消息通知条目
    public void notifymessagechange(){
        for (ImessageChangeListener islamicCalendar : islamicCalendars) {
               islamicCalendar.onupdatemessage();
        }
    }

}
