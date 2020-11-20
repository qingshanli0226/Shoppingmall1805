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
    private ImageView ivWelcom;



    @Override
    protected void initdate() {
        ViewCompat.animate(ivWelcom).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(2000);
    }

    @Override
    protected void initview() {
        //初始化布局文件
        ivWelcom = findViewById(R.id.iv_welcom);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_welcom;
    }

    /**
     * 欢迎动画显示完成进行跳转
     * @param view
     */
    @Override
    public void onAnimationStart(View view) {

    }

    @Override
    public void onAnimationEnd(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAnimationCancel(View view) {

    }
}
