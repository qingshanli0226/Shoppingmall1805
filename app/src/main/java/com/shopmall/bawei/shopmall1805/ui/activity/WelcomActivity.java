package com.shopmall.bawei.shopmall1805.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.widget.ImageView;

import com.shopmall.bawei.framework.example.framework.BaseActivity;
import com.shopmall.bawei.framework.example.framework.IPresenter;
import com.shopmall.bawei.framework.example.framework.IView;
import com.shopmall.bawei.framework.example.framework.service.MyService;
import com.shopmall.bawei.shopmall1805.R;

public class WelcomActivity extends BaseActivity<IPresenter, IView> implements ViewPropertyAnimatorListener {
    private ImageView ivWelcom;
    private String token;

    @Override
    protected void initpreseter() {
    }

    @Override
    protected void initdate() {

        Intent intent = new Intent();
        intent.setClass(this, MyService.class);
        startService(intent);
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
        finish();
    }

    @Override
    public void onAnimationCancel(View view) {

    }

}
