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

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.net.mode.GoodsBean;
import com.shopmall.bawei.shopmall1805.R;

public class GoodsInfoActivity extends BaseActivity implements View.OnClickListener {
    private Button btnGoodInfoAddCart;
    private GoodsBean goods_bean;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoPrice;
    private WebView wbGoodInfoMore;

    @Override
    protected void initView() {
        btnGoodInfoAddCart = findViewById(R.id.btn_good_info_addcart);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);
        Intent intent = getIntent();
        goods_bean = (GoodsBean) intent.getSerializableExtra("goods_bean");
        if (goods_bean != null) {
            //本地获取存储的数据
            setDataFormView(goods_bean);
        }
    }

    private void setDataFormView(GoodsBean goodsBean) {
        String name = goodsBean.getName();
        String cover_price = goodsBean.getCover_price();
        String figure = goodsBean.getFigure();
        Log.i("TAG", "setDataFormView: "+figure);
        String product_id = goodsBean.getProduct_id();

        Glide.with(this).load(UrlHelper.BASE_URl_IMAGE + figure).into(ivGoodInfoImage);
        if (name != null) {
            tvGoodInfoName.setText(name);
        }
        if (cover_price != null) {
            tvGoodInfoPrice.setText("￥" + cover_price);
        }
        setWebView(product_id);
    }

    private void setWebView(String product_id) {
        if (product_id != null) {
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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_good_info_addcart:
                showPopWindow();
                break;
        }
    }

    private void showPopWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);


        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);

        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        window.setBackgroundDrawable(dw);

        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        ImageView iv_goodinfo_photo = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        NumberAddSubView nas_goodinfo_num = (NumberAddSubView) view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_cancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        Button bt_goodinfo_confim = (Button) view.findViewById(R.id.bt_goodinfo_confim);

        Glide.with(GoodsInfoActivity.this).load(UrlHelper.BASE_URl_IMAGE + goods_bean.getFigure()).into(iv_goodinfo_photo);

        // 名称
        tv_goodinfo_name.setText(goods_bean.getName());
        // 显示价格
        tv_goodinfo_price.setText(goods_bean.getCover_price());

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




                Log.e("TAG", "66:" + goods_bean.toString());
                Toast.makeText(GoodsInfoActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
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
}
