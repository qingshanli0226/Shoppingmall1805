package com.shopmall.bawei.shopmall1805;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.server.MyServer;

public class ShopmallApplication extends Application {
   private  DaoSession daoSession;
    Intent intent;
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(this,"shopping");
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        daoSession=new DaoMaster(db).newSession();

    }
     public DaoSession getDaoSession(){
        return daoSession;
     }
}
