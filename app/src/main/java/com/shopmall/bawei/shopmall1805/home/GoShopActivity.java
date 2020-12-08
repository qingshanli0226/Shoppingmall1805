package com.shopmall.bawei.shopmall1805.home;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import framework.BaseActivity;
import framework.CacheManagerc;
import framework.ShopUserManager;
import framework.greendao.userBean;
import framework.mvpc.JsonPresenter;
import mode.ClothesBean;
import mode.ShopcarBean;
import view.Constants;
import view.ShopmallConstant;
import view.loadinPage.ErrorBean;
@Route(path = "/shop/GoShopActivity")
public
class GoShopActivity extends BaseActivity implements View.OnClickListener, CacheManagerc.IShopcarDataChangeListener{
    private ImageView imiageGoShop;
    private TextView tliteGoshop;
    private TextView priceShop;
    private Button textGo;
    private  userBean userBean;
    private ImageView shopcarGoShop;
    private Button shopcarCallect;

    @Override
    protected void createPresenter() {
        jsonPresenter = new JsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }
    @Override
    protected void initData() {

        imiageGoShop = (ImageView) findViewById(R.id.Image_goShop);
        tliteGoshop = (TextView) findViewById(R.id.tlite_goshop);
        priceShop = (TextView) findViewById(R.id.Price_shop);
        textGo = (Button) findViewById(R.id.textGo);
        shopcarGoShop = (ImageView) findViewById(R.id.shopcarGoShop);
        shopcarCallect = (Button) findViewById(R.id.shopcarCallect);

        textGo.setOnClickListener(this);
        userBean = (framework.greendao.userBean) getIntent().getSerializableExtra("user");
        Glide.with(GoShopActivity.this).load(Constants.BASE_URl_IMAGE+userBean.getUrl()).into(imiageGoShop);
        tliteGoshop.setText(userBean.getName()+"");
        priceShop.setText(userBean.getPrice()+"");
       // CacheManager.getInstance().setShopcarDataChangeListener(this);
        CacheManagerc.getInstance().setiShopcarDataChangeListener(this);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.goshopactivity;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textGo:
                if (!ShopUserManager.getInstance().isUserLogin()){
                    ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH).withInt(ShopmallConstant.TO_LOGIN_KEY, ShopmallConstant.TO_LOGIN_FROM_MINE_FRAGMENT).navigation();
                    finish();
                    return;
                }else {
                    onProductNumChange(2);
                }
                break;
        }
    }

    private void onProductNumChange(int type) {
        showShopcarAnim(2);
    }

    private void showShopcarAnim(final int type) {
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        int[] controlPoint = new int[2];

        int[] picWebviewPoint = new int[2];
        imiageGoShop.getLocationInWindow(picWebviewPoint);
        startPoint[0] = picWebviewPoint[0]+400;
        startPoint[1] = picWebviewPoint[1];

        Log.i("====","起始坐标是-->>"+startPoint[1]+"===="+startPoint[0]);

        int[] shopcarImgPoint  = new int[2];
        shopcarGoShop.getLocationInWindow(shopcarImgPoint);
        endPoint[0] = picWebviewPoint[0]+150;
        endPoint[1] = picWebviewPoint[1]-100;
        Log.i("====","终点坐标-->>"+endPoint[1]+"===="+endPoint[0]);

        controlPoint[0] = startPoint[0]-300;
        controlPoint[1] = startPoint[1]+100;
        Log.i("====","控制点坐标-->>"+controlPoint[1]+"===="+controlPoint[0]);

        final ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams animLayoutParams  = new RelativeLayout.LayoutParams(100,100);
        imageView.setLayoutParams(animLayoutParams);
        Glide.with(this).load(ShopmallConstant.BASE_RESOURCE_IMAGE_URL+userBean.getUrl())
                .into(imageView);
        LinearLayout rootView = findViewById(R.id.rootView);
        rootView.addView(imageView);
        //贝塞尔曲线 二阶 须要确认开始和终点  和一只平一点
        Path path = new Path();
        path.moveTo(startPoint[0],startPoint[1]);
        path.quadTo(controlPoint[0],controlPoint[1],endPoint[0],endPoint[1]);
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        //使用属性动画
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,pathMeasure.getLength());
        valueAnimator.setDuration(3000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();

                float[] nextPosition = new float[2];
                pathMeasure.getPosTan(value,nextPosition,null);
                imageView.setTranslationX(nextPosition[0]);
                imageView.setTranslationY(nextPosition[1]);

                if (value>=pathMeasure.getLength()){
                    if (type == 1){
                        addOneProductToCache();
                    }else if (type==2){
                        CacheManagerc.getInstance().updateProductNum(userBean.getId(),String.valueOf(userBean.getPrice()));
                        Toast.makeText(GoShopActivity.this, "在原来基础上+1", Toast.LENGTH_SHORT).show();
                    }
                    imageView.setVisibility(View.GONE);
                }
            }
        });
        valueAnimator.start();
    }
    private void addOneProductToCache() {
        ClothesBean.ResultBean.ChildBean childBean = new ClothesBean.ResultBean.ChildBean();
        childBean.setName(userBean.getName());
        childBean.setPic(userBean.getUrl());
        childBean.setParent_id(userBean.getPrice());
        childBean.setIs_deleted("");
        childBean.setP_catalog_id("");
    }
    @Override
    public void onDataChanged(List<ShopcarBean> shopcarBeanList) {

    }

    @Override
    public void onOneDataChanged(int position, ShopcarBean shopcarBean) {

    }

    @Override
    public void onMoneyChanged(String moneyVilue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
