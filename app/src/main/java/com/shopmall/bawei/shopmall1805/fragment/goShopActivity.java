package com.shopmall.bawei.shopmall1805.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;

import java.util.Random;

import framework.BaseActivity;
import framework.greendao.RxGreen;
import framework.greendao.userBean;
import framework.greendao.usernv;
import view.Constants;

public
class goShopActivity extends BaseActivity {
    private ImageView ImageGoShop;
    private TextView tliteGoshop;
    private TextView PriceShop;
    private Button TextGo;
    private  userBean userBean;
    @Override
    protected void OnClickListener() {
        final int i1 = new Random().nextInt(5000);
            TextGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxGreen.getInstance(goShopActivity.this,"user.db")
                            .insertUser(new usernv(i1,userBean.getName(),userBean.getPrice(),userBean.getUrl(),1));
                }
            });
    }

    @Override
    protected void initData() {

        ImageGoShop = (ImageView) findViewById(R.id.Image_goShop);
        tliteGoshop = (TextView) findViewById(R.id.tlite_goshop);
        PriceShop = (TextView) findViewById(R.id.Price_shop);
        TextGo = (Button) findViewById(R.id.Text_go);

         userBean = (framework.greendao.userBean) getIntent().getSerializableExtra("user");

        Log.i("=====","这是跳转过来接受到的数据 -》"+userBean.toString());

        Glide.with(goShopActivity.this).load(Constants.BASE_URl_IMAGE+userBean.getUrl()).into(ImageGoShop);
        tliteGoshop.setText(userBean.getName()+"");
        PriceShop.setText(userBean.getPrice()+"");

    }

    @Override
    protected int getlayoutId() {
        return R.layout.goshopactivity;
    }
}
