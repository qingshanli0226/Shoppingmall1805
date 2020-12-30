package com.bawei.shopmall.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.framework.CacheManager;
import com.bawei.framework.MessageManager;
import com.bawei.framework.NetConnectManager;
import com.bawei.framework.ShopUserManager;
import com.bawei.framework.greendao.dao.DaoSession;
import com.bawei.net.NetModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class ShopMallApplication extends Application {

    private DaoSession daoSession;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();


        ARouter.init(this);
        NetModule.init(this);

        ShopUserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);
        MessageManager.getInstance().init(this);

        if (!LeakCanary.isInAnalyzerProcess(this)) {
            refWatcher = LeakCanary.install(this);
        }

        NetConnectManager.getInstance().init(this);
    }
}
