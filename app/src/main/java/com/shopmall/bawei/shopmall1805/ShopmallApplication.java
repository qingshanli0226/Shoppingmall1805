package com.shopmall.bawei.shopmall1805;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;

import net.RxjavaRetortUlis;

import framework.CacheManagerc;
import framework.MessageMangerUlis;
import framework.ShopUserManager;


public class ShopmallApplication extends Application {

    public Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        CacheManagerc.getInstance().init(this);
        ShopUserManager.getInstance().init(this);
        MessageMangerUlis.getInstance().init(this);
        RxjavaRetortUlis.getInstance().init(this);

        if (!LeakCanary.isInAnalyzerProcess(this)){
            LeakCanary.install(this);
        }

    }
}
