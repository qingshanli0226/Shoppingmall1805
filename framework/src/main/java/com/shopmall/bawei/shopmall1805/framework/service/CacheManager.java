package com.shopmall.bawei.shopmall1805.framework.service;

import android.content.Context;
import android.content.Intent;

public class CacheManager {
    private Context context;
    public CacheManager() {}
    private static CacheManager instance;
    public static CacheManager getInstance() {
        if(instance == null){
            instance = new CacheManager();
        }
        return instance;
    }
    public void init(Context context) {
        this.context = context;
        initService();
    }
    private void initService() {
        Intent intent = new Intent(context,ShopmallService.class);
        context.startService(intent);//通过start启动service
    }
}
