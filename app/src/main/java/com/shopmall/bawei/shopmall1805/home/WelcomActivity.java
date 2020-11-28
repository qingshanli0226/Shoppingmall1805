package com.shopmall.bawei.shopmall1805.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.activity.MainActivity;

public class WelcomActivity extends AppCompatActivity implements ViewPropertyAnimatorListener {

    private ImageView welcomImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomactivity);


        welcomImage = findViewById(R.id.welcom_image);




        ViewCompat.animate(welcomImage).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(5000);

    }

    @Override
    public void onAnimationStart(View view) {

    }

    @Override
    public void onAnimationEnd(View view) {
        startActivity(new Intent(WelcomActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onAnimationCancel(View view) {

    }
}
