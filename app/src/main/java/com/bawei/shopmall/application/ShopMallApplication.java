package com.bawei.shopmall.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.framework.ShopUserManager;

public class ShopMallApplication extends Application {

    private boolean isDebugARouter = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebugARouter) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        ShopUserManager.getInstance().init(this);
    }
}
