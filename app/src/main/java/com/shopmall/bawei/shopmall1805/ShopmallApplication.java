package com.shopmall.bawei.shopmall1805;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.net.manager.ShopUserManager;

public class ShopmallApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        ShopUserManager.getInstance().init(this);
    }
}
