package com.shopmall.bawei.shopmall1805.app.adapter.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.ui.activity.MainActivity;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.framework.BaseActivity;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPActivity;
import com.shopmall.bawei.shopmall1805.framework.ShopUserManager;

public class DetailsActivity extends BaseActivity {
    private ImageView detailImg;
    private TextView detailTitle;
    private TextView detailMsg;
    private TextView detailPrice;
    private PopupWindow popupWindow;
    private TextView detailShopcar;
    private Button detailJoinShopcarBt;
    private ImageView popImg;
    private TextView popTitle;
    private TextView popPrice;
    private ImageView addNumberJianImg;
    private TextView addNumber;
    private ImageView addNumberJiaImg;
    private String figure;
    private String name;
    private   String cover_price;
    private int number = 0;
    private Button btCancel;
    private Button btPushi;
    @Override
    protected void initData() {
        toolbar.setToolBarTitle("商品详情");
        toolbar.setToolBarRightImg(R.drawable.icon_more);
        figure = getIntent().getStringExtra("figure");
        name = getIntent().getStringExtra("name");
        cover_price= getIntent().getStringExtra("cover_price");
        if(figure!=null){
            Glide.with(this).load(ConfigUrl.BASE_IMAGE+figure).into(detailImg);
            detailTitle.setText(name);
            detailPrice.setText("￥"+cover_price);
        }
        detailJoinShopcarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ShopUserManager.getInstance().isUserLogin()){
                    Toast.makeText(DetailsActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH)
                            .withInt(ShopmallConstant.TO_LOGIN_KEY,ShopmallConstant.TO_LOGIN_FROM_GOODS_DETAIL_ADD_SHOPCAR)
                            .navigation();
                }else {
                    popwindow();
                }
            }
        });
    }

    private void popwindow() {
        popupWindow = new PopupWindow();
        View inflate = getLayoutInflater().inflate(R.layout.down_popuwindow, null);
        popImg = inflate.findViewById(R.id.pop_img);
        popTitle = inflate.findViewById(R.id.pop_title);
        popPrice = inflate.findViewById(R.id.pop_price);
        addNumberJianImg = inflate.findViewById(R.id.add_number_jian_img);
        addNumber = inflate.findViewById(R.id.add_number);
        addNumberJiaImg = inflate.findViewById(R.id.add_number_jia_img);
        btCancel = inflate.findViewById(R.id.bt_cancel);
        btPushi = inflate.findViewById(R.id.bt_pushi);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        btPushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShopAnimal();
                popupWindow.dismiss();
            }
        });
        Glide.with(DetailsActivity.this).load(ConfigUrl.BASE_IMAGE+figure).into(popImg);
        popTitle.setText(name);
        popPrice.setText("￥"+cover_price);
        addNumber.setText(""+number);
        addNumberJianImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number != 0){
                    number--;
                    addNumber.setText(""+number);
                }
            }
        });
        addNumberJiaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number++;
                addNumber.setText(""+number);
            }
        });
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(inflate);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(detailJoinShopcarBt, Gravity.BOTTOM,0,100);
    }
    private void addShopAnimal() {


    }
    @Override
    protected void initView() {
        detailImg = findViewById(R.id.detail_img);
        detailTitle = findViewById(R.id.detail_title);
        detailMsg = findViewById(R.id.detail_msg);
        detailPrice = findViewById(R.id.detail_price);
        detailShopcar = findViewById(R.id.detail_shopcar);
        detailJoinShopcarBt = findViewById(R.id.detail_join_shopcar_bt);

        detailJoinShopcarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }
}