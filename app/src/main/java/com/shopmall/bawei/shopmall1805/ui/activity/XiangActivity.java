package com.shopmall.bawei.shopmall1805.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.net.ConfigUrl;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.application.ShopmallApplication;
import com.shopmall.bawei.shopmall1805.entity.DaoSession;
import com.shopmall.bawei.shopmall1805.entity.ShopEntity;
import com.shopmall.bawei.shopmall1805.entity.ShopEntityDao;

public class XiangActivity extends AppCompatActivity {

    private ImageView imv;
    private TextView textView;
    public static ShopEntityDao shopEntityDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);

        initView();
        Intent intent = getIntent();
        final String path = intent.getStringExtra("path");


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


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopEntityDao.insert(new ShopEntity(1,"11",path,"66"));
            }
        });

    }

    private void initView() {
        imv = (ImageView) findViewById(R.id.iv_good_info_image);
        textView = findViewById(R.id.tv_good_info_collection);
    }
}