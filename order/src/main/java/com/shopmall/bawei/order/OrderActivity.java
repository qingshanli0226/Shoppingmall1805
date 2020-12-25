package com.shopmall.bawei.order;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.DaoSession;
import com.example.framework.MessageManager;
import com.example.framework.ShopcarMessage;
import com.example.net.IntonVoryBean;
import com.example.net.OrderInfoBean;
import com.example.net.ShopcarBean;
import com.shopmall.bawei.order.adapter.OrderAdapter;
import com.shopmall.bawei.order.contract.TakeContract;
import com.shopmall.bawei.order.presenter.TakePresenterImpl;
import com.shopmall.bawei.order.util.OrderInfoUtil2_0;

import java.util.List;
import java.util.Map;

@Route(path = "/me/order")
public class OrderActivity extends BaseActivity<TakePresenterImpl,TakeContract.ITakeView> implements TakeContract.ITakeView {
    private TextView tvPhone;
    private TextView tvAddress;
    private ImageButton ibShopcartBack;
    private TextView tvName;
    private RecyclerView rvOrder;
    private TextView tvPrice;
    private RelativeLayout llGoodsRoot;
    private TextView tvPriceJie;
    private Button btBuy;
    private OrderAdapter orderAdapter;
    ShopcarMessage shopcarMessage = new ShopcarMessage();
    private OrderInfoBean orderInfoBean = new OrderInfoBean();


    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2016102100732173";

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
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCqGqhhiLvMuMdXhfLu2amrXyFzQLv9O18Xe0mWxA5Ivb6cGylFyffHsNw8/MlwsvkV9yWGQB/+H/wIkyK+pNG/sj+9Tb2AKRpnvbBk5bvm4xEHgIHYRh4XAjIHKbdXDOMxVdinW1YtqJUP2KOM9diHoY8102mSdvhp9mV7wIM4DA03Nzp1Uh9eOx01H8ZURcO+joD0erfGaAdH/zQBUaQzhrtyxVmTNXXK9ctK0T0mLHl5cnW112HzC6SRin+gV8Bq8Iarr15UQErU4pp7D0iB+vzduGZc/TtWIPET329FQRTr+TERS4seBTsoBN5yQ6scM35t6ZBASTWBL9sqAsPHAgMBAAECggEATldeqyTVETEcpcOMIdLfIIz8k98BdVwtrFvchXIf/gJdUfn0ZS7iTfa3R+NIRv42V5ZuksjfsyM56R0SiGjSLQ5D6sRt/SNxBMxI+/8OLgTvp9vYcSnB2+jn74KX7KCD1gnqcRFQvFCC7jxg/p+PLNAMa8bFPW5LdEnFbVw5UssB0WmI8Pm//uYtFkKl8dwXx6vrbvuKwNkHIOJO15VKqsZAA6q/HujGvv+ulWV79Kr01fP4aFnATJq30vqboxne91nzm/ztOr4RZIilxxcBzzNpUPQl7QV4mZrb3PqHI+1q41Wu0NIWKXabYUrRJWuDSPBeKEpccRjte0ac/eY3qQKBgQDXEkktjZAgufHCCsWEDQnDjd1j+6Gl6idIIyCc8AGbaHsNmvvOmcHoQReR3XajeG9XPrtmafnnEaVQmoFU3qM6iZC+ioUeblwmZE0mj9kG+66E+sfDHcWcJDoNvQ3WoDtotRNvabtC/cE939LOREXbzn+CFuqSUPy/fPlT3Q+54wKBgQDKea74Q+eK/4e0VlT28usj6H/CBCmfKRKU31vu9pyHGbrm80CxsiFpW6PF0Fm7cU7k5lMpE+KhnOb294P+BMpPxj4CJOjLl7sSxHvAPQkkqPPfp9ya7NCxQpTQ73nEsbS1ftM25DDdKx+9iQKfNhX1mkLKHV4h2+GUWnhTk1HDzQKBgFt65618nWWOMgr7UfBNK24GlbjFe4h7BKJmbqTSYZyc4Fp1Jc5HEHed2EyvCpuVyqYthMCXKzjiBJM4ZPKYkI2BnhOuD9lVz9R0rWxNm16hByaBFR8lNrynnBnj5FL6bMqo2s91BbYFAYE0BxK9b4mDWDEZVkVONXOt0M+fI+VJAoGAMiLXdJo752tGyxzlhRC5sPaNTBmDxOmCtGJqfU2LjNiOM0ngpx+YrmIGt6DT11pDUuLFjNJjt+kGN/kqMeg1wHMyP42sJ1228Pq+OefjL7S2TpdeRO6G7p2yA9qIqG/oc1ioLFtszhczkNcwUwQtg9di2nFTMcckoHuWd/tur7ECgYEAkYZymqy/GtN2f5P88eEvzMGhJLDJkf5DpuDBJEoxWFpWULXv+cfznmCwgUSMrVYwVMqQMmG5FSho9vG0Su30JbQwt1jZ9QGVvxdValIQIxem+AxevijDemALwweD0bzu6KGa3S0kwJgZe99S9+5LHuDjW032XLdn7d0sPHocmQY=";
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
                        Toast.makeText(OrderActivity.this, "成功", Toast.LENGTH_SHORT).show();

