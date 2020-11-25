package com.shopmall.bawei.shopmall1805;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.net.NetModule;

public class ShopmallApplication extends Application {
    public static ShopmallApplication instance;
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
    }
}
