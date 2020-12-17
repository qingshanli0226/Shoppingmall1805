package com.shopmall.bawei.shopmall1805.MessageManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.bawei.deom.CacheManager;
import com.shopmall.bawei.shopmall1805.DaoSession;
import com.shopmall.bawei.shopmall1805.ShangTitle;
import com.shopmall.bawei.shopmall1805.ShangTitleDao;
import com.shopmall.bawei.shopmall1805.ShopmallApplication;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.annotations.NonNull;

//消息管理类
public class MessageManager {
    private static MessageManager instance;
    private Context applicationContext;
    private DaoSession daoSession=((ShopmallApplication.daoSession));
      private ShangTitleDao shangTitleDao = daoSession.getShangTitleDao();
    private ExecutorService executorService= Executors.newCachedThreadPool();
    public static final int PAY_TYPE = 1;//类型支付类型

    Handler handler=new Handler();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor messageEditor;
    public MessageManager(){

    }
    public static MessageManager getInstance(){
        if (instance==null){
            instance=new MessageManager();
        }
        return instance;
    }
    public void init(Context applicationContext){
          sharedPreferences = applicationContext.getSharedPreferences("count",Context.MODE_PRIVATE);
         messageEditor=sharedPreferences.edit();

    }
    public void upadateMesageCount(int count) {
        messageEditor.putInt("count",count);
        messageEditor.commit();
    }
    public int getMessageCount() {
        return sharedPreferences.getInt("count",0);
    }
    //添加
    public void addMessage(@NonNull final ShangTitle shangTitle, final IMessageListener messageListener ){
           executorService.execute(new Runnable() {
               @Override
               public void run() {
                   shangTitleDao.insert(shangTitle);
                   upadateMesageCount(getMessageCount()+1);
                   handler.post(new Runnable() {
                       @Override
                       public void run() {//在主线程回调接口可以直接刷新UI
                           if(messageListener!=null){
                               messageListener.onResult(true,null);
                           }
                       }
                   });
               }
           });
    }
    public void querMessage(@NonNull final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final List<ShangTitle> shangTitles=shangTitleDao.queryBuilder().orderDesc().limit(50).list();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        messageListener.onResult(true,shangTitles);
                    }
                });
            }
        });
    }
    public void updateMessage(@NonNull final ShangTitle shopcarMessage, final IMessageListener messageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {

               // shangTitleDao.update(shopcarMessage);
                ShangTitle unique = shangTitleDao.queryBuilder().where(ShangTitleDao.Properties.Time.eq(shopcarMessage.getTime())).unique();

                if (unique!=null){
                    if (unique.getIsRead()){
                        Log.e("查询",""+unique.getIsRead()+"");
                    }else {
                        unique.setIsRead(true);
                        shangTitleDao.update(unique);
                    }

                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (messageListener!=null){
                            messageListener.onResult(true,null);
                        }
                    }
                });

            }
        });
    }
    public void deleteMessage(@NonNull final ShangTitle shopcarMessage, final IMessageListener messageListener) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                shangTitleDao.delete(shopcarMessage);
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
    public   interface IMessageListener{
        void onResult(boolean isSuccess, List<ShangTitle> shangTitles);
    }
}
