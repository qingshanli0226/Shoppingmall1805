package com.example.framwork.view.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.example.framwork.DaoMaster;
import com.example.framwork.DaoSession;
import com.example.framwork.GreenCarBeanDao;
import com.example.framwork.ShopCarGreen;
import com.example.framwork.ShopCarGreenDao;
import com.example.framwork.sql.MySqlOpenHelper;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.annotations.NonNull;

//消息管理类
public class MessageManager {

    private static MessageManager messageManager;
    private final String DB_NAME = "shopcar.db";
    private final String MESSAGE_SP_NAME = "shopcarCountSp";
    private final String MESSAGE_SP_COUNT = "shopcarCount";
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ShopCarGreenDao shopCarGreenDao;

    private  Handler handler=new Handler();
    //创建线程池
    private ExecutorService executorService=Executors.newCachedThreadPool();

    public static final int PAY_TYPE = 1;//类型支付类型
    public static final int ADR_TYPE = 2;

    private MessageManager(){

    }

    public static MessageManager getInstance(){
        if (messageManager==null){
            messageManager=new MessageManager();
        }
        return messageManager;
    }

    public void init(Context context){
        this.context=context;
        //初始化数据库


        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        shopCarGreenDao=daoSession.getShopCarGreenDao();

        sharedPreferences = context.getSharedPreferences(MESSAGE_SP_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void upadateMesageCount(int count) {
        editor.putInt(MESSAGE_SP_COUNT,count);
        editor.commit();
    }

    public int getMessageCount() {
        return sharedPreferences.getInt(MESSAGE_SP_COUNT,0);
    }

    //使用线程池来存储数据，不能在主线程中操作数据库
    public void addMessage(@NonNull final ShopCarGreen shopcarMessage, final IMessageListener messageListener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shopCarGreenDao.insert(shopcarMessage);
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

    public void deleteMessage(final ShopCarGreen shopCarGreen, final IMessageListener iMessageListener){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    shopCarGreenDao.delete(shopCarGreen);
                    if (getMessageCount()>0){
                        upadateMesageCount(getMessageCount()-1);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (iMessageListener!=null){
                                iMessageListener.onResult(true,null);
                            }
                        }
                    });
                }
            });
    }

    public void updatMessage(final ShopCarGreen shopCarGreen, final IMessageListener iMessageListener){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    shopCarGreenDao.update(shopCarGreen);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (iMessageListener!=null){
                                iMessageListener.onResult(true,null);
                            }
                        }
                    });
                }
            });
    }

    public void queryMessage( final IMessageListener iMessageListener){

//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                Cursor cursor = sqLiteDatabase.query("house", null, null, null, null, null, null);
//                while (cursor.moveToNext()){
//                   final String price = cursor.getString(cursor.getColumnIndex("price"));
//
//                }
////                final List<ShopCarGreen> list = shopCarGreenDao.queryBuilder().orderDesc(ShopCarGreenDao.Properties.Time).limit(50).list();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        iMessageListener.onResult(true,list);
//                    }
//                });
//
//            }
//        });
    }



    public interface IMessageListener{
        void onResult(boolean isSuccess, List<ShopCarGreen> shopcarMessageList);
    }
}
