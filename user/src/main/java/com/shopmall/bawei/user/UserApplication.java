package com.shopmall.bawei.user;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class UserApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        ARouter.openDebug();
        ARouter.openLog();
    }
}
