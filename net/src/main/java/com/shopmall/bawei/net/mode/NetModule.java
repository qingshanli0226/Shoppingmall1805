package com.shopmall.bawei.net.mode;

import android.app.Application;
import android.content.Context;

public class NetModule {

    public static Context context;

    public static void init(Context applicationContext) {
        context = applicationContext;
    }
}
