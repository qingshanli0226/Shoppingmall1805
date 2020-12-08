package com.bawei.shopmall.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.framework.CacheManager;
import com.bawei.framework.ShopUserManager;
import com.bawei.net.NetModule;

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
        NetModule.init(this);
        ShopUserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);
    }
}
