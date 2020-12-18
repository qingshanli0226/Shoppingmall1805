package com.shopmall.bawei.shopmall1805.ui.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.framework.example.framework.BaseActivity;
import com.shopmall.bawei.framework.example.framework.manager.CacheManager;
import com.shopmall.bawei.framework.example.framework.manager.UserManage;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.bean.PrimereBean;
import com.shopmall.bawei.shopmall1805.ui.activity.checkmvp.ProductDetailContract;
import com.shopmall.bawei.shopmall1805.ui.activity.checkmvp.ProductDetailPresenterImpl;

import java.util.List;

@Route(path="/goodsinto/GoodinfoActivity")
public class GoodinfoActivity extends BaseActivity<ProductDetailPresenterImpl, ProductDetailContract.IProductDetailView> implements View.OnClickListener, ProductDetailContract.IProductDetailView,CacheManager.IShopcarDataChangeListener {
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
    private int num = 1;
    private PrimereBean goods_bean;

    private boolean b = false;
    private int newNum;




    @Override
    protected void initpreseter() {
        httpresenter = new ProductDetailPresenterImpl();
    }

    @Override
    protected void initdate() {

        b = UserManage.getInstance().quFlag();

        Intent intent = getIntent();
        goods_bean = (PrimereBean) intent.getSerializableExtra("goods_bean");
        Toast.makeText(this, ""+goods_bean.getName(), Toast.LENGTH_SHORT).show();
        Glide.with(this).load(goods_bean.getPic()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(""+goods_bean.getName());
        tvGoodInfoPrice.setText(""+goods_bean.getPrice());

        Log.i("---", "initdate: good_info_id:"+goods_bean.getId());

        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        Log.i("---", "initdate: shopcarListSize："+shopcarBeanList.size());


        //点击监听事件
        tvGoodInfoCart.setOnClickListener(this);
        tvGoodInfoCallcenter.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);
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

        //注册监听listener，当购物车数据获取到后，通过该listener获取该数据
        CacheManager.getInstance().setShopcarDataChangeListener(this);

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
                if (!b){
                    ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
                    return;
                }
                ARouter.getInstance().build("/goodcar/ShopcarActivity").navigation();
                break;
            case R.id.tv_good_info_callcenter:
                if (!b){
                    ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
                    return;
                }
                Intent intent = new Intent(GoodinfoActivity.this, CallcenterActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_good_info_collection:
                if (!b){
                    ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
                    return;
                }
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_good_info_addcart:
                if (!b){
                    ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
                    return;
                }
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setWidth(GridView.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(GridView.LayoutParams.WRAP_CONTENT);
                View inflate = LinearLayout.inflate(GoodinfoActivity.this,R.layout.popupwindow_add_product, null);

                Button bt_goodinfo_cancel = inflate.findViewById(R.id.bt_goodinfo_cancel);
                Button bt_goodinfo_confim = inflate.findViewById(R.id.bt_goodinfo_confim);
                final TextView tv_count = inflate.findViewById(R.id.tv_count);


                popupWindow.setContentView(inflate);
                popupWindow.showAtLocation(inflate,Gravity.BOTTOM,0,0);
                bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(GoodinfoActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });

                bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkHasProduct();
                        popupWindow.dismiss();
                    }
                });
                break;
        }
    }
    private void checkHasProduct(){
        //检查库存
        httpresenter.checkOneProductNum(goods_bean.getId(),"1");

    }
    private void showShopcarAnim(final int i) {
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        int[] controlPoint = new int[2];
        int[] picWebViewPoint = new int[2];

        ivGoodInfoImage.getLocationInWindow(picWebViewPoint);

        startPoint[0] = picWebViewPoint[0]+400;
        startPoint[1] = picWebViewPoint[1];
        Log.d("起始坐标", startPoint[0] + " " + startPoint[1]);

        int[] shopCarImgPoint = new int[2];
        tvGoodInfoCart.getLocationInWindow(shopCarImgPoint);
        endPoint[0] = shopCarImgPoint[0]+80;
        endPoint[1] = shopCarImgPoint[1]-50;
        Log.d("终点坐标", endPoint[0] + " " + endPoint[1]);

        controlPoint[0] = startPoint[0] - 300;
        controlPoint[1] = startPoint[1] + 100;
        Log.d("控制点坐标", controlPoint[0] + " " + controlPoint[1]);

        //实例化一个ImageView，然后将该iamageView添加到根布局中
        final ImageView animImageView = new ImageView(this);
        RelativeLayout.LayoutParams animLayoutParams = new RelativeLayout.LayoutParams(100,100);
        animImageView.setLayoutParams(animLayoutParams);
        Glide.with(this).load(""+goods_bean.getPic()).into(animImageView);
        RelativeLayout rootView = findViewById(R.id.rootView);
        rootView.addView(animImageView);//把小图片添加到根布局中

        //使用贝塞尔曲线完成动画
        Path path = new Path();
        path.moveTo(startPoint[0],startPoint[1]);
        path.quadTo(controlPoint[0],controlPoint[1],endPoint[0],endPoint[1]);
        final PathMeasure pathMeasure = new PathMeasure(path,false);

        //使用属性动画，完成小图片在贝塞尔曲线上的平移
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,pathMeasure.getLength());
        valueAnimator.setDuration(1000);
        //注册更新的listener，获取下一个图片平移的坐标
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                Log.d("动画已经平移的距离:", value + "");
                float[] nextPosition = new float[2];

                pathMeasure.getPosTan(value,nextPosition,null);
                animImageView.setTranslationX(nextPosition[0]);
                animImageView.setTranslationY(nextPosition[1]);

                if (value >= pathMeasure.getLength()){
                    if (i == 1){
                        addOneProductToCache();
                        Log.i("---", "onAnimationUpdate: addoneProducctSuccess----");
                    }else {
                        CacheManager.getInstance().updateProductNum(goods_bean.getId(),String.valueOf(newNum));
                        Toast.makeText(GoodinfoActivity.this, "在原有的商品数据上成功加了1", Toast.LENGTH_SHORT).show();
                    }
                    animImageView.setVisibility(View.GONE);
                }
            }
        });
        valueAnimator.start();

    }


    @Override
    public void onDataChanged(List<ShopcarBean> shopcarBeanList) {


        Log.e("---",shopcarBeanList.size()+"");

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
    public void onCheckOneProduct(String productNum) {
        //服务端将仓库的产品数量返回
        if (Integer.valueOf(productNum) >= 1){
            if (checkIfShopcarListHasProduct()){
                ShopcarBean shopcarBan = CacheManager.getInstance().getShopcarBan(goods_bean.getId());
                int oldNum = Integer.parseInt(shopcarBan.getProductNum());
                newNum = oldNum + 1;
                httpresenter.updateProductNum(shopcarBan.getProductId(),String.valueOf(newNum),shopcarBan.getProductName(),shopcarBan.getUrl(),shopcarBan.getProductPrice());
            }else {
                httpresenter.addOneProduct(goods_bean.getId(),"1",goods_bean.getName(),goods_bean.getPic(),goods_bean.getPrice());
            }
        }
    }

    private boolean checkIfShopcarListHasProduct() {
        //第一步获取当前购物车上的商品列表
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (goods_bean.getId().equals(shopcarBean.getProductId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAddProduct(String addResult) {
        showShopcarAnim(1);
    }
    private void addOneProductToCache() {
        //把该商品也添加到在单例中存储的购物车公共数据里
        ShopcarBean shopcarBean = new ShopcarBean();
        shopcarBean.setProductId(goods_bean.getId());
        shopcarBean.setProductName(goods_bean.getName());
        shopcarBean.setProductPrice(goods_bean.getPrice());
        Log.i("---", "addOneProductToCache: productPrice"+goods_bean.getPrice());
        shopcarBean.setProductNum("1");
        shopcarBean.setProductSelected(true);
        shopcarBean.setUrl(goods_bean.getPic());
        CacheManager.getInstance().add(shopcarBean);
        Toast.makeText(this, "商品成功加入购物车", Toast.LENGTH_SHORT).show();//该函数代表着当前这个商品已经添加到该用户的购物车上了
    }


    @Override
    public void onProductNumChange(String result) {
        showShopcarAnim(2);
    }




    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {
        showloading();
    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showErroy(String message) {

    }

    @Override
    public void showEmpty() {

    }
}
