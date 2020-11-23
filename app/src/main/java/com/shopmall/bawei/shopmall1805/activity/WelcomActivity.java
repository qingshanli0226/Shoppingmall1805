package com.shopmall.bawei.shopmall1805.activity;

import android.content.Intent;
import android.os.Handler;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.base.BaseActivity;
import com.shopmall.bawei.shopmall1805.base.IPresenter;
import com.shopmall.bawei.shopmall1805.base.IView;

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
