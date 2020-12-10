package com.shopmall.bawei.shopmall1805.goodsdesc.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.ARouterHelper;
import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.framework.view.ClickToCheckInterface;
import com.shopmall.bawei.framework.view.NumberAddSubView;
import com.shopmall.bawei.framework.view.ResultFromCheckInterface;
import com.shopmall.bawei.net.mode.GoodsBean;
import com.shopmall.bawei.net.mode.ShopCarBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.goodsdesc.contract.GoodsInfoContract;
import com.shopmall.bawei.shopmall1805.goodsdesc.contract.GoodsInfoImpl;

import java.util.List;

public class GoodsInfoActivity extends BaseActivity<GoodsInfoImpl, GoodsInfoContract.IGoodsInfoView> implements View.OnClickListener, CacheManager.IShopcarDataChangeListener, GoodsInfoContract.IGoodsInfoView, ClickToCheckInterface {
    private Button btnGoodInfoAddCart;
    private GoodsBean goodsBean;
    private ShopCarBean shopCarBean;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoPrice;
    private WebView wbGoodInfoMore;
    private TextView tvGoodInfoCart;

    private PopupWindow window;


    private String name;
    private String coverPrice;
    private String figure;
    private String productId;


    private int newNum;

    private NumberAddSubView nasGoodinfoNum;

    private ResultFromCheckInterface resultFromCheckInterface;





//    public void setCheckNumListener(CheckNumListener checkNumListener){
//        this.checkNumListener = checkNumListener;
//    }


    @Override
    protected void initView() {

        btnGoodInfoAddCart = findViewById(R.id.btn_good_info_addcart);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);
        tvGoodInfoCart.setOnClickListener(this);
        if(UserManager.getInstance().isUserLogin()){
            tvGoodInfoCart.setText("购物车"+CacheManager.getInstance().getShopCarBeanList().size());
        }
        CacheManager.getInstance().setShopCarDataChangeListener(this);


