package com.shopmall.bawei.shopmall1805.application;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.LoginService;
import com.example.framework.ShopUsermange;
import com.shopmall.bawei.shopmall1805.entity.DaoMaster;
import com.shopmall.bawei.shopmall1805.entity.DaoSession;

public class ShopmallApplication extends Application {

    private boolean isDebugArouter = true;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebugArouter) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.init(this);
        }
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "shopcar.db");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        daoSession = new DaoMaster(writableDatabase).newSession();
        ShopUsermange
                .getInstance()
                .init(this);

        Intent intent = new Intent();
        intent.setClass(this, LoginService.class);
        startService(intent);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
