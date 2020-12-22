package com.shopmall.bawei.shopmall1805;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.deom.CacheManager;
import com.bawei.deom.NetModule;
import com.bawei.deom.ShopUserManager;
import com.shopmall.bawei.shopmall1805.MessageManager.MessageManager;

public class ShopmallApplication extends Application {
   public static DaoSession daoSession;
    Intent intent;
    public  Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        context=this;
        ARouter.init(this);
        DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(this,"shopcar");
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        daoSession=new DaoMaster(db).newSession();

        NetModule.init(this);
        ShopUserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);
        MessageManager.getInstance().init(this);
    }
     public DaoSession getDaoSession(){
        return daoSession;
     }
}
