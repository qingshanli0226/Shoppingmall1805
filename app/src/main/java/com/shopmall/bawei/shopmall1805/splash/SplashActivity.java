package com.shopmall.bawei.shopmall1805.splash;

import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.shopmall.bawei.shopmall1805.R;


public class SplashActivity extends BaseActivity {

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build("/main/MainActivity").navigation();
                finish();
            }
        },2000);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }
}