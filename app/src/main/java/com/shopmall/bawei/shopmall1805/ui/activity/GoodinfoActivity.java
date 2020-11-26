package com.shopmall.bawei.shopmall1805.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.framework.BaseActivity;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.bean.PrimereBean;

@Route(path="/goodsinto/GoodinfoActivity")
public class GoodinfoActivity extends BaseActivity<IPresenter, IView> implements View.OnClickListener {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;

    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {
        Intent intent = getIntent();
        PrimereBean goods_bean = (PrimereBean) intent.getSerializableExtra("goods_bean");
        Toast.makeText(this, ""+goods_bean.getName(), Toast.LENGTH_SHORT).show();
        Glide.with(this).load(goods_bean.getPic()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(""+goods_bean.getName());
        tvGoodInfoPrice.setText(""+goods_bean.getPrice());
        //点击监听事件
        tvGoodInfoCart.setOnClickListener(this);
        tvGoodInfoCallcenter.setOnClickListener(this);
    }

    @Override
    protected void initview() {
        ibGoodInfoBack = findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = findViewById(R.id.tv_good_info_style);
        llGoodsRoot = findViewById(R.id.ll_goods_root);
        tvGoodInfoCallcenter = findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = findViewById(R.id.btn_good_info_addcart);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_goodinfo;
    }
    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_good_info_cart:
                ARouter.getInstance().build("/goodcar/Shpping_car_Activity").navigation();
                break;
            case R.id.tv_good_info_callcenter:
                Intent intent = new Intent(GoodinfoActivity.this, CallcenterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
