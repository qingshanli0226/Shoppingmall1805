package com.shopmall.bawei.shopmall1805.app.adapter.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
import com.shopmall.bawei.shopmall1805.app.contract.ShopCarContract;
import com.shopmall.bawei.shopmall1805.app.presenter.ShopCarPresenterImpl;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.ErrorBean;
import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPActivity;
import com.shopmall.bawei.shopmall1805.framework.ShopUserManager;
import com.shopmall.bawei.shopmall1805.framework.service.CacheManager;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;

import java.util.List;

public class DetailsActivity extends BaseMVPActivity<ShopCarPresenterImpl, ShopCarContract.IProductDetailView> implements ShopCarContract.IProductDetailView {
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
    private int number = 1;
    private Button btCancel;
    private Button btPushi;
    private String product_id;
    private String productNum = "1";
    private int num;
    @Override
    protected void initData() {
        toolbar.setToolBarTitle("商品详情");
        toolbar.setToolBarRightImg(R.drawable.icon_more);

        figure = getIntent().getStringExtra("figure");
        name = getIntent().getStringExtra("name");
        cover_price= getIntent().getStringExtra("cover_price");
        product_id= getIntent().getStringExtra("product_id");

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
        detailShopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ShopmallConstant.SHOP_CAR_ACTIVITY_PATH)
                        .navigation();
            }
        });
    }
    @Override
    protected void initPresenter() {
        httpPresenter = new ShopCarPresenterImpl();
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
                httpPresenter.checkOneProductNum(product_id,productNum);
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
                if(number != 1){
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
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }
    @Override
    public void onCheckOneProduct(String productNum) {
       if(Integer.parseInt(productNum) >= 1){
           if(checkIfShopcarListHasProduct()){
               ShopcarBean shopcarBean = CacheManager.getInstance().geShopcarBean(product_id);
               int oldNum = Integer.parseInt(shopcarBean.getProductNum());
                num = oldNum+1;
               Log.i("TAGa", "onCheckOneProduct: ");
                httpPresenter.updateProductNum(product_id,String.valueOf(num),name,figure,cover_price);
           }else {
               Log.i("TAGs", "onCheckOneProduct: ");
               httpPresenter.addOneProduct(product_id,"1",name,figure,cover_price);
           }
       }
    }
    private boolean checkIfShopcarListHasProduct(){
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if(product_id.equals(shopcarBean.getProductId())){
                return true;
            }
        }
        return false;
    }
    @Override
    public void onAddProduct(String addRyesult) {
        ShopcarBean shopcarBean = new ShopcarBean();
        shopcarBean.setUrl(figure);
        shopcarBean.setProductSelected(true);
        shopcarBean.setProductNum("1");
        shopcarBean.setProductId(product_id);
        shopcarBean.setProductPrice(cover_price);
        CacheManager.getInstance().add(shopcarBean);
        Toast.makeText(this, "加入购物车", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onProductNumChange(String result) {

    }
    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoading(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {

    }
}