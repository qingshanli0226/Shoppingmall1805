package com.example.detail.detailpage;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.detail.R;
import com.example.framework.base.BaseActivity;
import com.example.net.Constants;
import com.example.net.bean.GoodsBean;
import com.example.net.bean.MainBean;
import com.google.gson.Gson;

import java.io.Serializable;


@Route(path = "/detailpage/DetailActivity")
public class DetailActivity extends BaseActivity {
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private Button btnGoodInfoAddcart;
    private LinearLayout llRoot;
    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;
    private Button btnMore;


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        ARouter.getInstance().build("/main/MainActivity").navigation();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Serializable extra = intent.getSerializableExtra("good");
        String type = intent.getStringExtra("type");
        if(extra!=null&&type!=null&&!type.equals("")){
            Gson gson = new Gson();
            switch (type){
                case "seckill":
                    MainBean.ResultBean.SeckillInfoBean.ListBean seckillInfoBean = (MainBean.ResultBean.SeckillInfoBean.ListBean) extra;
                    setUI(seckillInfoBean.getFigure(),seckillInfoBean.getName(),"",seckillInfoBean.getCover_price());
                    break;
                case "recommend":
                    MainBean.ResultBean.RecommendInfoBean recommendInfoBean = (MainBean.ResultBean.RecommendInfoBean) extra;
                    setUI(recommendInfoBean.getFigure(),recommendInfoBean.getName(),"",recommendInfoBean.getCover_price());
                    break;
                case "hot":
                    MainBean.ResultBean.HotInfoBean hotInfoBean = (MainBean.ResultBean.HotInfoBean) extra;
                    setUI(hotInfoBean.getFigure(),hotInfoBean.getName(),"",hotInfoBean.getCover_price());
                    break;
                case "product":
                    GoodsBean.ResultBean.HotProductListBean productListBean = (GoodsBean.ResultBean.HotProductListBean) extra;
                    setUI(productListBean.getFigure(),productListBean.getName(),"",productListBean.getCover_price());
                    break;
           }
        }

    }

    private void setUI(String image, String name, String desc, String cover_price) {
        Glide.with(this).load(Constants.BASE_URl_IMAGE+image).into(ivGoodInfoImage);
        tvGoodInfoName.setText(name);
        if(!desc.equals("")){
            tvGoodInfoDesc.setText(desc);
        }
        tvGoodInfoPrice.setText("¥"+cover_price);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        tvMoreShare = (TextView) findViewById(R.id.tv_more_share);
        tvMoreSearch = (TextView) findViewById(R.id.tv_more_search);
        tvMoreHome = (TextView) findViewById(R.id.tv_more_home);
        btnMore = (Button) findViewById(R.id.btn_more);
    }
}