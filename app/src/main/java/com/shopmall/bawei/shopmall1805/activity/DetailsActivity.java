package com.shopmall.bawei.shopmall1805.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.common.BaseActivity;
import com.bw.net.Contants;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.R;
import java.io.Serializable;

public class DetailsActivity extends BaseActivity {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStyle;
    private TextView tvGoodInfoStore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        super.initView();
        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        tvGoodInfoStyle = (TextView) findViewById(R.id.tv_good_info_style);
        tvGoodInfoStore = (TextView) findViewById(R.id.tv_good_info_store);
        llGoodsRoot = (LinearLayout) findViewById(R.id.ll_goods_root);
        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);



        Intent intent = getIntent();
        String action = intent.getAction();
        Log.e("---","action"+action);


        if (action.equals("hotInfo")){
            Serializable bean = intent.getSerializableExtra(action);
            HomeFragmentBean.ResultBean.HotInfoBean hotInfoBean = (HomeFragmentBean.ResultBean.HotInfoBean) bean;
            tvGoodInfoName.setText(hotInfoBean.getName());
            Glide.with(this).load(Contants.BASE_URl_IMAGE+hotInfoBean.getFigure()).into(ivGoodInfoImage);
            tvGoodInfoPrice.setText(hotInfoBean.getCover_price());
        }else  if (action.equals("listBeans")) {
            Serializable bean = intent.getSerializableExtra(action);
            HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean listBean = (HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean) bean;
            tvGoodInfoName.setText(listBean.getName());
            Glide.with(this).load(Contants.BASE_URl_IMAGE + listBean.getFigure()).into(ivGoodInfoImage);
            tvGoodInfoPrice.setText(listBean.getCover_price());
        }else  if (action.equals("recommend_Info")) {
            Serializable bean = intent.getSerializableExtra(action);
            HomeFragmentBean.ResultBean.RecommendInfoBean recommendInfoBean = (HomeFragmentBean.ResultBean.RecommendInfoBean) bean;
            tvGoodInfoName.setText(recommendInfoBean.getName());
            Glide.with(this).load(Contants.BASE_URl_IMAGE + recommendInfoBean.getFigure()).into(ivGoodInfoImage);
            tvGoodInfoPrice.setText(recommendInfoBean.getCover_price());
        }
//        }else  if (action.equals("act_Info")) {
//            Serializable bean = intent.getSerializableExtra(action);
//            HomeFragmentBean.ResultBean.ActInfoBean actInfoBean = (HomeFragmentBean.ResultBean.ActInfoBean) bean;
//            tvGoodInfoName.setText(actInfoBean.getName());
//            Glide.with(this).load(Contants.BASE_URl_IMAGE + actInfoBean.getFigure()).into(ivGoodInfoImage);
//            tvGoodInfoPrice.setText(actInfoBean.getCover_price());
//        }




    }
}
