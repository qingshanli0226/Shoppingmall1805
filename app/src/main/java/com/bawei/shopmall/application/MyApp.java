package com.bawei.shopmall.application;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