        Intent intent = getIntent();
        goodsBean = (GoodsBean) intent.getSerializableExtra("goods_bean");
        if (goodsBean != null) {
            //本地获取存储的数据
            setDataFormView(goodsBean);
        }
    }

    private void setDataFormView(GoodsBean goodsBean) {
        name = goodsBean.getName();
        coverPrice = goodsBean.getCover_price();
        figure = goodsBean.getFigure();
        productId = goodsBean.getProduct_id();
        Log.i("TAG", "setDataFormView: "+productId);
        shopCarBean = new ShopCarBean();
        shopCarBean.setProductId(goodsBean.getProduct_id());
        shopCarBean.setProductName(goodsBean.getName());
        shopCarBean.setProductNum(String.valueOf(goodsBean.getNumber()));
        shopCarBean.setUrl(goodsBean.getFigure());
        shopCarBean.setProductPrice(goodsBean.getCover_price());

        Glide.with(this).load(UrlHelper.BASE_URl_IMAGE + figure).into(ivGoodInfoImage);
        if (name != null) {
            tvGoodInfoName.setText(name);
        }
        if (coverPrice != null) {
            tvGoodInfoPrice.setText("￥" + coverPrice);
        }
        setWebView(productId);
    }

    private void setWebView(String productId) {
        if (productId != null) {
            //http://192.168.51.104:8080/atguigu/json/GOODSINFO_URL.json2691
//            wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
            wbGoodInfoMore.loadUrl("http://www.atguigu.com");
            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
            //启用支持javascript
            WebSettings settings = wbGoodInfoMore.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);

            //优先使用缓存
            wbGoodInfoMore.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_goods_info;
    }

    @Override
    protected void initListener() {
        btnGoodInfoAddCart.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new GoodsInfoImpl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_good_info_addcart:
                if(!UserManager.getInstance().isUserLogin()){
                    ARouter.getInstance().build(ARouterHelper.USER_LOGIN).withInt(UrlHelper.TO_LOGIN_KEY,UrlHelper.TO_LOGIN_FROM_ADD_SHOP).navigation(this,100);
                    return;
                }
                Log.i("addcart", "onClick: ");
                checkHasProduct(1);
                break;
            case R.id.tv_good_info_cart:
                if(!UserManager.getInstance().isUserLogin()){
                    ARouter.getInstance().build(ARouterHelper.USER_LOGIN).withInt(UrlHelper.TO_LOGIN_KEY,UrlHelper.TO_LOGIN_FROM_SHOP_ACTIVITY).navigation();
                    return;
                }
                break;
        }
    }

    private void checkHasProduct(int num) {
        httpPresenter.checkOneProductNum(productId,String.valueOf(num));
    }

    private void showPopWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);



        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);

        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        window.setBackgroundDrawable(dw);

        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        ImageView ivGoodinfoPhoto = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tvGoodinfoName = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tvGoodinfoPrice = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        nasGoodinfoNum = (NumberAddSubView) view.findViewById(R.id.nas_goodinfo_num);
        nasGoodinfoNum.setClickToCheckInterface(this);
        resultFromCheckInterface = (ResultFromCheckInterface)nasGoodinfoNum;
        Button btGoodinfoCancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        Button btGoodinfoConfim = (Button) view.findViewById(R.id.bt_goodinfo_confim);

        Glide.with(GoodsInfoActivity.this).load(UrlHelper.BASE_URl_IMAGE + shopCarBean.getUrl()).into(ivGoodinfoPhoto);

        // 名称
        tvGoodinfoName.setText(shopCarBean.getProductName());
        // 显示价格
        tvGoodinfoPrice.setText(shopCarBean.getProductPrice());

        goodsBean.setNumber(nasGoodinfoNum.getValue());
        shopCarBean.setProductNum(String.valueOf(nasGoodinfoNum.getValue()));

        nasGoodinfoNum.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(int value) {
                goodsBean.setNumber(value);
                shopCarBean.setProductNum(String.valueOf(nasGoodinfoNum.getValue()));
            }

            @Override
            public void subNumber(View view, int value) {
                goodsBean.setNumber(value);
                shopCarBean.setProductNum(String.valueOf(nasGoodinfoNum.getValue()));
            }
        });

        btGoodinfoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        btGoodinfoConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                //添加购物车

                if(goodsBean.getNumber() > 0) {
                    if(checkIfShopCarListHasProduct()){
                        ShopCarBean shopCarBan = CacheManager.getInstance().getShopCarBan(productId);
                        int oldNum = Integer.parseInt(shopCarBan.getProductNum());
                        newNum = oldNum + Integer.parseInt(shopCarBean.getProductNum());
                        httpPresenter.updateProductNum(productId,String.valueOf(newNum),name,figure,coverPrice);
                    } else {
                        httpPresenter.addOneProduct(shopCarBean.getProductId(), String.valueOf(shopCarBean.getProductNum()), shopCarBean.getProductName(), shopCarBean.getUrl(), shopCarBean.getProductPrice());
                    }
                } else {
                    Toast.makeText(GoodsInfoActivity.this,"请确保商品数大于0,num:"+ goodsBean.getNumber(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                window.dismiss();
            }
        });

        // 5 在底部显示
        window.showAtLocation(GoodsInfoActivity.this.findViewById(R.id.ll_goods_root),
                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(GoodsInfoActivity.this));
    }

    private boolean checkIfShopCarListHasProduct(){
        List<ShopCarBean> shopCarBeanList = CacheManager.getInstance().getShopCarBeanList();
        for (ShopCarBean shopCarBean : shopCarBeanList){
            if(productId.equals(shopCarBean.getProductId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDataChanged(List<ShopCarBean> shopcarBeanList) {
        tvGoodInfoCart.setText("购物车:"+shopcarBeanList.size());
    }

    @Override
    public void onOneDataChanged(int position, ShopCarBean shopcarBean) {

    }

    @Override
    public void onMoneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }

    @Override
    public void onCheckOneProducts(String productNum) {
        if(window == null || !window.isShowing()) {
            showPopWindow();
        }else{
            resultFromCheckInterface.onChecked(Integer.parseInt(productNum));
        }
    }

    @Override
    public void onAddProduct(String addResult) {
        showShopCarAnim(1);
    }

    private void showShopCarAnim(int type) {
        if(type == 1){
            addOneProductToCache();
        }else{
            CacheManager.getInstance().updateProductNum(productId,String.valueOf(newNum));
        }
    }

    private void addOneProductToCache() {
        ShopCarBean shopCarBan = new ShopCarBean();
        shopCarBan.setProductId(productId);
        shopCarBan.setProductName(name);
        shopCarBan.setProductPrice(coverPrice);
        shopCarBan.setProductSelected(true);
        shopCarBan.setProductNum(shopCarBean.getProductNum());
        shopCarBan.setUrl(figure);
        CacheManager.getInstance().add(shopCarBan);
    }

    @Override
    public void onProductNumChange(String result) {
        showShopCarAnim(2);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showLoaDing() {
        showLoading();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void checking(int num) {
        checkHasProduct(num);
    }
}
