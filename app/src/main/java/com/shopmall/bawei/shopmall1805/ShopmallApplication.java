package com.shopmall.bawei.shopmall1805;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.CacheManager;
import com.bw.framework.MessageManager;
import com.bw.framework.NetContentManager;
import com.bw.framework.OrderManager;
import com.bw.framework.ShopUserManager;
import com.bw.framework.service.AutoLoginService;
import com.bw.net.NetModel;
import com.squareup.leakcanary.LeakCanary;

public class ShopmallApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        ShopUserManager.getInstance().init(this);
        NetModel.init(this);

        CacheManager.getInstance().init(this);
        OrderManager.getInstance().init(this);
        NetContentManager.getInstance().init(this);
        MessageManager.getInstance().init(this);
        if (!LeakCanary.isInAnalyzerProcess(this)){
            LeakCanary.install(this);
        }


    }


}
