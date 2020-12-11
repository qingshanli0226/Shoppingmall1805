package com.shopmall.bawei.shopmall1805.detail;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bw.framework.BaseActivity;
import com.bw.framework.CacheManager;
import com.bw.framework.ShopUserManager;
import com.bw.net.Contants;
import com.bw.net.bean.ShopCarBean;
import com.bw.user.LoginFragment;
import com.shopmall.bawei.shopmall1805.DaoSession;
import com.shopmall.bawei.shopmall1805.GreenDaoBean;
import com.shopmall.bawei.shopmall1805.GreenDaoBeanDao;
import com.shopmall.bawei.shopmall1805.MyGreenManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ShopmallApplication;
import com.shopmall.bawei.shopmall1805.home.Bean;

import java.util.List;


public class DetailsActivity extends BaseActivity<DetailPresenter,DetailContract.DetailView> implements DetailContract.DetailView
        , CacheManager.IShopcarDataChangeListener   {
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

    private Intent intent;
    private String action;
    private Bean bean;
    private int newNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        super.initView();

        ARouter.getInstance().inject(this);
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





        intent = getIntent();
        Log.e("---","action"+action);
        bean = (Bean) intent.getSerializableExtra("goods");


        tvGoodInfoName.setText(bean.getProductName());
        Glide.with(this).load(Contants.BASE_URl_IMAGE+bean.getUrl()).into(ivGoodInfoImage);
        tvGoodInfoPrice.setText(bean.getProductPrice());

        CacheManager.getInstance().setShopCarDataChangerListener(this);

        boolean userLogin = ShopUserManager.getInstance().isUserLogin();

        if (userLogin){
            List<ShopCarBean> shopCarBeans = CacheManager.getInstance().getShopCarBeans();
            tvGoodInfoCart.setText("购物车："+shopCarBeans.size());
        }

        //加入购物车 按钮点击  如果没有登录，跳转登录页面登录
        //如果已经登录，检查仓库中是否有一件商品

        btnGoodInfoAddcart.setOnClickListener(v -> {
            Log.i("----", "initView: "+userLogin);
            if (!userLogin){
                ARouter.getInstance().build("/usr/LoginRegisterActivity").navigation();
            }else {
                //先检查仓库中是否还有一件商品
                checkHasProduct();
//                httpPresenter.addProduct(bean.getProductId(),bean.getProductNum(),bean.getProductName(),bean.getUrl(),bean.getProductPrice());
            }
        });


        //点击进入购物车
        tvGoodInfoCart.setOnClickListener(v -> {

            //判断是否登录，已经登录 跳转购物车 ，没有登录跳转登录页面登录
            Log.i("----", "initView: "+userLogin);

            if (!userLogin){
                ARouter.getInstance().build("/usr/LoginRegisterActivity").navigation();
            }else {
                ARouter.getInstance().build("/activity/activity_shopCart").navigation();
            }
        });

    }

    private void checkHasProduct() {

        //网络请求服务端，检查仓库数量
        httpPresenter.checkOneProductNum(bean.getProductId(),"1");
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new DetailPresenter();
    }

    @Override
    public void onCheckOneProduct(String productNum) {

        //检查到商品数量 返回
        Log.i("---", "onCheckOneProduct: "+productNum);

        if (Integer.parseInt(productNum) >= 1){ //如果商品数量大于1
            if (checkIfShopcarListHasProduct() == true){//判断购物车缓存中是否有这个商品 如果有返回 true

                //购物车缓存中，有这个商品，再次加入这个商品，商品数量加1
                ShopCarBean shopcarBan = CacheManager.getInstance().getShopcarBan(bean.getProductId());
                Log.i("---11", "onCheckOneProduct: "+shopcarBan.getProductName()+","+shopcarBan.getProductNum());
                int oldNum = Integer.parseInt(shopcarBan.getProductNum());
                newNum = oldNum + 1;
                //网络请求到服务端更改商品数量
                httpPresenter.updateProductNum(shopcarBan.getProductId(),String.valueOf(newNum),shopcarBan.getProductName(),shopcarBan.getUrl(),shopcarBan.getProductPrice());
            }else {
                httpPresenter.addProduct(bean.getProductId(),"1",bean.getProductName(),bean.getUrl(),bean.getProductPrice());
            }
        }
    }

    private boolean checkIfShopcarListHasProduct() {
        List<ShopCarBean> shopCarBeans = CacheManager.getInstance().getShopCarBeans();
        for (ShopCarBean shopCarBean : shopCarBeans) {
            if (bean.getProductId().equals(shopCarBean.getProductId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAddProductOk(String addResult) {
        Log.i("---", "onAddProductOk: "+addResult);

        //添加商品到购物车服务端 返回
        //展示动画
        showShopCarAnim(1);
//        addOneProduct();
    }

    private void showShopCarAnim(int i) {
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        int[] controlPoint = new int[2];

        int[] picViewPoint = new int[2];

        ivGoodInfoImage.getLocationInWindow(picViewPoint);
        Log.i("---", "showShopCarAnim: picViewPoint:"+picViewPoint[0]+"------"+picViewPoint[1]);
        startPoint[0] = picViewPoint[0]+400;
        startPoint[1] = picViewPoint[1]+800;
        Log.i("---", "showShopCarAnim:起点坐标 startPoint:"+startPoint[0]+"------"+startPoint[1]);

        int[] shopcarPoint = new int[2];
        tvGoodInfoCart.getLocationInWindow(shopcarPoint);
        Log.i("---", "showShopCarAnim: shopcarPoint:"+shopcarPoint[0]+"------"+shopcarPoint[1]);
        endPoint[0] = shopcarPoint[0]+150;
        endPoint[1] = shopcarPoint[1]-100;
        Log.i("---", "showShopCarAnim:终点坐标 startPoint:"+endPoint[0]+"------"+endPoint[1]);

        controlPoint[0] = shopcarPoint[0]-300;
        controlPoint[1] = shopcarPoint[1]+100;
        Log.i("---", "showShopCarAnim:控制点坐标 startPoint:"+controlPoint[0]+"------"+controlPoint[1]);


        ImageView animView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
        animView.setLayoutParams(layoutParams);
        Glide.with(this).load(Contants.BASE_URl_IMAGE+bean.getUrl()).into(animView);
        RelativeLayout rootview = findViewById(R.id.rootView);
        rootview.addView(animView);

        //使用贝塞尔曲线完成动画
        Path path = new Path();
        path.moveTo(startPoint[0],startPoint[1]);
        path.quadTo(controlPoint[0],controlPoint[1],endPoint[0],endPoint[1]);
        PathMeasure pathMeasure = new PathMeasure(path, false);

        //使用属性动画 完成小图片在贝塞尔曲线上移动
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.setDuration(1000);

        //注册更新监听，获取下一个图片的坐标
        valueAnimator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            Log.d("---", "showShopCarAnim: 动画已经平移的距离"+valueAnimator);
            float[] nextPosition = new float[2];

            pathMeasure.getPosTan(animatedValue,nextPosition,null);
            animView.setTranslationX(nextPosition[0]);
            animView.setTranslationY(nextPosition[1]);

            if (animatedValue >= pathMeasure.getLength()){
                if (i == 1){
                    //如果 是 1  说明是添加一条数据 同步缓存
                    addOneProduct();
                }else {
                    //如果不是 1 说明是修改了 数量 同步缓存
                    CacheManager.getInstance().updateProductNum(bean.getProductId(),String.valueOf(newNum));
                    Toast.makeText(this, "在原有数据数量上+1", Toast.LENGTH_SHORT).show();
                }

                animView.setVisibility(View.GONE);
            }

        });
        valueAnimator.start();
    }

    private void addOneProduct() {
        ShopCarBean shopCarBean = new ShopCarBean();
        shopCarBean.setProductId(bean.getProductId());
        shopCarBean.setProductName(bean.getProductName());
        shopCarBean.setProductNum("1");
        shopCarBean.setUrl(bean.getUrl());
        shopCarBean.setProductPrice(bean.getProductPrice());
        CacheManager.getInstance().add(shopCarBean);
    }

    @Override
    public void onProductNumChange(String result) {
        showShopCarAnim(2);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showsLoaing() {

    }

    @Override
    public void hidesLoading(boolean isSuccess) {

    }

    @Override
    public void showEmpty() {

    }


    @Override
    public void onDataChanged(List<ShopCarBean> shopCarBeanList) {
//        CacheManager.getInstance().setShopCarBeans(shopCarBeanList);
    }

    @Override
    public void onOneDataChanged(int position, ShopCarBean shopCarBean) {
//        CacheManager.getInstance().updatePositionProductNum(position,String.valueOf(newNum));
    }

    @Override
    public void onMoneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }
}
