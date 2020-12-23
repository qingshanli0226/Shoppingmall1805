package com.example.framework.view.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.example.framework.dao.DaoMaster;
import com.example.framework.dao.DaoSession;
import com.example.framework.dao.ShopcarMessage;
import com.example.framework.dao.ShopcarMessageDao;
import com.shopmall.bawei.framework.R;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.annotations.NonNull;

public class MessageManager {
    public static final int PAY_TYPE = 1;
    public static final int ADR_TYPE = 2;
    private static MessageManager instance;
    private String DB_NAME = "shopcar.db";
    private String MESSAGE_NAME_SP="shopcarCountsp";
    private String MESSAGE_NAME="shopcarCount";
    private ShopcarMessageDao shopcarMessageDao;
    SharedPreferences.Editor shopCarEditor;
    private SharedPreferences sharedPreferences;
    private Context context;
    //创建一个全局的线程池
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler();
    private MessageManager(){

    }
    public static MessageManager getInstance(){
        if (instance == null){
            instance = new MessageManager();
        }
        return instance;
    }
    public void init(Context context){
        this.context = context;
        //初始化数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        shopcarMessageDao = daoSession.getShopcarMessageDao();
        //初始化sp储存
        sharedPreferences = context.getSharedPreferences(MESSAGE_NAME_SP,Context.MODE_PRIVATE);
        shopCarEditor = sharedPreferences.edit();
    }

    //修改消息的数量
    public void updateMessageCount(int count){
        shopCarEditor.putInt(MESSAGE_NAME,count);
        shopCarEditor.commit();
    }
    //获取消息的数量
    public int getMessageCount(){
        int anInt = sharedPreferences.getInt(MESSAGE_NAME, 0);
        return anInt;
    }
    //使用线程池来储存消息,不能在主线程中操作数据
    public void addMessge(@NonNull final ShopcarMessage  shopcarMessage, final IMessageListenter iMessageListenter){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shopcarMessageDao.insert(shopcarMessage);
                updateMessageCount(getMessageCount()+1);
                //在主线程里面接口回掉，这样ui回调时，可以直接刷新Ui
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (iMessageListenter!=null){
                            iMessageListenter.onresult(true,null);
                        }
                    }
                });
            }
        });

    }
    //删除消息
    public void deleteMessage(@NonNull ShopcarMessage shopcarMessage, final IMessageListenter iMessageListenter){
        shopcarMessageDao.delete(shopcarMessage);
        if (getMessageCount()>0){
            updateMessageCount(getMessageCount()-1);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (iMessageListenter!=null){
                    iMessageListenter.onresult(true,null);
                }
            }
        });
    }
    //修改消息
    public void updateMessage(@NonNull ShopcarMessage shopcarMessage, final IMessageListenter iMessageListenter){
        ShopcarMessage unique = shopcarMessageDao.queryBuilder().where(ShopcarMessageDao.Properties.Time.eq(shopcarMessage.getTime())).unique();
        if (unique!=null){
            if (unique.getIsRead()){
                Log.e("###",""+unique.getIsRead());
            }else {
                unique.setIsRead(true);
                shopcarMessageDao.update(unique);
            }
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (iMessageListenter!=null){
                    iMessageListenter.onresult(true,null);
                }
            }
        });
    }
    //查询消息
    public void quereMessage(final IMessageListenter iMessageListenter){
        final List<ShopcarMessage> list = shopcarMessageDao.queryBuilder().orderDesc(ShopcarMessageDao.Properties.Time).limit(50).list();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (iMessageListenter!=null){
                    iMessageListenter.onresult(true,list);
                }
            }
        });
    }
    //查询信息
    public List<ShopcarMessage> queresmessage(){
        List<ShopcarMessage> list = shopcarMessageDao.queryBuilder().list();
        return list;
    }
    //使用接口回调来刷新Ui
    public interface IMessageListenter{
        void onresult(boolean issucess, List<ShopcarMessage> shopcarMessages);
    }
}
