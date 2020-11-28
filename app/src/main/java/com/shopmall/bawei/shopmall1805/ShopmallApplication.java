package com.shopmall.bawei.shopmall1805;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class ShopmallApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

    }
}
