package com.example.detail.detailpage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.detail.R;
import com.example.detail.detailpage.detail.DetailContract;
import com.example.detail.detailpage.detail.DetailPresenterImpl;
import com.example.framework.base.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.UserManager;
import com.example.framework.view.NumberAddSubView;
import com.example.net.Constants;
import com.example.net.bean.AddProductBean;
import com.example.net.bean.GoodsBean;
import com.example.net.bean.MainBean;
import com.example.net.bean.ShopCarBean;
import com.example.net.bean.UpdateProductNumBean;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.io.Serializable;
import java.util.List;


@Route(path = "/detailpage/DetailActivity")
public class DetailActivity extends BaseActivity<DetailPresenterImpl, DetailContract.IDetailView> implements DetailContract.IDetailView {
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private Button btnGoodInfoAddcart;
    private LinearLayout llRoot;
    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;
    private Button btnMore;
    private WebView wbGoodInfoMore;
    private  Serializable extra;
    private String type;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private ProductGoodBean goodBean;
    private NumberAddSubView nasGoodinfoNum;
    private PopupWindow window;
    private int num;
    private Button btGoodinfoCancel;
    private  Button btGoodinfoConfim;
    @Override
    protected void initPresenter() {
        presenter=new DetailPresenterImpl();
        presenter.attchView(this);
    }

