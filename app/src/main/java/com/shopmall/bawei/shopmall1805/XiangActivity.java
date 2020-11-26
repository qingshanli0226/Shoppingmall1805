package com.shopmall.bawei.shopmall1805;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.net.ConfigUrl;

public class XiangActivity extends AppCompatActivity {

    private ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);

        initView();
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");

        Glide.with(this).load(ConfigUrl.BASE_IMAGE+path).into(imv);

        ARouter.getInstance().inject(this);

        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build("/duoduo/shopcar")
                        .navigation();
            }
        });
    }

    private void initView() {
        imv = (ImageView) findViewById(R.id.iv_good_info_image);
    }
}