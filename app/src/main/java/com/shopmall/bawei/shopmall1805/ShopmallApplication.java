package com.shopmall.bawei.shopmall1805;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.UserManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.ShopUserManager;
import com.bw.net.NetModel;
import com.bw.net.TokenInterceptor;
import com.shopmall.bawei.shopmall1805.service.AotuLoginService;

public class ShopmallApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(),"daodao");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        daoSession = new DaoMaster(writableDatabase).newSession();
        GreenDaoBeanDao greenDaoBeanDao = daoSession.getGreenDaoBeanDao();
        MyGreenManager.getMyGreenManager().setGreenDaoBeanDao(greenDaoBeanDao);

        ShopUserManager.getInstance().init(this);
        NetModel.init(this);

        Intent intent = new Intent(this, AotuLoginService.class);
        startService(intent);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
