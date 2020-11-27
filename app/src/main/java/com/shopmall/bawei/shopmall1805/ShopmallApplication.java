package com.shopmall.bawei.shopmall1805;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;


public class ShopmallApplication extends Application {

    public Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

    }
}
