package com.shopmall.bawei.shopmall1805;

import android.content.Intent;
import android.widget.ImageView;

import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.IPresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.shopmall1805.home.MainActivity;

public class WelcomeActivity extends BaseActivity<IPresenter, IView> {
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
    protected void initPresenter() {
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

    @Override
    protected void onRightClick() {

    }
}
