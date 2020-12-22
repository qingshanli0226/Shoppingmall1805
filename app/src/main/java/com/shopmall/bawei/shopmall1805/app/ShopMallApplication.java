package com.shopmall.bawei.shopmall1805.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.MessageDao;
import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.framework.dao.DaoMaster;
import com.shopmall.bawei.framework.dao.DaoSession;
import com.shopmall.bawei.net.mode.NetModule;

public class ShopMallApplication extends Application {

    private Boolean isDebug = true;
    public static ShopMallApplication instance;

//    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if(isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        NetModule.init(this);
        UserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);

        MessageDao.getInstance().init(this);

//        initGreenDao();

    }

//    private void initGreenDao() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(instance,"1805shop.db");
//        SQLiteDatabase database = helper.getWritableDatabase();
//        DaoMaster daoMaster = new DaoMaster(database);
//        mDaoSession = daoMaster.newSession();
//    }
//
//    public static DaoSession getDaoSession(){
//        return mDaoSession;
//    }
}
