package com.example.elevenmonthshoppingproject;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framwork.service.LoginService;
import com.example.framwork.BaseActivity;
@Route(path = "/WeMain/WelMainActivity")
public class WelcomeActivity extends BaseActivity {
    private ImageView welcomeImg;
    private Intent intent;


    @Override
    protected int getLayoutId() {
        return R.layout.welcome_main;
    }

    @Override
    protected void iniView() {
        intent=new Intent(this,LoginService.class);
        startService(intent);
        welcomeImg = findViewById(R.id.welcome_img);
        ViewCompat.animate(welcomeImg).setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {

            }

            @Override
            public void onAnimationEnd(View view) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

            }

            @Override
            public void onAnimationCancel(View view) {

            }
        }).scaleX(1.0f).scaleY(1.0f).setDuration(3000).start();


//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgPic, "scaleY", 1.0f);
//        scaleY.setDuration(5000);
//        scaleY.start();
//
//
//

    }

    @Override
    protected void iniData() {




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }
}
