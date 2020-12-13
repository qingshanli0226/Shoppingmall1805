package com.example.framwork;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;


import org.greenrobot.greendao.database.Database;

public class MasterApplication extends Application {

    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();

            ARouter.openLog();
            ARouter.openDebug();

        ARouter.init(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "hot-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
