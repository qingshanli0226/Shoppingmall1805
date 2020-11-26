package com.shopmall.bawei.shopmall1805;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class ShopmallApplication extends Application {

    private boolean isDebugArouter = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebugArouter){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(ShopmallApplication.this);
    }
}
