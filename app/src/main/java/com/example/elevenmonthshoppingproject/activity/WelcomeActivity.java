package com.example.elevenmonthshoppingproject.activity;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.elevenmonthshoppingproject.R;
import com.example.net.BaseActivity;

public class WelcomeActivity extends BaseActivity {
    private ImageView imgPic;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void iniView() {
        imgPic = findViewById(R.id.img_pic);

        ViewCompat.animate(imgPic).setListener(new ViewPropertyAnimatorListener() {
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



}
