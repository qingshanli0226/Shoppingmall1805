package com.shopmall.bawei.shopmall1805.product.view;

import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.shopmall.shopcar.view.ShopcarActivity;
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

public class ProductDetailActivity extends BaseMVPActivity<ProductDetailPresenterImpl, ProductDetailContract.IProductDetailView> implements ProductDetailContract.IProductDetailView, View.OnClickListener {
    private WebView picWebView;
    private TextView priceTv;
    private String productId;
    private String productName;
    private String prodctPrice;
    private String url;
    private int newNum;

    @Override
    protected void initPresenter() {
        httpPresenter = new ProductDetailPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        picWebView = findViewById(R.id.productWebView);
        priceTv = findViewById(R.id.productDetailPrice);
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
        showMessage("商品成功加入购物车");//该函数代表着当前这个商品已经添加到该用户的购物车上了

        //把该商品也添加到在单例中存储的购物车公共数据里
        ShopcarBean shopcarBean = new ShopcarBean();
        shopcarBean.setProductId(productId);
        shopcarBean.setProductName(productName);
        shopcarBean.setProductPrice(prodctPrice);
        shopcarBean.setProductNum("1");
        shopcarBean.setProductSelected(true);
        shopcarBean.setUrl(url);
        CacheManager.getInstance().add(shopcarBean);
    }

    @Override
    public void onProductNumChange(String result) {
        //已经成功的把购物车商品数据增加了一个
        showMessage("在原有的商品数据上成功加了1");
        //更新本地缓存中的商品数量，始终让两个数据保持一致
        CacheManager.getInstance().updateProductNum(productId, String.valueOf(newNum));
    }

    @Override
    public void showError(String code, String message) {
        showMessage(code + message);
    }

    @Override
    public void showLoaing() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
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
}
