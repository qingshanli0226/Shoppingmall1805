package com.shopmall.bawei.shopmall1805.app.ui.activity;

import android.content.Intent;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.framework.ui.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private Timer timer;

    @Override
    protected int bandLayout() {
        return R.layout.activity_welcome;
    }
    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
                timer.cancel();
            }
        },2000);
    }
    @Override
    protected void initEvent() {

    }
    @Override
    protected void createPresenter() {

    }

}