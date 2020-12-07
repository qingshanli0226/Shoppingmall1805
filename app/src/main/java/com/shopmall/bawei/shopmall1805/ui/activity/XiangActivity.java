package com.shopmall.bawei.shopmall1805.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.framework.MySql;
import com.example.net.ConfigUrl;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.entity.ShopEntityDao;

import java.util.zip.Inflater;

public class XiangActivity extends AppCompatActivity {

    private ImageView imv;
    private TextView textView;
    public static ShopEntityDao shopEntityDao;
    private MySql mySql;
    private SQLiteDatabase sqLiteDatabase;
    private TextView tv_shopcar;
    private Button button;
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
        mySql = new MySql(this);
         sqLiteDatabase = mySql.getWritableDatabase();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("Name","多多");
                values.put("Path",path);
                values.put("Money","200");
                sqLiteDatabase.insert("shopcar",null,values);
            }
        });

        tv_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build("/duo/shopcar")
                        .navigation();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow popupWindow = new PopupWindow();
//                popupWindow.setContentView();
            }
        });

    }

    private void initView() {
        imv = (ImageView) findViewById(R.id.iv_good_info_image);
        textView = findViewById(R.id.tv_good_info_collection);
        tv_shopcar = findViewById(R.id.tv_good_info_cart);
        button = findViewById(R.id.btn_good_info_addcart);
    }
}