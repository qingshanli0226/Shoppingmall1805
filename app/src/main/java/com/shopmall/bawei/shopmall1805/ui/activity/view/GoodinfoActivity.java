package com.shopmall.bawei.shopmall1805.ui.activity.view;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.text.TextUtils;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.ShopUsermange;
import com.example.net.Confing;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.bean.PrimereBean;
import com.shopmall.bawei.shopmall1805.ui.activity.CallcenterActivity;
import com.shopmall.bawei.shopmall1805.ui.activity.contract.GoodsInfoContract;
import com.shopmall.bawei.shopmall1805.ui.activity.presenter.GoodInfoPresenter;

import java.util.List;

@Route(path="/goodsinto/GoodinfoActivity")
public class GoodinfoActivity extends BaseActivity<GoodInfoPresenter, GoodsInfoContract.IGoodsInfoView> implements GoodsInfoContract.IGoodsInfoView,View.OnClickListener,CacheManager.IShopcarDataCharListerter {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private ScrollView ivLayout;
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
    private ImageView ivGoodinfoPhoto;
    private TextView tvGoodinfoName;
    private TextView tvGoodinfoPrice;
    private ImageView btnSub;
    private TextView tvCount;
    private ImageView btnAdd;
    private Button btGoodinfoCancel;
    private LinearLayout root;
    private Button btGoodinfoConfim;
    private int currentValue = 1; // 默认为1
    private int minValue = 1;
    private int maxValue = 10; // 实际情况为最大库存
    private String productId;
    private String productName;
    private String productprice;
    private String url;
    private String num;
    int newNum;
    @Override
    protected void initpreseter() {
        httpresenter = new GoodInfoPresenter();
    }

    @Override
    protected void initdate() {
        //判断一下如果登录过就让详情页面购物车数据发生改变
        if (ShopUsermange.getInstance().isUserLogin()){
            tvGoodInfoCart.setText("购物车:"+CacheManager.getInstance().getShopcarList().size());
        }
        Intent intent = getIntent();
        PrimereBean goods_bean = (PrimereBean) intent.getSerializableExtra("goods_bean");
        productId = goods_bean.getId();
        productName = goods_bean.getName();
        productprice = goods_bean.getPrice();
        url = goods_bean.getPic();
        num = goods_bean.getId();
        Toast.makeText(this, ""+goods_bean.getName(), Toast.LENGTH_SHORT).show();
        Glide.with(this).load(Confing.BASE_IMAGE + goods_bean.getPic()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(""+goods_bean.getName());
        tvGoodInfoPrice.setText(""+goods_bean.getPrice());
        //点击监听事件
        tvGoodInfoCart.setOnClickListener(this);
        tvGoodInfoCallcenter.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);
    }

