package com.shopmall.bawei.shopmall1805;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bawei.deom.BaseActivity;
import com.shopmall.bawei.shopmall1805.home.MainActivity;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public  class WelcomActivity extends BaseActivity {


    @Override
    protected void inData() {
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

    @Override
    protected void intView() {

    }

    @Override
    protected int getlayouview() {
        return R.layout.welcom;
    }
}
