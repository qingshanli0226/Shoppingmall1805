package com.shopmall.bawei.shopmall1805;

import android.app.Application;
import android.content.Context;

public class ShopmallApplication extends Application {

    public Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }
}