    @Override
    protected void initview() {
        //点击监听数据改变，改变后刷新Ui
        CacheManager.getInstance().setshopcarChangedListenter(this);
        root = findViewById(R.id.root);
        ibGoodInfoBack = findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = findViewById(R.id.tv_good_info_desc);
        ivLayout = findViewById(R.id.iv_layout);
        tvGoodInfoPrice = findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = findViewById(R.id.tv_good_info_style);
        llGoodsRoot = findViewById(R.id.ll_goods_root);
        tvGoodInfoCallcenter = findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = findViewById(R.id.btn_good_info_addcart);
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
                ARouter.getInstance().build("/goodcar/Shpping_car_Activity").navigation();
                break;
            case R.id.tv_good_info_callcenter:
                Intent intent = new Intent(GoodinfoActivity.this, CallcenterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_good_info_addcart:
                //判断用户是否登录
                if (!ShopUsermange.getInstance().isUserLogin()){
                    ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
                    return;
                }else {
                    final PopupWindow popupWindow = new PopupWindow();
                    popupWindow.setWidth(GridView.LayoutParams.MATCH_PARENT);
                    popupWindow.setHeight(GridView.LayoutParams.WRAP_CONTENT);
                    View inflate = LinearLayout.inflate(v.getContext(), R.layout.popupwindow_add_product, null);
                    ivGoodinfoPhoto = inflate.findViewById(R.id.iv_goodinfo_photo);
                    tvGoodinfoName = inflate.findViewById(R.id.tv_goodinfo_name);
                    tvGoodinfoPrice = inflate.findViewById(R.id.tv_goodinfo_price);
                    btnSub = inflate.findViewById(R.id.btn_sub);
                    tvCount = inflate.findViewById(R.id.tv_count);
                    btnAdd = inflate.findViewById(R.id.btn_add);
                    btGoodinfoCancel = inflate.findViewById(R.id.bt_goodinfo_cancel);
                    btGoodinfoConfim = inflate.findViewById(R.id.bt_goodinfo_confim);
                    setCurrentValue(getCurrentValue());
                    //自增
                    btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (currentValue < maxValue) {
                                currentValue++;
                            }
                            setCurrentValue(currentValue);
                        }
                    });
                    //自减
                    btnSub.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (currentValue > minValue) {
                                currentValue--;
                            }
                            setCurrentValue(currentValue);
                        }
                    });
                    //点击加入到购物车
                    btGoodinfoConfim.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //判断库存数量进行添加
                            httpresenter.CheckOneproduct(productId, ""+tvCount.getText().toString());
                            popupWindow.dismiss();
                        }
                    });
                    popupWindow.setContentView(inflate);
                    popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
                    btGoodinfoCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            Toast.makeText(GoodinfoActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
    }
    //获取当前数量
    public int getCurrentValue(){
        String string = tvCount.getText().toString();
        if (!TextUtils.isEmpty(string)){
            currentValue = Integer.parseInt(string);
        }
        return currentValue;
    }
    //当前数量输入
    public void setCurrentValue(int currentValue){
        this.currentValue = currentValue;
        tvCount.setText(""+currentValue);
    }

    @Override
    public void onCheckOneproduct(String productNum) {
        /*服务端将仓库的数量返回
         * 首先判断一下库存数量是否大于1，如果大于1就加入到购物车中
         */
        if (Integer.parseInt(productNum)>=Integer.parseInt(tvCount.getText().toString())){
            //再次做一个判断，判断购物车是否有过这个商品，如果有了的话就不需要再次添加，只需要让数量+1
            if (ShopcarListHashproduct()){
                ShopcarBean shopcarBan = CacheManager.getInstance().getShopcarBan(productId);
                int oldNum = Integer.parseInt(shopcarBan.getProductNum());
                newNum = oldNum + 1;
                httpresenter.Productchanged(productId,String.valueOf(newNum),productName,url,productprice);
            }else {
                httpresenter.AddOneproduct(productId,tvCount.getText().toString(),productName,url,productprice);
            }

        }
    }
    //购物车是否存在这个商品，如果存在返回true 不存在就返回false
    private boolean ShopcarListHashproduct(){
        //第一步获取当前购物车列表的数据
        List<ShopcarBean> shopcarList = CacheManager.getInstance().getShopcarList();
        for (ShopcarBean shopcarBean : shopcarList) {
            if (productId.equals(shopcarBean.getProductId())){
                return true;
            }
        }
        return false;
    }
    //往购物车添加一条数据
    @Override
    public void onAddOneproduct(String addresult) {
        //使用贝塞尔曲线动画
        showShopcarAnim(1);

    }
    private void onaddproduct() {
        Toast.makeText(this, "商品成功加入购物车", Toast.LENGTH_SHORT).show();
        ShopcarBean shopcarBean = new ShopcarBean();
        shopcarBean.setProductId(productId);
        shopcarBean.setProductName(productName);
        shopcarBean.setProductNum("1");
        shopcarBean.setProductPrice(productprice);
        shopcarBean.setProductSelected(false);
        shopcarBean.setUrl(url);
        CacheManager.getInstance().add(shopcarBean);
    }
    private void showShopcarAnim(final int type) {
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        int[] controlPoint = new int[2];

        int[] picWebviewPoint = new int[2];
        ivGoodInfoImage.getLocationInWindow(picWebviewPoint);
        startPoint[0] = picWebviewPoint[0] + 400;
        startPoint[1] = picWebviewPoint[1];
        Log.d("LQS 起始坐标", startPoint[0] + " " + startPoint[1]);

        int[] shopcarImgPoint = new int[2];
        tvGoodInfoCart.getLocationInWindow(shopcarImgPoint);
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
        Glide.with(this).load(Confing.BASE_IMAGE+url).into(animImageView);
        root.addView(animImageView);//把小图片添加到根布局中

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
                        onaddproduct();
                    } else {
                        CacheManager.getInstance().updateProductNum(productId, String.valueOf(newNum));
                        Toast.makeText(GoodinfoActivity.this, "商品已存在，再原本的基础上加了1", Toast.LENGTH_SHORT).show();
                    }
                    animImageView.setVisibility(View.GONE);
                }
            }

        });
        valueAnimator.start();
    }
    //使用贝塞尔曲线来完成加入购物车的动画
    private void showShopCarAnimin(final int type) {
        //起始坐标
        int[] startPoint = new int[2];
        //终点坐标
        int[] endPoint = new int[2];
        //控制点坐标
        int[] controlPoint = new int[2];

        int[] picwebViewPoint = new int[2];
        ivGoodInfoImage.getLocationInWindow(picwebViewPoint);
        startPoint[0] = picwebViewPoint[0] + 400;
        startPoint[1] = picwebViewPoint[1];
        Log.e("zld","起始坐标"+startPoint[0]+":"+startPoint[1]);

        int[] shopcarImageview = new int[2];
        tvGoodInfoCart.getLocationInWindow(shopcarImageview);
        endPoint[0] = shopcarImageview[0] +150;
        endPoint[1] = shopcarImageview[1] -100;
        Log.e("zld","终点坐标"+endPoint[0]+":"+endPoint[1]);

        controlPoint[0] = startPoint[0] - 300;
        controlPoint[1] = startPoint[1] + 100;
        Log.e("zld","控制点坐标"+controlPoint[0]+":"+controlPoint[1]);

        //实例化一个imageview，然后将recycle添加到根部局中
        final ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
        imageView.setLayoutParams(layoutParams);
        Glide.with(this).load(Confing.BASE_IMAGE+url).into(imageView);
        //蒋小图标添加到根部局中
        root.addView(imageView);
        //使用贝塞尔曲线完成动画
        final Path path = new Path();
        path.moveTo(startPoint[0],startPoint[1]);
        path.quadTo(controlPoint[0],controlPoint[1],endPoint[0],endPoint[1]);
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        //使用属性动画，完成小图片在贝塞尔曲线上的平移
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.setDuration(1000);
        //注册一个listerter，获取下一个平移坐标
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Log.e("zld","动画已经平移的距离"+animatedValue);
                float[] nextPoint = new float[2];
                pathMeasure.getPosTan(animatedValue,nextPoint,null);
                imageView.setTranslationX(nextPoint[0]);//将小图标移动下一个坐标处
                imageView.setTranslationY(nextPoint[1]);
                if (animatedValue>=pathMeasure.getLength()){
                    if (type == 1){
                        onaddproduct();
                    }else {
                        Toast.makeText(GoodinfoActivity.this, "商品已存在，再原本的基础上加了1", Toast.LENGTH_SHORT).show();
                        CacheManager.getInstance().updateProductNum(productId,String.valueOf(newNum));
                    }
                    imageView.setVisibility(View.GONE);
                }
            }
        });
        valueAnimator.start();
    }



    //更新了一下服务端商品的数量
    @Override
    public void onProductchanged(String result) {

        //更行缓存，让缓存的数量一致
        showShopcarAnim(2);


    }

    @Override
    public void onErroy(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showsloading() {
        showloading();
    }

    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
        showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
    }
    //最新的数据拿到刷新ui
    @Override
    public void ondataChanged(List<ShopcarBean> shopcarBeanList) {
            tvGoodInfoCart.setText("购物车:"+shopcarBeanList.size());
    }

    @Override
    public void onOneChanged(int position, ShopcarBean shopcarBean) {

    }

    @Override
    public void onManeyvhanged(String moneyValue) {

    }

    @Override
    public void onAllselected(boolean isAllSelect) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(this);
    }
}
