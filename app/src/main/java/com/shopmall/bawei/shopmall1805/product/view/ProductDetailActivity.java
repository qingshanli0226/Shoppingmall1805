package com.shopmall.bawei.shopmall1805.product.view;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.shopmall.shopcar.view.ShopcarActivity;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.BaseMVPActivity;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.net.mode.ShopcarBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.product.contract.ProductDetailContract;
import com.shopmall.bawei.shopmall1805.product.presenter.ProductDetailPresenterImpl;
import com.shopmall.bawei.user.LoginRegisterActivity;

import java.util.List;

public class ProductDetailActivity extends BaseMVPActivity<ProductDetailPresenterImpl, ProductDetailContract.IProductDetailView> implements ProductDetailContract.IProductDetailView, View.OnClickListener, CacheManager.IShopcarDataChangeListener {
    private WebView picWebView;
    private TextView priceTv;
    private String productId;
    private String productName;
    private String prodctPrice;
    private TextView shopcarCountTv;
    private String url;
    private int newNum;
    private ImageView shopcarImg;

    @Override
    protected void initPresenter() {
        httpPresenter = new ProductDetailPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void resume() {
        super.resume();
        Log.d("LQS", " width = " + picWebView.getMeasuredWidth());
        Log.d("LQS", " height = " + picWebView.getMeasuredHeight());
    }

    @Override
    protected void initView() {
        picWebView = findViewById(R.id.productWebView);
        priceTv = findViewById(R.id.productDetailPrice);
        shopcarCountTv = findViewById(R.id.shopCarCountTV);
        if (ShopUserManager.getInstance().isUserLogin()) {
            shopcarCountTv.setText(CacheManager.getInstance().getShopcarBeanList().size()+"");
        }
        //注册监听listener，当购物车数据获取到后，通过该listener获取该数据
        CacheManager.getInstance().setShopcarDataChangeListener(this);

        findViewById(R.id.addProduct).setOnClickListener(this);

        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");
        productName = intent.getStringExtra("productName");
        prodctPrice = intent.getStringExtra("productPrice");
        url = intent.getStringExtra("url");

        priceTv.setText(prodctPrice);
        picWebView.setWebViewClient(new WebViewClient());
        picWebView.setWebChromeClient(new WebChromeClient());
        picWebView.loadUrl(ShopmallConstant.BASE_RESOURCE_IMAGE_URL+url);

        findViewById(R.id.productDetailShopcar).setOnClickListener(this);
        shopcarImg = findViewById(R.id.productDetailShopcar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void onCheckOneProduct(String productNum) {
        //服务端将仓库的产品数量返回
        if (Integer.valueOf(productNum) >=1) { //当前仓库有该商品,把该商品添加到购物车
            //添加个判断，判断当前这个商品在购物车是不是已经有了，如果有了，只是把这个数量增加一个，如果购物车
            //上没有这个商品，再把商品添加到购物车上，防止一个商品出现两条数据
            if (checkIfShopcarListHasProduct()) {
                ShopcarBean shopcarBean = CacheManager.getInstance().getShopcarBan(productId);
                int oldNum = Integer.parseInt(shopcarBean.getProductNum());
                newNum = oldNum+1;
                httpPresenter.updateProductNum(productId,String.valueOf(newNum),productName,url,prodctPrice);
            } else {
                httpPresenter.addOneProduct(productId, "1", productName, url, prodctPrice);
            }
        }
    }

    private boolean checkIfShopcarListHasProduct() {
        //第一步获取当前购物车上的商品列表
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (productId.equals(shopcarBean.getProductId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAddProduct(String addResult) {
        Log.d("LQS", " width = " + picWebView.getMeasuredWidth());
        Log.d("LQS", " height = " + picWebView.getMeasuredHeight());

        //显示加入成功的购物车动画
        showShopcarAnim(1);

    }

    private void addOneProductToCache() {
        //把该商品也添加到在单例中存储的购物车公共数据里
        ShopcarBean shopcarBean = new ShopcarBean();
        shopcarBean.setProductId(productId);
        shopcarBean.setProductName(productName);
        shopcarBean.setProductPrice(prodctPrice);
        shopcarBean.setProductNum("1");
        shopcarBean.setProductSelected(true);
        shopcarBean.setUrl(url);
        CacheManager.getInstance().add(shopcarBean);
        showMessage("商品成功加入购物车");//该函数代表着当前这个商品已经添加到该用户的购物车上了

    }


    private void showShopcarAnim(final int type) {
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        int[] controlPoint = new int[2];

        int[] picWebviewPoint = new int[2];
        picWebView.getLocationInWindow(picWebviewPoint);
        startPoint[0] = picWebviewPoint[0] + 400;
        startPoint[1] = picWebviewPoint[1];
        Log.d("LQS 起始坐标", startPoint[0] + " " + startPoint[1]);

        int[] shopcarImgPoint = new int[2];
        shopcarImg.getLocationInWindow(shopcarImgPoint);
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
        Glide.with(this).load(ShopmallConstant.BASE_RESOURCE_IMAGE_URL+url).into(animImageView);
        RelativeLayout rootView = findViewById(R.id.rootView);
        rootView.addView(animImageView);//把小图片添加到根布局中

        //使用贝塞尔曲线完成动画
        Path path = new Path();
        path.moveTo(startPoint[0],startPoint[1]);
        path.quadTo(controlPoint[0],controlPoint[1],endPoint[0],endPoint[1]);
        final PathMeasure pathMeasure = new PathMeasure(path, false);

        //使用属性动画，完成小图片在贝塞尔曲线上的平移
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());//平移属性动画
        valueAnimator.setDuration(1000);
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
                      if (type == 1) {
                          addOneProductToCache();
                      } else {
                          CacheManager.getInstance().updateProductNum(productId, String.valueOf(newNum));
                          showMessage("在原有的商品数据上成功加了1");
                      }
                      animImageView.setVisibility(View.GONE);
                  }
            }

        });
        valueAnimator.start();
    }

    @Override
    public void onProductNumChange(String result) {
        //已经成功的把购物车商品数据增加了一个
        //更新本地缓存中的商品数量，始终让两个数据保持一致
        showShopcarAnim(2);
    }


    @Override
    public void showLoaing() {
        showLoading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoading(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addProduct:
                //第一步先判断用户是否登录，没有登录的话，跳转到登录页面
                if (!ShopUserManager.getInstance().isUserLogin()) {
                    ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH).withInt(ShopmallConstant.TO_LOGIN_KEY,ShopmallConstant.TO_LOGIN_FROM_GOODS_DETAIL_ADD_SHOPCAR).navigation(this,100);
                    return;
                }
                //第二步判断仓库是否有足够的产品
                checkHasProduct();
                break;
            case R.id.productDetailShopcar:
                if (!ShopUserManager.getInstance().isUserLogin()) {
                    ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH).withInt(ShopmallConstant.TO_LOGIN_KEY,ShopmallConstant.TO_LOGIN_FROM_GOODS_DETAIL_SHOPCAR_PIC).navigation();
                    return;
                }
                launchActivity(ShopcarActivity.class, null);
                break;
                default:
                    break;
        }
    }

    private void checkHasProduct() {
        httpPresenter.checkOneProductNum(productId, "1");//检查仓库是不是还有一件该商品
    }

    @Override
    public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
        shopcarCountTv.setText(shopcarBeanList.size()+"");//刷新UI
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
}
