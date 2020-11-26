package com.shopmall.bawei.shopmall1805.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class ShopMallApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        ARouter.openDebug();
        ARouter.openLog();
    }
}
