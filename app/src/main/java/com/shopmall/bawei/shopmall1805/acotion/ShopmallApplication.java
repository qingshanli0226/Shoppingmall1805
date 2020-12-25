package com.shopmall.bawei.shopmall1805.acotion;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.manager.GreendaoManager;
import com.shopmall.bawei.framework.manager.NetConnectManager;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bawei.framework.manager.ShopUserManager;


public class ShopmallApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        // 打印日志
        ARouter.openLog();
        // 开启调试模式(如果在InstantRun(就是AndroidStudio2.0以后新增的一个可以减少很多编译时间的运行机制)模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openDebug();
        // 初始化尽可能早，推荐在Application中初始化
        ARouter.init(this);

        HttpsFactory.getHttpsFactory().init(this);
        ShopUserManager.getInstance().init(this);
        GreendaoManager.getInstance().init(this);
        NetConnectManager.getInstance().init(this);

    }
}
