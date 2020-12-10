package com.shopmall.bawei.shopmall1805;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import mvp.CacheManager;
import mvp.view.ShopUserManager;

public class ShopmallApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        ShopUserManager.getInstance().init(this);
    }


}
