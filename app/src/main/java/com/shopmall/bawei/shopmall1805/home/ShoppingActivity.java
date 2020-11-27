package com.shopmall.bawei.shopmall1805.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1805.R;

import java.io.Serializable;

public class ShoppingActivity extends AppCompatActivity {
    private ImageButton mIbGoodInfoBack;
    private ImageButton mIbGoodInfoMore;
    private ImageView mIvGoodInfoImage;
    private TextView mTvGoodInfoName;
    private TextView mTvGoodInfoDesc;
    private TextView mTvGoodInfoPrice;
    private TextView mTvGoodInfoStore;
    private TextView mTvGoodInfoStyle;
    private WebView mWbGoodInfoMore;
    private LinearLayout mLlGoodsRoot;
    private TextView mTvGoodInfoCallcenter;
    private TextView mTvGoodInfoCollection;
    private TextView mTvGoodInfoCart;
    private Button mBtnGoodInfoAddcart;
    private LinearLayout mLlRoot;
    private TextView mTvMoreShare;
    private TextView mTvMoreSearch;
    private TextView mTvMoreHome;
    private Button mBtnMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        mIbGoodInfoBack = findViewById(R.id.ib_good_info_back);
        mIbGoodInfoMore = findViewById(R.id.ib_good_info_more);
        mIvGoodInfoImage = findViewById(R.id.iv_good_info_image);
        mTvGoodInfoName = findViewById(R.id.tv_good_info_name);
        mTvGoodInfoDesc = findViewById(R.id.tv_good_info_desc);
        mTvGoodInfoPrice = findViewById(R.id.tv_good_info_price);
        mTvGoodInfoStore = findViewById(R.id.tv_good_info_store);
        mTvGoodInfoStyle = findViewById(R.id.tv_good_info_style);
        mWbGoodInfoMore = findViewById(R.id.wb_good_info_more);
        mLlGoodsRoot = findViewById(R.id.ll_goods_root);
        mTvGoodInfoCallcenter = findViewById(R.id.tv_good_info_callcenter);
        mTvGoodInfoCollection = findViewById(R.id.tv_good_info_collection);
        mTvGoodInfoCart = findViewById(R.id.tv_good_info_cart);
        mBtnGoodInfoAddcart = findViewById(R.id.btn_good_info_addcart);
        mLlRoot = findViewById(R.id.ll_root);
        mTvMoreShare = findViewById(R.id.tv_more_share);
        mTvMoreSearch = findViewById(R.id.tv_more_search);
        mTvMoreHome = findViewById(R.id.tv_more_home);
        mBtnMore = findViewById(R.id.btn_more);

        final Intent intent = getIntent();
        HomeBean.ResultBean.RecommendInfoBean bean =(HomeBean.ResultBean.RecommendInfoBean) intent.getSerializableExtra("bean");
        Glide.with(this).load("http://49.233.0.68:8080/atguigu/img/"+bean.getFigure()).into(mIvGoodInfoImage);
        String cover_price = bean.getCover_price();
        String name = bean.getName();
        String product_id = bean.getProduct_id();
        mTvGoodInfoName.setText(name);
        mTvGoodInfoPrice.setText(cover_price);
        mIbGoodInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ShoppingActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}