package com.shopmall.bawei.shopmall1805;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

import com.shopmall.bawei.shopmall1805.home.MainActivity;

public class WelcomActivity extends AppCompatActivity implements ViewPropertyAnimatorListener {

    private ImageView im;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcomactivity);



        initView();
    }

    private void initView() {
        im = (ImageView) findViewById(R.id.im);

        ViewCompat.animate(im).scaleX(1.0f).scaleY(1.0f).setDuration(2000)
                .setListener(this);


    }


    @Override
    public void onAnimationStart(View view) {

    }

    @Override
    public void onAnimationEnd(View view) {
        finish();
        startActivity(new Intent(WelcomActivity.this, MainActivity.class));

    }

    @Override
    public void onAnimationCancel(View view) {

    }
}
