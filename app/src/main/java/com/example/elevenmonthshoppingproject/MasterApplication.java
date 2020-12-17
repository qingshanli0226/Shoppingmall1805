package com.example.elevenmonthshoppingproject;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framwork.CacheManager;
import com.example.framwork.DaoMaster;
import com.example.framwork.DaoSession;
import com.example.framwork.ShopUserManager;
import com.example.net.NetModule;


import org.greenrobot.greendao.database.Database;

public class MasterApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

            ARouter.openLog();
            ARouter.openDebug();


        ShopUserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);
        NetModule.init(this);
        ARouter.init(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "hot-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
