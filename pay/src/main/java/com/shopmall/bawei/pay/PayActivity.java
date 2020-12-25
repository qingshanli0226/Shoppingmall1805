package com.shopmall.bawei.pay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.shopmall.bawei.pay.zfb.AuthResult;
import com.shopmall.bawei.pay.zfb.PayResult;
import com.shopmall.bawei.pay.zfb.util.OrderInfoUtil2_0;
import com.shopmall.framework.base.BaseMVPActivity;
import com.shopmall.net.bean.OrderBean;
import com.shopmall.net.share.RestName;

import java.util.Map;

@Route(path = "/pay/PayActivity")
public class PayActivity extends BaseMVPActivity {

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000117683490";

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
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCz8O6ky0TW4he8XEsm0d5bEVzvkiqPkNHYj4lwSU2kZd7nVAg9tN/Jk6p8RuTeD2xnNCVU8KAeRdKEQJkBvQhmBpS+IgdYCdyQWz/7a7teNHqPZtR5PzwP9Ht6CYWA1+wRdsouYW4rmJpZeDod/v2IaalYUbFWPjlTpm2hTH4uzfD5M78vrpfj00W54IxssleBR0HZp7LpXFSbS5R9kJV4Y8tBZYidDk6kD7tTxzk0vNjtXyDFglivbN64ckvfoT52J06g10L0ftwzgZfaCRVZRWB5Nbvurk8jAQs/f9PzY3k2uqMl3zl/gWIc3NhjlT8CLwfgQly1Ub4IjJWk4TRAgMBAAECggEBAKWOSWkykDkGLbN5/lMJN0miULeXO0uqlCSDAgu1w7lHK3Q68ZDCEmTZGjrkEypVIiDiAoUU5JOn1Ln2EJEPM8Ok7qIaYhqMz5kSrt4kkOdxKPunFWsYbjiCFBbZlhxZC6XTB/fpChIz6KBnmyYUaYT3fhbHv4wRG5xMGud49EnK2ugffe5qBJ0QJJLXKqjtURnTlnlUGASkhaINyvi2JX69mgyUkVs7Od9eAPItXJ0Pn5yTPl4Z3vYiklTWdV0ywtG2EPvgTZNCX1CkKaJWT9mlCPqRih4BS5wcer9tirs36c9/AD4etQauVbgqi9Vs5Vptx+SsUZC8748nFsji8BECgYEA+uhBOB6B96rPCLIBbVU0/x0X69a1fG6zi26pZerLsw1OGnDYzC26fCEEJyQ36F2f3flrqBDkXwozWfZRZJZw25e3E/waBcOb9BZsKlS75FaMJmz1+jTSSuL30f35jDq3Xx0Rs2Towoez7jwCNeeV3r+MFLKzTqVefNwoxQA0ia0CgYEAxsQHngejTrpM0vZzmFe6LHTV+C0SaFdAg797ocyxP7gginF9IXk5oCYG9tByux1+XIax+OFyGSz9uAA25h5a1OL9H9Qq4wC1bkudwTk/wkE+a8M83G3IMEqMTMnViF/RTM15AiS4/u6Vovpr/bEDslaP4lX2fEWlOxSq5ZXnlDUCgYEAnO7t9Aro4Z7ddaPYzqivyC1LTAB0bUrUVA64N33fQLO+uT0B7NthO8huz4iQy27b0jvyQSGnpWY87jTpfc+28TJ4mTBiml5ZyhbIOsXa65vSdypJw+CL87j4pkiDcFk9XmlRK/KevyWfvg+clliuXa9TD5MiIbu/WLtfaLCTK9UCgYAhs4s3GERwTNZJzs+6vOUsNIMzGTFNz714mTb1MCe9jhK2Zpe43T0QEmKL5rwNTerm9U+ny1H1QtOiJycnzW3JOtOAWhELfEtG8DtHT9WnsjhLPUdMeLDAv2Magf5aqSRryUsDOgbD8uAcSimVUjY2PscWsUyhtY1WfyKa2tfxIQKBgGC8zWfuLQDzdjijQhLm8fNi8HeFMvjkKCXNIMuI+Fm+8pJnARrXiTBtza50mpMqw7SMcGkCly13YYfP/WTBRgdqyoQRL/8gmfvuSTYF6qrPJXWuEzXipUDhORNh5EBVHBko7vpYbsOQoAucndIzhJEHFWVaTtdKEPzt+dzxAQjL";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private OrderBean orderBean;

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
                        showAlert(PayActivity.this, getString(R.string.pay_success) + payResult);

                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。

                        finish();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    if (TextUtils.equals(resultStatus,"9000")&&TextUtils.equals(authResult.getResultCode(),"200")){
                        showAlert(PayActivity.this,getString(R.string.auth_success)+authResult);
                    }else {
                        showAlert(PayActivity.this,getString(R.string.auth_failed)+authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        orderBean = RestName.orderBean;
        OrderInfoUtil2_0.setMoney(RestName.money);
        payV2();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(this,getString(R.string.error_missing_appid_rsa_private));
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
                PayTask alipay = new PayTask(PayActivity.this);
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

    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    @SuppressLint("NewApi")
    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }
}