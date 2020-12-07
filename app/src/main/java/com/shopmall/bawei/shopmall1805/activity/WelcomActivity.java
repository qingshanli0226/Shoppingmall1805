package com.shopmall.bawei.shopmall1805.activity;

import android.content.Intent;
import android.os.Handler;

import com.bw.framework.BaseActivity;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.shopmall.bawei.shopmall1805.R;


public class WelcomActivity extends BaseActivity<IPresenter, IView> {


    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }


    @Override
    protected void initView() {
        super.initView();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomActivity.this, MainActivity.class));
            }
        },5000);
    }
}
