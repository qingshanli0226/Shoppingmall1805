package com.shopmall.bawei.shopmall1805;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

import com.shopmall.bawei.framework.SimpleBaseActivity;
import com.shopmall.bawei.shopmall1805.home.MainActivity;

public class WelcomeActivity extends SimpleBaseActivity{
    private ImageView imgWel;


    @Override
    protected int layoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        imgWel = (ImageView) findViewById(R.id.img_wel);
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    }
                });
            }
        }).start();
    }
}
