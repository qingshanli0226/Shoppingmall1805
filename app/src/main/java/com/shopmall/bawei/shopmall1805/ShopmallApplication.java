package com.shopmall.bawei.shopmall1805;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;

public class ShopmallApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(),"daodao");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        daoSession = new DaoMaster(writableDatabase).newSession();


    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