                        shopcarMessage.setTitle("支付消息");
                        shopcarMessage.setBody("支付成功");
                        shopcarMessage.setIsRead(false);
                        shopcarMessage.setType(MessageManager.PAY_TYPE);
                        shopcarMessage.setTime(System.currentTimeMillis());
                        httpPresenter.ConfirmServerPayResult(orderInfoBean,true);
                        MessageManager.getInstance().addMessage(shopcarMessage, new MessageManager.IMessageListener() {
                            @Override
                            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
                                Toast.makeText(OrderActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OrderActivity.this, "失败", Toast.LENGTH_SHORT).show();
                        shopcarMessage.setTitle("支付消息");
                        shopcarMessage.setBody("支付失败");
                        shopcarMessage.setIsRead(false);
                        shopcarMessage.setType(MessageManager.ADR_TYPE);
                        shopcarMessage.setTime(System.currentTimeMillis());
                        httpPresenter.ConfirmServerPayResult(orderInfoBean,false);
                        MessageManager.getInstance().addMessage(shopcarMessage, new MessageManager.IMessageListener() {
                            @Override
                            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
                                Toast.makeText(OrderActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                            }
                        });
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
                    } else {
                        // 其他状态值则为授权失败
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };





    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    private void dialogShow() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_more);
        builder.setTitle("设置地址");
        builder.setMessage("请设置收货地址");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(OrderActivity.this, "跳转设置收货地址页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OrderActivity.this, TakeActivity.class));
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(OrderActivity.this, "取消跳转", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    protected void initView() {
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        ibShopcartBack = (ImageButton) findViewById(R.id.ib_shopcart_back);
        tvName = (TextView) findViewById(R.id.tv_name);
        rvOrder = (RecyclerView) findViewById(R.id.rv_order);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        llGoodsRoot = (RelativeLayout) findViewById(R.id.ll_goods_root);
        tvPriceJie = (TextView) findViewById(R.id.tv_price_jie);
        btBuy = (Button) findViewById(R.id.bt_buy);
    }

    @Override
    protected void initData() {
        initView();
        dialogShow();

        httpPresenter = new TakePresenterImpl();
        tvPhone.setText(CacheManager.getInstance().getPhone() + "");
        tvAddress.setText(CacheManager.getInstance().getAddress() + "");
        btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ShopcarBean> selectedShopBeans = CacheManager
                        .getInstance()
                        .getSelectedShopBeans();
                httpPresenter.checkIntonvory(selectedShopBeans);

                payV2(view);
            }
        });

        List<ShopcarBean> selectedShopBeans = CacheManager.getInstance().getSelectedShopBeans();

        orderAdapter = new OrderAdapter(R.layout.item_order, selectedShopBeans);
        rvOrder.setAdapter(orderAdapter);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter.notifyDataSetChanged();

        String moneyValue = CacheManager.getInstance().getMoneyValue();
        tvPrice.setText(moneyValue+"");
        tvPriceJie.setText(moneyValue+"");
        OrderInfoUtil2_0.setTotalMoney(moneyValue);

        ibShopcartBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
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

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void onCheckIntonvory(List<IntonVoryBean> intonVoryBeans) {
        if (checkIntontnvory(intonVoryBeans)){
            //库存足够的时候，发起订单
            httpPresenter.orderinfo(CacheManager.getInstance().getSelectedShopBeans());
        }else {
            Toast.makeText(this, "库存不足", Toast.LENGTH_SHORT).show();
        }
    }
    //检查商品库存
    private boolean checkIntontnvory(List<IntonVoryBean> intonVoryBeans) {
        //获取选中的商品集合
        List<ShopcarBean> selectedShopBeans = CacheManager.getInstance().getSelectedShopBeans();
        for (IntonVoryBean intonVoryBean : intonVoryBeans) {
            for (ShopcarBean selectedShopBean : selectedShopBeans) {
                if (intonVoryBean.getProductId().equals(selectedShopBean.getProductId())){
                    int num = Integer.parseInt(intonVoryBean.getProductNum());
                    int needNum = Integer.parseInt(intonVoryBean.getProductNum());
                    if (needNum>num){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    @Override
    public void getOrderInfo(OrderInfoBean orderInfoBean) {
        orderInfoBean.setOrderInfo(orderInfoBean.getOrderInfo());
        orderInfoBean.setOutTradeNo(orderInfoBean.getOutTradeNo());
        payV2(new View(this));
    }

    @Override
    public void getConfirmServerPayResult(String result) {
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {

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