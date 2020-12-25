package com.shopmall.bawei.pay.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.greendaobean.MessageBean;
import com.shopmall.bawei.framework.manager.GreendaoManager;
import com.shopmall.bawei.framework.mvptest.presenter.ShopcarPresenter;
import com.shopmall.bawei.framework.notice.Notify;
import com.shopmall.bawei.pay.R;
import com.shopmall.bawei.pay.ui.pay.AuthResult;
import com.shopmall.bawei.pay.ui.pay.PayResult;
import com.shopmall.bawei.pay.ui.pay.util.OrderInfoUtil2_0;
import com.shopmall.bean.OrderBean;
import com.shopmall.restname.RestName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
@Route(path = "/pay/PayMainActivity")
public class PayMainActivity extends BaseActivity<ShopcarPresenter> implements Constant.ShopcarConstartView {

//    @Autowired(name="orderbean")
//    private OrderBean orderBean;


    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000118682560";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "2088621957460583";

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
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPw7zquJTJBEk+GIKe/dDrKgELY2HW8lcwFEFNnRBmBsIw7dqcuXOPi+3+i8aWULbYXfSdWf5ohJ8+iGm0OQ+JlviZBvjY/XSTZs9Z+lsg2Dk9Euni0huLPWn01/MJOtx7Br79v5/HRe/1nj9Dd3YcCPhQF8oOH3FjgnO/qb1Wkdldh1/0G+fYMmMLfYRo1XMGFjkL/i+i3P7oE7XZpuPIiosAwOkQ7LqpBB/F1VvG89d5wDsMSCx/396crttcs2YyUIiPLPCjdl0bNAMfvkBoNYsaqMhFoVSDflScKFuhq2jUQKNfyLxTLDtzbdLfRfmA84PbhEpUwL/VupCP4bEXAgMBAAECggEAVHbDv4PCpneGMJ1l4NNusztycaZqDUdEQlMkKt1kjQzberPEBOBQLL4hjbPBcP7q7iQFW41ME0wX4z1yHRiJMGUqmX2nJpWCsDdvC4YzlBB1UJSUJVccJ6plXhyiBYI3l3BXNe4niYRlfwECWjkNg1shyKyxYarHi5dROgIX44vaZKP4yyWCrgR8qxncX2nxhInpGgm79++yCkvIZ6Ed5AfXEIDh+bXxZYyhCyeqnkTIV9J2emTnRS27aEZEtAc2TQw+G+U/t4fzLahAhpccwMKiZFjT9dlc0Mjs39gb+R8pF7kjoBbXYa7/onRJTGw6j2CIcWJZv/6URb9c0UQgwQKBgQDVIdKlkc+M6shU0Fo7e4KaTxfsb9CnN6Udf5AiHfWdlOzdQWNNyGhRqXJ3KG5muvzPn+68E4TFkOMLnijpxJ4UXzjz6B5/TrXIQZ9fk/71gb3CtC1BfDqriT/IVsnz6CdKE7AMX4QJUFpLzqH5i5pza+edu+JjQ49RGmjKmAP2IwKBgQCsri4fgK4857qDW0YgmeF+dnO0JLIlop39o3ZQo4jQTMof1hPsRfb1jeqnVvmPyb+QQDBlnxBNfsc0nBymKApmfKSC4uzbMvqzBi8Bl5xg0KoUM6pUvpF1sFOFI3Q9lzAsN22RrIgzMZWOuSRHGtO92pImXQwnaRzfhHUFT/6WfQKBgQC8JkquDWWbMhy9+tzdS/CExrQI7sDVDaN+wgD8jVQp07GytSm0HeoxxROeq5uipwnNRJPdhqGcQ4ab1/ioPgGLXHCLqWta2aJScdP+7UiTdQCPJL4a5E9q5RPgiPVpk5UzqOOuWJfYHQ5Jg9Wr1Zj4sdZbgeSd2qNnZpgB2WcN3wKBgCHSK6534aiEKn+3zNBt00CVqqvJ1CqS4ybecTnyDcXehGgf4d8RBQGrPh5wZojHFwIF0HTW5raG8jVXrjTM+tx/5SjNkV6NaZTOzT4el75OoEE8rUvT12GZm4IkI4w8pG7EnG0/wZ1lRMmjcXuLwZUZzi49YzgE2mbc5p6XwGFxAoGAMaf8MM59mb2Rmz8BqpuX7w8Uk8/IMV+NpWymPJX9zCPvEnysxUMtxVXFb0PP+pVcr1QMrG3b6RASUuHOjVQhdTYBLqEnAAqOTBzTwVm7APb2Kbn4MXLD4c/6iWNYS7kGU8xg+s/wD8BxuDBO2/dOrCogLNKJG8fnZKBxiJsrXAE=";
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
                        showAlert(PayMainActivity.this, getString(R.string.pay_success) + payResult);
                        mPresenter.confirmServerPayResult(Constants.CONFIRMSERVERPAYRESULT,true,RestName.OutTradeNo,RestName.OrderInfo);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。

                        mPresenter.confirmServerPayResult(Constants.CONFIRMSERVERPAYRESULT,false,RestName.OutTradeNo,RestName.OrderInfo);
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
                        showAlert(PayMainActivity.this, getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(PayMainActivity.this, getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };


    @Override
    protected void oncreatePresenter() {
       mPresenter=new ShopcarPresenter(this);
    }

    @Override
    protected void initEnvent() {

    }

    @Override
    protected void initview() {

    }

    @Override
    protected void initData() {
        OrderInfoUtil2_0.setMoney(RestName.money);
       Log.e("log", RestName.OutTradeNo+"   订单"+RestName.OrderInfo);
        payV2();
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_pay_main;
    }

    /**
     * 支付宝支付业务示例
     */
    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(this, getString(R.string.error_missing_appid_rsa_private));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//支付宝那句话依赖
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayMainActivity.this);
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

    private String getdate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;

    }

    @Override
    public void Success(Object... objects) {
        boolean isPay=(Boolean) objects[1];
        if (isPay){
            MessageBean messageBean = new MessageBean();
            messageBean.setTitle("支付提示！");
            messageBean.setRead(false);
            messageBean.setMsg("您有一比已支付信息的账单，请查看！");
            messageBean.setDate(getdate());
            messageBean.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608091421071&di=e1d6c83c2c67972a86a4207bef44f88a&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_pic%2F19%2F04%2F04%2F1c71da9c6caac2d9db76db9fff2600ed.jpg");
            GreendaoManager.getInstance().insert(messageBean);

            Notify.setnotify(PayMainActivity.this,1,"您有一比已支付信息的账单，请查看！",R.mipmap.baocuo,"支付提示！");

        }else {
            MessageBean messageBean = new MessageBean();
            messageBean.setTitle("支付提示！");
            messageBean.setRead(false);
            messageBean.setMsg("您有一比未支付信息的账单，请查看！");
            messageBean.setDate(getdate());
            messageBean.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608091421071&di=e1d6c83c2c67972a86a4207bef44f88a&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_pic%2F19%2F04%2F04%2F1c71da9c6caac2d9db76db9fff2600ed.jpg");
            GreendaoManager.getInstance().insert(messageBean);
            Notify.setnotify(PayMainActivity.this,1,"您有一比未支付信息的账单，请查看！",R.mipmap.baocuo,"支付提示！");
            Toast.makeText(PayMainActivity.this, "支付出现异常,请稍候再试！！！", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void Error(String s) {
        Toast.makeText(this, "s", Toast.LENGTH_SHORT).show();
    }
}