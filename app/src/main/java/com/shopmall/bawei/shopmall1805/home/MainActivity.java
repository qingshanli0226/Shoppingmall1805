package com.shopmall.bawei.shopmall1805.home;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.shopmall.bawei.shopmall1805.R;

import mvp.ToastUtil;

public class
MainActivity extends AppCompatActivity {

    private Button one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        one = (Button) findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.toast(MainActivity.this,"1111");
            }
        });
    }
}
