package com.shopmall.bawei.shopmall1805.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.framework.ShopUserManager;
import com.shopmall.bawei.shopmall1805.framework.service.CacheManager;
import com.shopmall.bawei.shopmall1805.net.NetModule;

public class ShopmallApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Arouter
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        NetModule.init(this);
        ShopUserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);
    }
}
