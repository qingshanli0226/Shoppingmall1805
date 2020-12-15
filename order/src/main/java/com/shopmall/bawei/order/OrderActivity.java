package com.shopmall.bawei.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.net.bean.ShopCarBean;
import com.shopmall.bawei.pay.qqb.AuthResult;
import com.shopmall.bawei.pay.qqb.PayResult;
import com.shopmall.bawei.pay.qqb.util.OrderInfoUtil2_0;

import java.util.List;
import java.util.Map;

@Route(path = "/order/OrderActivity")
public class OrderActivity extends BaseActivity {
    private TextView nameOrder;
    private TextView phoneOrder;
    private TextView adressOrder;
    private RecyclerView rvOrder;
    private TextView priceOrder;
    private RelativeLayout llGoodsRoot;
    private TextView pricePay;
    private Button buyOrder;
    private String key;
    private OrderAdapter adapter;



























    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2016102100732196";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCetpoOv2BedP/Iv1ynicEQ3Bpl3Rmd1Q8EeNs3ljNCR1fQURYYasgRUJPohzsGVlLtPJsPd+jTNqqImsbY/GPuuPgifDQ+e5PnPWZrngm9Ru91wTqi5SIq/BkpN1nF5HIrHs5rRzyBcKcX82fkPBd5N2KNovD+8Biz+pdWuHVOjbCZ7MVCc/LfpwMI9QwZXMrEsm4PvqsnnnlNB3l3gqb110zgqZLX9hckP42+fikYNGxKLFF4zYZ0DRLkGjtLCse2KXgX3Tr4+/BQpxnyGhZMtgFoO9JXQGo0UER9D2FGc7LZCWschyGETLeJwWKIjZXIZD57gfPCK37A+7R7vejnAgMBAAECggEAbNSToSczorGhr3sgwrVgEPqMk2rgJO0zBgMFdwFklr8rBOqFNysJk23obltEax0IcirLvPihSyvCFjfjwGiY8doeNC9s96dvjPH6aDMPRJ3+l4VvesGaA1Wovy14Po1eiBjwvHk1kSC5Q2AhzkwyYGlNCAhCLt5eYhOkcM+9iKXlkISUSdA+t1ER9k5BeM10XnsZX/gl1yYW6fnxQ/XKsfeLmbLRhFbBHd6UwqcBWZqKN8lCh1h6JOF0jLuVByMMJzyXf8yKeQY2iNmUCvJbb/7bePvYoMVKrQzlyHonnM4V7mCxiyOb6740SILJes5N9g/Fu09V/Tfv+hJRWfYZYQKBgQDKvXfkYCrAv4amHDwmaBY57laHAF0LE6tQy8OMuT+BzzAJBx/j3uxJquAuAnHZQS9GR93W5li7fUKRtviM4eBrPjh/lJXAxKJn3JAcqpcXQ0f3DC1pAtOzGjgywqcPuNTu+NygUpPkjtPFBbFynElK2ruEnhBgrH39ARqbGx3WlwKBgQDIaEX6t0As0CQ6uc4BI2+niehmH2LG8ELO0S8UWjCJSB0oq3p0iTws190soQHzITsBWADvSs3hHEP/3LY13FhGNF0lLAkO3CsBcA/IpTaF0oS+lwI6hd2zA/iHLzeD44EGTpJc+HZMuZ+GTf7NRdMNFAKbvDQbPe4w1EEf3reaMQKBgQCaT5/jiXbBAoYgBLmbmfng2hGt647mEXCBrLYIdC9sRCCRnoSdUl2SrKa5Hk89RyoOWkD1gpnjCrISaqu/v2Sq+87Q/G0HLiNW3kAqMYWSxTkPRouBtA8h8UD5EcNKaipYQb7boD7E5hk1iuHHFEGM4fN8OzrH+kJiweZYTElnvQKBgF8Iw5ae67nUgjmu/reffEUwqpoy6/521NeKbw7xre6L2ff9STaWFYkWXHXbbDdFXNvIRbkz+el0I/LjUSy9bsbr8fe8qBb55RLrdzCo1/Ah4n0W0yG5dWZ8zZAdne/XJMo+3D1mPYMoyzM/LUNehzS+dnYvi24XsipJnRBl5x8hAoGAd0W7wYvjdoy0xsPldZxqco5f00e3Ka3SYWbIkYPr6wvxoyWwy+VJMWzXEDLXIIRNrcKrpu1Y2XVAZjsHp4ZYjZDjYaRTOcTb8IZ1Ps+ODWFypxZ9VdQBztqp59a8k4fqNM62UHKL/slJ2X4YtSdXaJMJO8kcvA9TUbodoPi9hX8=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(OrderActivity.this, "9000", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OrderActivity.this, "不是9000", Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(OrderActivity.this, "9000", Toast.LENGTH_SHORT).show();

                    } else {
                        // 其他状态值则为授权失败
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
        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
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

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

























    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        buyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payV2(v);
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
        String moneyValue = CacheManager.getInstance().getMoneyValue();
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
}