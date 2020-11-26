package com.bawei.shopmall;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.bawei.framework.BaseActivity;
import com.bawei.framework.IPresenter;
import com.bawei.framework.IView;
import com.bawei.shopmall.home.MainActivity;
import com.shopmall.bawei.shopmall1805.R;

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

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
            }
        }, 3000);
    }
}
