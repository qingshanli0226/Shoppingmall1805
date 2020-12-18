package com.shopmall.bawei.shopmall1805.aplacation;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.net.NetModule;
import com.shopmall.bawei.framework.example.framework.manager.CacheManager;
import com.shopmall.bawei.framework.example.framework.manager.PayMessageManager;
import com.shopmall.bawei.framework.example.framework.manager.UserManage;
import com.squareup.leakcanary.LeakCanary;


public class ShopmallApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        // 打印日志
        ARouter.openLog();
        // 开启调试模式(如果在InstantRun(就是AndroidStudio2.0以后新增的一个可以减少很多编译时间的运行机制)模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openDebug();
        // 初始化尽可能早，推荐在Application中初始化
        ARouter.init(this);

        UserManage.getInstance().nid(this);
        CacheManager.getInstance().init(this);

        NetModule.init(this);

        if (!LeakCanary.isInAnalyzerProcess(this)){
            LeakCanary.install(this);
        }

        PayMessageManager.getInstance().init(this);

    }




}
