package com.shopmall.bawei.shopmall1805;

import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;

import http.NetModule;
import mvp.CacheManager;
import mvp.view.ShopUserManager;
import mvp.view.UserService;

public class ShopmallApplication extends Application {

    public static ShopmallApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(ShopmallApplication.this);
        NetModule.init(this);
        ShopUserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);

        Intent intent = new Intent();
        intent.setClass(this, UserService.class);
        startService(intent);
    }


}
