package com.example.shopercar.view;

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.framwork.BaseActivity;
import com.example.shopercar.R;

public class ShoppingPageActivity extends BaseActivity {
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;

    private CheckBox productSelect;
    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private ImageView btnAdd;
    private ImageView btnSub;
    private TextView productCount;

    String hotimg,hotprice;
    String hottxt;
    private int i=1;
    private RelativeLayout reView;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_page;
    }

    @Override
    protected void iniView() {


        tvGoodInfoName = findViewById(R.id.tv_good_info_name);
        ivGoodInfoImage = findViewById(R.id.iv_good_info_image);
        tvGoodInfoCart = findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = findViewById(R.id.btn_good_info_addcart);

        reView = findViewById(R.id.re_view);

        productSelect = findViewById(R.id.productSelect);
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        productCount = findViewById(R.id.productCount);

        Intent intent = getIntent();
         hotimg = intent.getStringExtra("hotimg");
         hottxt = intent.getStringExtra("hottxt");
        hotprice = intent.getStringExtra("hotprice");
        tvGoodInfoName.setText(hottxt);
        Glide.with(this).load(hotimg).into(ivGoodInfoImage);

        btnGoodInfoAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //判断是否登陆如果登陆直接使用  如果没有跳转登陆页面
                    product();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                productCount.setText(i+"");
                tvGoodInfoCart.setText("购物车"+i);
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i--;
                productCount.setText(i+"");
                tvGoodInfoCart.setText("购物车"+i);
                if (i==0){
                    tvGoodInfoCart.setText("购物车");
                   reView.setVisibility(View.GONE);
                }

            }
        });

        tvGoodInfoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/Main/MainActivity")
                        .withString("count",i+"")
                        .withString("hotimg",hotimg)
                        .withString("hottxt",hottxt)
                        .withString("hotprice",hotprice)
                        .navigation();
            }
        });


    }

    private void product() {

        reView.setVisibility(View.VISIBLE);
        productSelect.setChecked(true);
        productCount.setText(i+"");
        if (i==0){
            tvGoodInfoCart.setText("购物车");
        }
        tvGoodInfoCart.setText("购物车"+i);
        Glide.with(this).load(hotimg).into(productImage);
        productName.setText(hottxt);
        productPrice.setText(hotprice);
    }

    @Override
    protected void iniData() {

    }
}
