package com.shopmall.bawei.shopmall1805.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class ShopMallApplication extends Application {

    private Boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if(isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
