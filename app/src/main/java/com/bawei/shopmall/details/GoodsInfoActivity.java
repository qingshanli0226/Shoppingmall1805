package com.bawei.shopmall.details;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.CacheManager;
import com.bawei.framework.ShopUserManager;
import com.bawei.net.mode.ShopcarBean;
import com.bawei.shopmall.details.view.NumberAddSubView;
import com.bawei.shopmall.details.view.VirtualkeyboardHeight;
import com.bawei.shopmall.product.ProductDetailContract;
import com.bawei.shopmall.product.ProductDetailContractImpl;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class GoodsInfoActivity extends BaseActivity<ProductDetailContractImpl, ProductDetailContract.IProductDetailView> implements View.OnClickListener, CacheManager.IShopcarDataChangeListener, ProductDetailContract.IProductDetailView {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoPrice;
    private WebView wbGoodInfoMore;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;
    private LinearLayout ll_root;
    private Button btn_more;
    private String productId;
    private int newNum;
    private String productName;
    private String productPrice;
    private String url;
    private String productNum;

    private ShopcarBean shopcarBean;

    private DetailsGoodsBean goods_bean;

    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {
            finish();
        } else if (v == ibGoodInfoMore) {
            if (ll_root.getVisibility() == View.VISIBLE) {
                ll_root.setVisibility(View.GONE);
            } else {
                ll_root.setVisibility(View.VISIBLE);
            }
        } else if (v == btn_more) {
            ll_root.setVisibility(View.GONE);
        } else if (v == tvMoreShare) {
            Toast.makeText(GoodsInfoActivity.this, R.string.share, Toast.LENGTH_SHORT).show();
//            showShare();
        } else if (v == tvMoreSearch) {
            Toast.makeText(GoodsInfoActivity.this, R.string.search, Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreHome) {
            //Constants.isBackHome = true;
            finish();
        } else if (v == tvGoodInfoCallcenter) {
            Toast.makeText(GoodsInfoActivity.this, R.string.customer_service, Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, CallCenterActivity.class);
            //startActivity(intent);
        } else if (v == tvGoodInfoCollection) {
            Toast.makeText(GoodsInfoActivity.this, R.string.Collection, Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
            //Intent intent = new Intent(this, ShoppingCartActivity.class);
            //startActivity(intent);

            ARouter.getInstance().build("/shopcar/ShopcarActivity").navigation();
        } else if (v == btnGoodInfoAddcart) {
            //添加购物车
            if (!ShopUserManager.getInstance().isUserLogin()) {
                ARouter.getInstance().build(NetConfig.LOGIN_ACTIVITY_PATH).withInt(NetConfig.TO_LOGIN_KEY, NetConfig.TO_LOGIN_FROM_GOODS_DETAIL_ADD_SHOPCAR).navigation(this, 100);
                return;
            }
            showPopwindow();
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_goods_info;
    }

    @Override
    protected void initView() {
        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);

        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);

        ll_root = (LinearLayout) findViewById(R.id.ll_root);
        tvMoreShare = (TextView) findViewById(R.id.tv_more_share);
        tvMoreSearch = (TextView) findViewById(R.id.tv_more_search);
        tvMoreHome = (TextView) findViewById(R.id.tv_more_home);

        btn_more = (Button) findViewById(R.id.btn_more);

        Intent intent = getIntent();
        goods_bean = (DetailsGoodsBean) intent.getSerializableExtra("goods_bean");

        productId = goods_bean.getProductId();
        productPrice = goods_bean.getCoverPrice();
        productName = goods_bean.getName();
        productNum = goods_bean.getNumber() + "";
        url = goods_bean.getFigure();

        shopcarBean = new ShopcarBean();

        shopcarBean.setProductId(productId);
        shopcarBean.setProductPrice(productPrice);
        shopcarBean.setProductNum(productNum);
        shopcarBean.setProductName(productName);
        shopcarBean.setUrl(url);

        if (goods_bean != null) {
            //本地获取存储的数据
            setDataFormView(goods_bean);
        }

        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);

        tvMoreShare.setOnClickListener(this);
        tvMoreSearch.setOnClickListener(this);
        tvMoreHome.setOnClickListener(this);

        btn_more.setOnClickListener(this);

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);
        tvGoodInfoCallcenter.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ProductDetailContractImpl();
    }

    private void setWebView(String product_id) {

        if (product_id != null) {
            wbGoodInfoMore.loadUrl(NetConfig.OFFICIAL_WEBSITE);
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
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


    public void setDataFormView(DetailsGoodsBean goodsBean) {
        String name = goodsBean.getName();
        String cover_price = goodsBean.getCoverPrice();
        String figure = goodsBean.getFigure();
        String product_id = goodsBean.getProductId();

        //Glide.with(this).load(Constants.BASE_URl_IMAGE + figure).into(ivGoodInfoImage);
        if (name != null) {
            tvGoodInfoName.setText(name);
        }
        if (cover_price != null) {
            tvGoodInfoPrice.setText("$" + cover_price);
        }
        if (figure != null) {
            Glide.with(GoodsInfoActivity.this).load(NetConfig.BASE_URl_IMAGE + figure).into(ivGoodInfoImage);
        }
        setWebView(product_id);
    }


    /**
     * 显示popupWindow
     */
    private void showPopwindow() {

        // 1 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 3 参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);


        // 4 控件处理
        ImageView iv_goodinfo_photo = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        NumberAddSubView nas_goodinfo_num = (NumberAddSubView) view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_cancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        Button bt_goodinfo_confim = (Button) view.findViewById(R.id.bt_goodinfo_confim);

        // 加载图片
        Glide.with(GoodsInfoActivity.this).load(NetConfig.BASE_URl_IMAGE + goods_bean.getFigure()).into(iv_goodinfo_photo);

        // 名称
        tv_goodinfo_name.setText(goods_bean.getName());
        // 显示价格
        tv_goodinfo_price.setText(goods_bean.getCoverPrice());

        // 设置最大值和当前值
        nas_goodinfo_num.setMaxValue(8);
        nas_goodinfo_num.setValue(1);

        nas_goodinfo_num.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {
                goods_bean.setNumber(value);
            }

            @Override
            public void subNumner(View view, int value) {
                goods_bean.setNumber(value);
            }
        });

        bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                //添加购物车
                checkHasProduct();
                //addOneProductToCache();
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

    private void checkHasProduct() {
        httpPresenter.checkOneProductNum(productId, "1");
    }

    private void addOneProductToCache() {
        ShopcarBean shopcarBean = new ShopcarBean();
        shopcarBean.setProductId(productId);
        shopcarBean.setProductName(productName);
        shopcarBean.setProductPrice(productPrice);
        shopcarBean.setProductNum("1");
        shopcarBean.setProductSelected(true);
        shopcarBean.setUrl(url);
        CacheManager.getInstance().add(shopcarBean);
        Toast.makeText(this, "添加购物车成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
        tvGoodInfoCart.setText("购物车" + shopcarBeanList.size());
    }

    @Override
    public void onOneDataChanged(int postion, ShopcarBean shopcarBean) {

    }

    @Override
    public void onMoneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }

    @Override
    public void onCheckOneProduct(String productNum) {
        if (Integer.valueOf(productNum) >= 1) {
            if (checkIfShopcarListHasProduct()) {
                CacheManager.getInstance().getShopcarBean(productId);
                int oldNum = Integer.parseInt(shopcarBean.getProductNum());
                newNum = oldNum + 1;
                httpPresenter.updateProdyctNum(productId, String.valueOf(newNum), productName, url, productPrice);
            } else {
                httpPresenter.addOneProduct(productId, "1", productName, url, productPrice);
            }
        }
    }

    private boolean checkIfShopcarListHasProduct() {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        for (ShopcarBean bean : shopcarBeanList) {
            if (productId.equals(bean.getProductId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAddProduct(String addResult) {
        showShopcarAnim(1);
    }

    private void showShopcarAnim(final int type) {
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        int[] controlPoint = new int[2];

        int[] picWebViewPoint = new int[2];

        ivGoodInfoImage.getLocationInWindow(picWebViewPoint);

        startPoint[0] = picWebViewPoint[0] + 400;
        startPoint[1] = picWebViewPoint[1];

        int[] shopcarImgPoint = new int[2];

        tvGoodInfoCart.getLocationInWindow(shopcarImgPoint);
        endPoint[0] = shopcarImgPoint[0] + 120;
        endPoint[1] = shopcarImgPoint[1] - 120;

        controlPoint[0] = startPoint[0] - 300;
        controlPoint[1] = startPoint[1] + 100;

        final ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);

        imageView.setLayoutParams(layoutParams);

        Glide.with(this).load(NetConfig.BASE_RESOURCE_IMAGE_URL + url).circleCrop().into(imageView);
        RelativeLayout rootView = findViewById(R.id.rootview);
        rootView.addView(imageView);

        Path path = new Path();
        path.moveTo(startPoint[0], startPoint[1]);
        path.quadTo(controlPoint[0], controlPoint[1], endPoint[0], endPoint[1]);
        final PathMeasure pathMeasure = new PathMeasure(path, false);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());//平移属性动画
        valueAnimator.setDuration(1000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();

                float[] nextPosition = new float[2];
                pathMeasure.getPosTan(value, nextPosition, null);
                imageView.setTranslationX(nextPosition[0]);//让小图片移动到下一个坐标处
                imageView.setTranslationY(nextPosition[1]);

                if (value >= pathMeasure.getLength()) {
                    if (type == 1) {
                        addOneProductToCache();
                    } else {
                        Toast.makeText(GoodsInfoActivity.this, "喜加一", Toast.LENGTH_SHORT).show();
                        CacheManager.getInstance().updateProductNum(productId, String.valueOf(newNum));
                    }
                    imageView.setVisibility(View.GONE);
                }
            }
        });
        valueAnimator.start();
    }

    @Override
    public void onProductNumChange(String resylt) {
        showShopcarAnim(2);
    }

    @Override
    public void showLoaDing() {
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
    }

    @Override
    public void showEmpty() {

    }
}