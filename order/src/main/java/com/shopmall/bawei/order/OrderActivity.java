package com.shopmall.bawei.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.framework.base.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.UserManager;
import com.example.net.bean.CheckInventoryBean;
import com.example.net.bean.ShopCarBean;
import com.shopmall.bawei.pay.qqb.AuthResult;
import com.shopmall.bawei.pay.qqb.PayResult;
import com.shopmall.bawei.pay.qqb.util.OrderInfoUtil2_0;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.util.List;
import java.util.Map;

@Route(path = "/order/OrderActivity")
public class OrderActivity extends BaseActivity<OrderPresenterImpl, OrderContract.IOrderView> implements OrderContract.IOrderView {
    private TextView nameOrder;
    private TextView phoneOrder;
    private TextView adressOrder;
    private RecyclerView rvOrder;
    private TextView priceOrder;
    private RelativeLayout llGoodsRoot;
    private TextView pricePay;
    private Button buyOrder;
    private ProgressBar pbOrder;
    private String key;
    private OrderAdapter adapter;
    private String moneyValue;
    public static final String APPID = "2016102100732196";
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCetpoOv2BedP/Iv1ynicEQ3Bpl3Rmd1Q8EeNs3ljNCR1fQURYYasgRUJPohzsGVlLtPJsPd+jTNqqImsbY/GPuuPgifDQ+e5PnPWZrngm9Ru91wTqi5SIq/BkpN1nF5HIrHs5rRzyBcKcX82fkPBd5N2KNovD+8Biz+pdWuHVOjbCZ7MVCc/LfpwMI9QwZXMrEsm4PvqsnnnlNB3l3gqb110zgqZLX9hckP42+fikYNGxKLFF4zYZ0DRLkGjtLCse2KXgX3Tr4+/BQpxnyGhZMtgFoO9JXQGo0UER9D2FGc7LZCWschyGETLeJwWKIjZXIZD57gfPCK37A+7R7vejnAgMBAAECggEAbNSToSczorGhr3sgwrVgEPqMk2rgJO0zBgMFdwFklr8rBOqFNysJk23obltEax0IcirLvPihSyvCFjfjwGiY8doeNC9s96dvjPH6aDMPRJ3+l4VvesGaA1Wovy14Po1eiBjwvHk1kSC5Q2AhzkwyYGlNCAhCLt5eYhOkcM+9iKXlkISUSdA+t1ER9k5BeM10XnsZX/gl1yYW6fnxQ/XKsfeLmbLRhFbBHd6UwqcBWZqKN8lCh1h6JOF0jLuVByMMJzyXf8yKeQY2iNmUCvJbb/7bePvYoMVKrQzlyHonnM4V7mCxiyOb6740SILJes5N9g/Fu09V/Tfv+hJRWfYZYQKBgQDKvXfkYCrAv4amHDwmaBY57laHAF0LE6tQy8OMuT+BzzAJBx/j3uxJquAuAnHZQS9GR93W5li7fUKRtviM4eBrPjh/lJXAxKJn3JAcqpcXQ0f3DC1pAtOzGjgywqcPuNTu+NygUpPkjtPFBbFynElK2ruEnhBgrH39ARqbGx3WlwKBgQDIaEX6t0As0CQ6uc4BI2+niehmH2LG8ELO0S8UWjCJSB0oq3p0iTws190soQHzITsBWADvSs3hHEP/3LY13FhGNF0lLAkO3CsBcA/IpTaF0oS+lwI6hd2zA/iHLzeD44EGTpJc+HZMuZ+GTf7NRdMNFAKbvDQbPe4w1EEf3reaMQKBgQCaT5/jiXbBAoYgBLmbmfng2hGt647mEXCBrLYIdC9sRCCRnoSdUl2SrKa5Hk89RyoOWkD1gpnjCrISaqu/v2Sq+87Q/G0HLiNW3kAqMYWSxTkPRouBtA8h8UD5EcNKaipYQb7boD7E5hk1iuHHFEGM4fN8OzrH+kJiweZYTElnvQKBgF8Iw5ae67nUgjmu/reffEUwqpoy6/521NeKbw7xre6L2ff9STaWFYkWXHXbbDdFXNvIRbkz+el0I/LjUSy9bsbr8fe8qBb55RLrdzCo1/Ah4n0W0yG5dWZ8zZAdne/XJMo+3D1mPYMoyzM/LUNehzS+dnYvi24XsipJnRBl5x8hAoGAd0W7wYvjdoy0xsPldZxqco5f00e3Ka3SYWbIkYPr6wvxoyWwy+VJMWzXEDLXIIRNrcKrpu1Y2XVAZjsHp4ZYjZDjYaRTOcTb8IZ1Ps+ODWFypxZ9VdQBztqp59a8k4fqNM62UHKL/slJ2X4YtSdXaJMJO8kcvA9TUbodoPi9hX8=";
    public static final String RSA_PRIVATE = "";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @Override
    protected void initPresenter() {
        presenter=new OrderPresenterImpl();
    }

    @Override
    protected void initListener() {
        super.initListener();
        buyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShopCarBean.ResultBean> shopCarPayList = CacheManager.getInstance().getShopCarPayList();
                //ToDo
//                presenter.checkInventory();
            }
        });
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        if(key.equals("main")){
            ARouter.getInstance().build("/main/MainActivity").navigation();
        }else if(key.equals("shopCar")){
            ARouter.getInstance().build("/shopCar/ShopCarActivity").navigation();
        }
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        moneyValue = CacheManager.getInstance().getMoneyValue();
        nameOrder.setText(UserManager.getInstance().getName());
        phoneOrder.setText(UserManager.getInstance().getTel());
        adressOrder.setText(UserManager.getInstance().getAddress());
        priceOrder.setText("¥"+moneyValue);
        pricePay.setText("¥"+moneyValue);
        List<ShopCarBean.ResultBean> shopCarPayList = CacheManager.getInstance().getShopCarPayList();
        adapter.updataData(shopCarPayList);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        pbOrder = (ProgressBar) findViewById(R.id.pb_order);
        nameOrder = (TextView) findViewById(R.id.name_order);
        phoneOrder = (TextView) findViewById(R.id.phone_order);
        adressOrder = (TextView) findViewById(R.id.adress_order);
        rvOrder = (RecyclerView) findViewById(R.id.rv_order);
        priceOrder = (TextView) findViewById(R.id.price_order);
        llGoodsRoot = (RelativeLayout) findViewById(R.id.ll_goods_root);
        pricePay = (TextView) findViewById(R.id.price_pay);
        buyOrder = (Button) findViewById(R.id.buy_order);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        adapter=new OrderAdapter(this);
        rvOrder.setAdapter(adapter);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(OrderActivity.this, "9000", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OrderActivity.this, "不是9000", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        Toast.makeText(OrderActivity.this, "9000", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OrderActivity.this, "不是9000", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            Toast.makeText(OrderActivity.this, "1352132123123", Toast.LENGTH_SHORT).show();

            return;
        }
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void onCheckOk(CheckInventoryBean bean) {
        if(bean.getCode().equals("200")){
            List<CheckInventoryBean.ResultBean> result = bean.getResult();
            int i = result.size();
            Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();
            OrderInfoUtil2_0.setmoney(moneyValue);
            payV2(buyOrder);
        }else {
            Toast.makeText(this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showloading() {
        pbOrder.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        pbOrder.setVisibility(View.GONE);
        if(!isSuccess){
            Toast.makeText(this, ""+errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showEmpty() {

    }
}