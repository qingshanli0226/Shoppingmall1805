package com.shopmall.bawei.shopmall1805.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.activity.home.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public  class WelcomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomActivity.this, MainActivity.class);
                startActivity(intent);
                WelcomActivity.this.finish();

            }
        },3000);
    }

}



