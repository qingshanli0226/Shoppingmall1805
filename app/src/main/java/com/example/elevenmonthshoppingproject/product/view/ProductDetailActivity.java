package com.example.elevenmonthshoppingproject.product.view;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.common.ShopMallContants;
import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.product.contract.ProductDetailContract;
import com.example.elevenmonthshoppingproject.product.presenter.ProductDetailPresenterImpl;
import com.example.framwork.BaseMVPActivity;
import com.example.framwork.CacheManager;
import com.example.framwork.ShopUserManager;
import com.example.net.bean.ShopcarBean;

import java.util.List;


public class ProductDetailActivity extends BaseMVPActivity<ProductDetailPresenterImpl, ProductDetailContract.IProductDetailView> implements ProductDetailContract.IProductDetailView, View.OnClickListener, CacheManager.IShopCarChangeLinsterner {
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private ProductDetailPresenterImpl productDetailPresenter;
    private String proDuctId;
    private String proDuctimg;
    private String productName;
    private String prodctPrice;

    private int newNum;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_good_info_addcart:
                if (!ShopUserManager.getInstance().isUserLogin()){
                    ARouter.getInstance().build("/login/loginActivity").navigation();
                    return;
                }
                checkHasProduct();
                break;
            case R.id.tv_good_info_cart:
                ARouter.getInstance().build("/Main/MainActivity").navigation();
                break;
        }
    }

    private void checkHasProduct() {
        productDetailPresenter.checkOneProductNum(proDuctId,"1");
    }

    @Override
    public void onCheckOneProduct(String productNum) {
                if (Integer.valueOf(productNum)>=1){
                    if (checkIfShopcarListHasProduct()){
                        ShopcarBean shopcarBan = CacheManager.getInstance().getShopcarBan(proDuctId);
                        int oldnum = Integer.parseInt(shopcarBan.getProductNum());
                        newNum=oldnum+1;
                        productDetailPresenter.updateProductNum(proDuctId,productNum,productName,proDuctimg,prodctPrice);
                    }else {
                        productDetailPresenter.addOneProduct(proDuctId,"1",productName,proDuctimg,prodctPrice);
                    }
                }
    }

    private boolean checkIfShopcarListHasProduct() {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if (proDuctId.equals(shopcarBean.getProductId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAddProduct(String addResult) {
        showShopcarAnim(1);
    }

    private void showShopcarAnim(final int i) {
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        int[] controlPoint = new int[2];

        int[] picWebviewPoint = new int[2];
//        picWebView.getLocationInWindow(picWebviewPoint);
        startPoint[0] = picWebviewPoint[0] + 400;
        startPoint[1] = picWebviewPoint[1];
        Log.d("LQS 起始坐标", startPoint[0] + " " + startPoint[1]);

        int[] shopcarImgPoint = new int[2];
//        shopcarImg.getLocationInWindow(shopcarImgPoint);
        endPoint[0] = shopcarImgPoint[0] + 150;
        endPoint[1] = shopcarImgPoint[1] - 100;
        Log.d("LQS 终点坐标", endPoint[0] + " " + endPoint[1]);
        controlPoint[0] = startPoint[0] - 300;
        controlPoint[1] = startPoint[1] + 100;
        Log.d("LQS 控制点坐标", controlPoint[0] + " " + controlPoint[1]);

        //实例化一个ImageView，然后将该iamageView添加到根布局中
        final ImageView animImageView = new ImageView(this);
        RelativeLayout.LayoutParams animLayoutParams = new RelativeLayout.LayoutParams(100,100);
        animImageView.setLayoutParams(animLayoutParams);
        Glide.with(this).load(ShopMallContants.Gui_Url+proDuctimg).into(animImageView);
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_product_detail, null);
        RelativeLayout rootView = inflate.findViewById(R.id.rootView);
        rootView.addView(animImageView);//把小图片添加到根布局中

        //使用贝塞尔曲线完成动画
        Path path = new Path();
        path.moveTo(startPoint[0],startPoint[1]);
        path.quadTo(controlPoint[0],controlPoint[1],endPoint[0],endPoint[1]);
        final PathMeasure pathMeasure = new PathMeasure(path, false);

        //使用属性动画，完成小图片在贝塞尔曲线上的平移
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());//平移属性动画
        valueAnimator.setDuration(5000);
        //注册更新的listener，获取下一个图片平移的坐标
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                Log.d("LQS 动画已经平移的距离:", value + "");
                float[] nextPosition = new float[2];
                pathMeasure.getPosTan(value, nextPosition, null);
                animImageView.setTranslationX(nextPosition[0]);//让小图片移动到下一个坐标处
                animImageView.setTranslationY(nextPosition[1]);

                if (value >= pathMeasure.getLength()) {
                    if (i == 1) {
                        addOneProductToCache();
                    } else {
                        CacheManager.getInstance().updateProductNum(proDuctId, String.valueOf(newNum));
                        Toast.makeText(ProductDetailActivity.this, "在原有的商品数据上成功加了1", Toast.LENGTH_SHORT).show();
                    }
                    animImageView.setVisibility(View.GONE);
                }
            }

        });
        valueAnimator.start();
    }

    private void addOneProductToCache() {
        ShopcarBean shopcarBean = new ShopcarBean();
        shopcarBean.setProductId(proDuctId);
        shopcarBean.setProductName(productName);
        shopcarBean.setProductPrice(prodctPrice);
        shopcarBean.setProductNum("1");
        shopcarBean.setProductSelected(true);
        shopcarBean.setUrl(proDuctimg);
        CacheManager.getInstance().add(shopcarBean);
        Toast.makeText(this, "商品成功加入购物车", Toast.LENGTH_SHORT).show();
//        showMessage("");//该函数代表着当前这个商品已经添加到该用户的购物车上了
    }

    @Override
    public void onProductNumChange(String result) {
        showShopcarAnim(2);
    }

    @Override
    protected void iniHttpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_page;
    }

    @Override
    protected void iniView() {
        ivGoodInfoImage = findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = findViewById(R.id.tv_good_info_name);
        tvGoodInfoPrice = findViewById(R.id.tv_good_info_price);
        tvGoodInfoCart = findViewById(R.id.tv_good_info_cart);
        if (ShopUserManager.getInstance().isUserLogin()){
            tvGoodInfoCart.setText(CacheManager.getInstance().getShopcarBeanList().size()+"");
        }
        btnGoodInfoAddcart = findViewById(R.id.btn_good_info_addcart);



        CacheManager.getInstance().setShopcarDataChangeListener(this);

        btnGoodInfoAddcart.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
        Intent intent = getIntent();
        proDuctId = intent.getStringExtra("hotid");
        proDuctimg = intent.getStringExtra("hotimg");
        productName = intent.getStringExtra("hottxt");
        prodctPrice = intent.getStringExtra("hotprice");
        tvGoodInfoPrice.setText(prodctPrice);
        tvGoodInfoName.setText(productName);
        Glide.with(this).load(proDuctimg).into(ivGoodInfoImage);



    }

    @Override
    protected void initPresenter() {
        productDetailPresenter=new ProductDetailPresenterImpl();
        productDetailPresenter.attatch(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDataChanged(List<ShopcarBean> shopcarBeanList) {

        tvGoodInfoCart.setText(shopcarBeanList.size()+"");//刷新UI
    }

    @Override
    public void onOneDataChanged(int position, ShopcarBean shopcarBean) {

    }

    @Override
    public void onMoneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }

    @Override
    public void onError(String code, String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
