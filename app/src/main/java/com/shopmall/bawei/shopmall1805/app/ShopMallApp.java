package com.shopmall.bawei.shopmall1805.app;


import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class  ShopMallApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this);
    }
}