    @Override
    protected void initListener() {
        super.initListener();
        tvMoreHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/main/MainActivity").navigation();
                finish();
            }
        });
        btnGoodInfoAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserManager.getInstance().isLogin()){
                    showPopwindow();
                }else {
                    ARouter.getInstance().build("/user/LoginActivity").withString("key","detail").withSerializable("good",extra).withString("type",type).navigation();
                    finish();
               }
            }
        });

        tvGoodInfoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserManager.getInstance().isLogin()){
                    ARouter.getInstance().build("/shopCar/ShopCarActivity").withString("key","detail").withSerializable("good",extra).withString("type",type).navigation();
                    finish();
                }else {
                    ARouter.getInstance().build("/user/LoginActivity").withString("key","detail").withSerializable("good",extra).withString("type",type).navigation();
                    finish();
                }
            }
        });
        tvGoodInfoCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserManager.getInstance().isLogin()){

                }else {
                    ARouter.getInstance().build("/user/LoginActivity").withString("key","detail").withSerializable("good",extra).withString("type",type).navigation();
                    finish();
                }
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llRoot.setVisibility(View.GONE);
            }
        });

    }

    private void showPopwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);
        window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        window.setBackgroundDrawable(dw);
        ImageView ivGoodinfoPhoto = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tvGoodinfoName = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tvGoodinfoPrice = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        nasGoodinfoNum = (NumberAddSubView) view.findViewById(R.id.nas_goodinfo_num);
        btGoodinfoCancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        btGoodinfoConfim = (Button) view.findViewById(R.id.bt_goodinfo_confim);
        Glide.with(DetailActivity.this).load(Constants.BASE_URl_IMAGE + goodBean.getFigure()).into(ivGoodinfoPhoto);
        tvGoodinfoName.setText(goodBean.getName());
        tvGoodinfoPrice.setText(goodBean.getCover_price());
        nasGoodinfoNum.setValue(1);
        nasGoodinfoNum.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {

            }

            @Override
            public void subNumner(View view, int value) {

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

                ShopCarBean.ResultBean bean = CacheManager.getInstance().getShopcarBean(goodBean.getProduct_id());
                if(bean==null){
                    presenter.addProduct(goodBean.getProduct_id(),nasGoodinfoNum.getValue(),goodBean.getName(),goodBean.getFigure(),goodBean.getCover_price());
                    num=nasGoodinfoNum.getValue();
                }else {
                    Log.i("Yoyo", "onClick: "+bean.getProductNum());
                    Log.i("Yoyo", "onClick: "+nasGoodinfoNum.getValue());
                    presenter.UpData(goodBean.getProduct_id(),Integer.parseInt(bean.getProductNum())+nasGoodinfoNum.getValue(),goodBean.getName(),goodBean.getFigure(),goodBean.getCover_price());
                    num=nasGoodinfoNum.getValue()+Integer.parseInt(bean.getProductNum());
                }

            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                window.dismiss();
            }
        });
        window.showAsDropDown(wbGoodInfoMore);
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        ARouter.getInstance().build("/main/MainActivity").navigation();
    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        llRoot.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        extra = intent.getSerializableExtra("good");
        type = intent.getStringExtra("type");
        if(extra!=null&&type!=null&&!type.equals("")){
            switch (type){
                case "seckill":
                    MainBean.ResultBean.SeckillInfoBean.ListBean seckillInfoBean = (MainBean.ResultBean.SeckillInfoBean.ListBean) extra;
                    goodBean = new ProductGoodBean(seckillInfoBean.getName(), seckillInfoBean.getCover_price(), seckillInfoBean.getFigure(), seckillInfoBean.getProduct_id());
                    break;
                case "recommend":
                    MainBean.ResultBean.RecommendInfoBean recommendInfoBean = (MainBean.ResultBean.RecommendInfoBean) extra;
                    goodBean = new ProductGoodBean(recommendInfoBean.getName(), recommendInfoBean.getCover_price(), recommendInfoBean.getFigure(), recommendInfoBean.getProduct_id());
                    break;
                case "hot":
                    MainBean.ResultBean.HotInfoBean hotInfoBean = (MainBean.ResultBean.HotInfoBean) extra;
                    goodBean = new ProductGoodBean(hotInfoBean.getName(), hotInfoBean.getCover_price(), hotInfoBean.getFigure(), hotInfoBean.getProduct_id());
                    break;
                case "product":
                    GoodsBean.ResultBean.HotProductListBean productListBean = (GoodsBean.ResultBean.HotProductListBean) extra;
                    goodBean = new ProductGoodBean(productListBean.getName(), productListBean.getCover_price(), productListBean.getFigure(), productListBean.getProduct_id());
                    break;
            }

            setUI(goodBean);

        }


    }

    private void setUI(ProductGoodBean goodBean) {
        Glide.with(this).load(Constants.BASE_URl_IMAGE+goodBean.getFigure()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(goodBean.getName());
        tvGoodInfoPrice.setText("¥"+goodBean.getCover_price());

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);
        llRoot = (LinearLayout) findViewById(R.id.ll_root);


        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);

        tvMoreShare = (TextView) findViewById(R.id.tv_more_share);
        tvMoreSearch = (TextView) findViewById(R.id.tv_more_search);
        tvMoreHome = (TextView) findViewById(R.id.tv_more_home);
        btnMore = (Button) findViewById(R.id.btn_more);

    }

    @Override
    public void onAddOk(AddProductBean bean) {

        if(bean.getCode().equals("200")){
            Toast.makeText(this, "加入购物车成功", Toast.LENGTH_SHORT).show();
            ShopCarBean.ResultBean resultBean = new ShopCarBean.ResultBean();
            resultBean.setProductId(goodBean.getProduct_id());
            resultBean.setProductName(goodBean.getName());
            resultBean.setProductNum(num+"");
            resultBean.setProductPrice(goodBean.getCover_price());
            resultBean.setProductSelected(true);
            resultBean.setUrl(goodBean.getFigure());
            CacheManager.getInstance().addEdit(resultBean);
            CacheManager.getInstance().add(resultBean);
        }else {
            Toast.makeText(this, "加入购物车失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAddError(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }


    @Override
    public void onUpDataOk(UpdateProductNumBean bean) {
        if(!bean.getCode().equals("200")){
            Toast.makeText(this, "加入购物车失败", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "购物车中已存在,数量已更改", Toast.LENGTH_SHORT).show();
            List<ShopCarBean.ResultBean> carList = CacheManager.getInstance().getShopCarList();
            ShopCarBean.ResultBean shopcarBean = CacheManager.getInstance().getShopcarBean(goodBean.getProduct_id());
            int position = carList.indexOf(shopcarBean);
            CacheManager.getInstance().upDataProductNum(position,num+"");
        }
    }

    @Override
    public void onUpDataError(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}