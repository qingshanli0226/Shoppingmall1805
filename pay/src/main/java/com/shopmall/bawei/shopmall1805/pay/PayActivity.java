package com.shopmall.bawei.shopmall1805.pay;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.shopmall.bawei.pay.R;
import com.shopmall.bawei.shopmall1805.common.ARouterUtils;
import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.framework.BaseActivity;
import com.shopmall.bawei.shopmall1805.framework.service.CacheManager;
import com.shopmall.bawei.shopmall1805.framework.view.Toolbar;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;
import com.shopmall.bawei.shopmall1805.pay.adapter.PayAdapter;
import com.shopmall.bawei.shopmall1805.pay.zf.PayResult;
import com.shopmall.bawei.shopmall1805.pay.zf.util.OrderInfoUtil2_0;

import java.util.List;
import java.util.Map;


@Route(path = ARouterUtils.PAY_INTERFACE)
public class PayActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView tvAllPrice;
    private Button btGoZhi;
    private  String price;
    private TextView payNameTv;
    private TextView payPhoneTv;
    private TextView payAddressTv;
    private String moneyValue;
    private PayAdapter payAdapter;
    private RecyclerView rvPay;
    public static final String APPID = "2016102100732202";
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
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCURbgV/FJPEXETJc/DQ7mCDSxoUiiPCZGZOhSLU4neP4ZABKGIVSavzUnVb2s4D4JX/9jJKLZacGpD89Sw42VedlyMex0XoohmyBTyiV6L1Rx3xtF2GnyJunh5OkFFmMz283V24Lo1YCyNb3fK9w5KfqHAxAL/f9MgIxKl2rdwubnUnjk6ZyAgtbIK3iXpmMfbEHcLGGZoOOBwkO1FBtM1YP+ublGRBbnOo5ANXLmpVNRyr8r8GQwOqASTdzZIkzqFD9fncgaFEzTQ+Y936Vhp9vuJXzthFS5rcUCHq+AxvGLgZMzixbVya0XKaJKRyqt/tSBelTSi92cpbdRpf9WhAgMBAAECggEBAIC+0Qt2Hto9INwpdu/sPIYqfibPQByDiINuJSydixcKPb4Ux1Yj6gW40aWcq5LwFJPougphaYW6GuuKK2K08O5HBDGPLUG6hYoJrfazRP9SOvfeET7l3MGSws0wwkB2X3MHDzX8CHQUYFsnMPNx9lzE/BrvmFLWYTKEgcq10Zb0u1oKPSRTkzG0Ob/vjZdO3+l6Q3NDS3Bary+8jqadx2oWrKyEOdQ2UC67J30u+No0psXK0EYq0P1NLANWSc/xInU9B/+0nNG9pBbjEu7chQ3yNKnDkObGfePhlAigo99xQ+DEjuK5PZE9npW7h+5Hsjn03QVUOOb8Q22bh+VPkgECgYEAyV/zHC1TXws6Ehk9V965OA9oOgoy/eJpGj31gaTxjYxmxkkFDpkSKWjIS0MymxpbN34uxSrvGyzQ/KR8ILEEaiw62DE8BCnPdmTXYyxiEFD3URmbMYaTMfwTK1jTO7/G/UopJPFxnhcN3pC8UQy2sjcioN6YWq86OjKN0nci53ECgYEAvH4uLkT0xQvEuKQFR554x7ZOScXVpFH+4tfaQXoKZO0fXt+eDBL4O1wY8bUJbEx/DC16Cl/PnvPJ8Wk3eBD9dfVg1EmaFXAsGYbQcUJz3/X8C84w+9WP3avuQXnKalcbBH80/ZdWRGwTlMpd6LVZpYLg+h+KRRGGFVaJ9/3vmTECgYEAwd5D+N6SIzB1ybLO4eekvZnnp+jlypLMyNzZnOSlQLbowwDNg45Oc9tS6VJaSASJyNUMRfndlHYDBRd+ASMqsuS+CiDsPL3b44mV6v5xsm+gik1yoa2DzF14PwUW57Dr7DIXhl+EGMTtL1Z8VjM9ODnodqOWXcfQ8PJ2oybbTaECgYBgqq1wWTjWXSS0NJnokuMKOPqJZwYb0VlQaLar4bS28cym0TOyqX/YoXDBdMCyY8C8NA5kh6JouEoWnokt8DisZ/0SofR9jhtvs+3uR9A5SWPLgiEo5et0GZqExbsDMkzOz5YTf6tkR7D7G5VxpJ+m/Cn43HgC1+vVwZ3cKsTX4QKBgHDYaSxrNY3SKjf6MI/EfEtCIBK+VgZDLhl9eBOTy26SPjS9fTA17fvfr2FHPJiljX/6jqcpT4Aq0a4A0SiMRtpyvatIWKFVnmf2t94F7odGhYeWKDrRqceDulMNEicstwttAoJ/fGNxcARKnDjETv9AdTWpFEq77s4B9WPKOwK7";
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
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        handler.sendEmptyMessage(1);
                    } else {
                        handler.sendEmptyMessage(2);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build(ARouterUtils.PAY_SUCCEED).navigation();
                case 2:
                    Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build(ARouterUtils.PAY_SUCCEED).navigation();
                    break;
            }
        }
    };
    @Override
    protected void initData() {
        initGetMessager();
        initSetItemData();
    }
    private void initSetItemData() {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        if(shopcarBeanList!=null){
            payAdapter=new PayAdapter(R.layout.pay_item,shopcarBeanList);
            rvPay.setAdapter(payAdapter);
            payAdapter.notifyDataSetChanged();
        }
        moneyValue = CacheManager.getInstance().getMoneyValue();
        if(moneyValue!=null){
            Toast.makeText(this, ""+moneyValue, Toast.LENGTH_SHORT).show();
            tvAllPrice.setText(ShopmallConstant.PAY_ALL_PRICE+moneyValue);
        }
    }
    private void initGetMessager() {
        Object phone = CacheManager.getInstance().getPhone();
        Object address = CacheManager.getInstance().getAddress();
        Object name = CacheManager.getInstance().getName();

        if(name!=null){
            payNameTv.setText(ShopmallConstant.PAY_NAME +name.toString());
        }else {
            payNameTv.setText(ShopmallConstant.PAY_NO_ADDITION);
        }
        if(phone!=null){
            payPhoneTv.setText(ShopmallConstant.PAY_PHONE+phone.toString());
        }else {
            payPhoneTv.setText(ShopmallConstant.PAY_NO_ADDITION);
        }
        if(address!=null){
            payAddressTv.setText(ShopmallConstant.PAY_ADDRESS+phone.toString());
        }else {
            payAddressTv.setText(ShopmallConstant.PAY_NO_ADDITION);
        }

        btGoZhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payV2(moneyValue);
            }
        });
    }
    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        tvAllPrice = findViewById(R.id.tv_all_price);
        btGoZhi = findViewById(R.id.bt_go_zhi);
        payNameTv = findViewById(R.id.pay_name_tv);
        payPhoneTv = findViewById(R.id.pay_phone_tv);
        payAddressTv = findViewById(R.id.pay_address_tv);
        rvPay = findViewById(R.id.rv_pay);
        rvPay.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }
    /**
     * 支付宝支付业务示例
     */
    public void payV2(String allMoney) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
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
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2,allMoney);
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
}