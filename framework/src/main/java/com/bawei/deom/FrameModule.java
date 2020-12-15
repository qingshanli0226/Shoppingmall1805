package com.bawei.deom;

import android.content.Context;

public class FrameModule {
    public static Context context;
    public  static  void  init(Context applicationContext){
        context=applicationContext;
    }
}
