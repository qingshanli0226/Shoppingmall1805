package com.shopmall.bawei.shopmall1805.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.framework.MySql;
import com.example.framework.ShopUsermange;
import com.example.net.ConfigUrl;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.entity.ShopEntityDao;

public class XiangActivity extends AppCompatActivity {

    private ImageView imv;
    private TextView textView;
    public static ShopEntityDao shopEntityDao;
    private MySql mySql;
    private SQLiteDatabase sqLiteDatabase;
    private TextView tv_shopcar;
    private Button button;
    private ImageView ivGoodinfoPhoto,btnSub,btnAdd;
    private TextView tvGoodinfoName,tvGoodinfoPrice,tvCount;
    private Button btGoodinfoCancel,btGoodinfoConfim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);

        initView();
        Intent intent = getIntent();
        final String path = intent.getStringExtra("path");
        final String name = intent.getStringExtra("name");
        final String money = intent.getStringExtra("money");


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
                values.put("Name",name);
                values.put("Path",path);
                values.put("Money",money);
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
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setWidth(GridView.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(GridView.LayoutParams.WRAP_CONTENT);
                View inflate = LinearLayout.inflate(XiangActivity.this,R.layout.popupwindow_add_product,null);
                popupWindow.setContentView(inflate);
                 ivGoodinfoPhoto = inflate.findViewById(R.id.iv_goodinfo_photo);
                tvGoodinfoName = inflate.findViewById(R.id.tv_goodinfo_name);
                tvGoodinfoPrice = inflate.findViewById(R.id.tv_goodinfo_price);
                btnSub = inflate.findViewById(R.id.btn_sub);
                tvCount = inflate.findViewById(R.id.tv_count);
                btnAdd = inflate.findViewById(R.id.btn_add);
                btGoodinfoCancel = inflate.findViewById(R.id.bt_goodinfo_cancel);
                btGoodinfoConfim = inflate.findViewById(R.id.bt_goodinfo_confim);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                if (!ShopUsermange.getInstance().isUserLogin()){
                    ARouter.getInstance()
                            .build("/duoduo/user")
                            .navigation();
                    return;
                }
                popupWindow.showAtLocation(inflate, Gravity.BOTTOM,0,0);
                btGoodinfoCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
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