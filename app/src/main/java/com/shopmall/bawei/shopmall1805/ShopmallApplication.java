package com.shopmall.bawei.shopmall1805;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.framework.manager.ShopUserManager;
import com.shopmall.net.RetrofitFactory;

public class ShopmallApplication extends Application {
    public static ShopmallApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        RetrofitFactory.getInstance().init(this);
        ShopUserManager.getInstance().init(this);
    }
}
