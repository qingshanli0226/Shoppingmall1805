package com.shopmall.bawei.shopmall1805;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.framework.view.manager.MessageManager;
import com.shopmall.bawei.net.NetModule;
import com.shopmall.bawei.net.mode.HomeBean;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import okhttp3.Cache;

public class ShopmallApplication extends Application {
    public static ShopmallApplication instance;
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        //初始化Arouter
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(ShopmallApplication.this);

        NetModule.init(this);
        ShopUserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);
        MessageManager.getInstance().init(this);

        //使用LeakCannary来检查Activity会不会出现内存泄漏问题
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            refWatcher = LeakCanary.install(this);
        }

    }
}
