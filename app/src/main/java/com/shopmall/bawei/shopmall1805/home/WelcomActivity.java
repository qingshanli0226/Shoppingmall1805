package com.shopmall.bawei.shopmall1805.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomActivity extends AppCompatActivity {
     private int i=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomactivity);


        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                i++;
                if (i==4){
                    startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                    timer.cancel();
                }
            }
        },0,1000);

    }
}
