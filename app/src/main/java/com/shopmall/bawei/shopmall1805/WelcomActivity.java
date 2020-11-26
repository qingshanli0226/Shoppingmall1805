package com.shopmall.bawei.shopmall1805;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.framework.BaseActivity;
import com.shopmall.bawei.shopmall1805.home.MainActivity;

public class WelcomActivity extends BaseActivity implements ViewPropertyAnimatorListener {

    private ImageView imageView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wecome;
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.imv);
    }

    @Override
    protected void initData() {
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(2000);
    }


    @Override
    public void onAnimationStart(View view) {

    }

    @Override
    public void onAnimationEnd(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onAnimationCancel(View view) {

    }
}
