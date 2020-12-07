package com.shopmall.bawei.shopmall1805.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.net.mode.NetModule;

public class ShopMallApplication extends Application {

    private Boolean isDebug = true;
    public static ShopMallApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if(isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        NetModule.init(this);
        UserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);
    }
}
