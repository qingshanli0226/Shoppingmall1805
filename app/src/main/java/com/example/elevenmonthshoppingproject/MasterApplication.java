package com.example.elevenmonthshoppingproject;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framwork.CacheManager;
import com.example.framwork.ConnectManager;
import com.example.framwork.DaoMaster;
import com.example.framwork.DaoSession;
import com.example.framwork.ShopUserManager;
import com.example.framwork.view.manager.MessageManager;
import com.example.net.NetModule;


import org.greenrobot.greendao.database.Database;

public class MasterApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

            ARouter.openLog();
            ARouter.openDebug();
        ConnectManager.getInstance().init(this);
        MessageManager.getInstance().init(this);
        ShopUserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);
        NetModule.init(this);
        ARouter.init(this);



    }


}
