package com.shopmall.bawei.order.payorder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.framework.base.BaseActivity;
import com.example.framework.greendao.MessageBean;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.MSGManager;
import com.example.net.bean.ConfirmServerPayResultBean;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.shoporder.OrderContract;
import com.shopmall.bawei.order.shoporder.OrderPresenterImpl;
import com.shopmall.bawei.pay.AuthResult;
import com.shopmall.bawei.pay.PayResult;
import com.shopmall.bawei.pay.util.OrderInfoUtil2_0;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;
import com.shoppmall.common.adapter.error.ErrorBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

@Route(path = "/order/PayOrderActivity")
public class PayOrderActivity extends BaseActivity<OrderPresenterImpl, OrderContract.IOrderView> implements OrderContract.IOrderView {
    private RecyclerView rvPayorder;
    private PayOrderAdapter adapter;
    private List<FindForPayBean.ResultBean> findForPayList;
    private String moneyValue;
    public static final String APPID = "2016102100732196";
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCetpoOv2BedP/Iv1ynicEQ3Bpl3Rmd1Q8EeNs3ljNCR1fQURYYasgRUJPohzsGVlLtPJsPd+jTNqqImsbY/GPuuPgifDQ+e5PnPWZrngm9Ru91wTqi5SIq/BkpN1nF5HIrHs5rRzyBcKcX82fkPBd5N2KNovD+8Biz+pdWuHVOjbCZ7MVCc/LfpwMI9QwZXMrEsm4PvqsnnnlNB3l3gqb110zgqZLX9hckP42+fikYNGxKLFF4zYZ0DRLkGjtLCse2KXgX3Tr4+/BQpxnyGhZMtgFoO9JXQGo0UER9D2FGc7LZCWschyGETLeJwWKIjZXIZD57gfPCK37A+7R7vejnAgMBAAECggEAbNSToSczorGhr3sgwrVgEPqMk2rgJO0zBgMFdwFklr8rBOqFNysJk23obltEax0IcirLvPihSyvCFjfjwGiY8doeNC9s96dvjPH6aDMPRJ3+l4VvesGaA1Wovy14Po1eiBjwvHk1kSC5Q2AhzkwyYGlNCAhCLt5eYhOkcM+9iKXlkISUSdA+t1ER9k5BeM10XnsZX/gl1yYW6fnxQ/XKsfeLmbLRhFbBHd6UwqcBWZqKN8lCh1h6JOF0jLuVByMMJzyXf8yKeQY2iNmUCvJbb/7bePvYoMVKrQzlyHonnM4V7mCxiyOb6740SILJes5N9g/Fu09V/Tfv+hJRWfYZYQKBgQDKvXfkYCrAv4amHDwmaBY57laHAF0LE6tQy8OMuT+BzzAJBx/j3uxJquAuAnHZQS9GR93W5li7fUKRtviM4eBrPjh/lJXAxKJn3JAcqpcXQ0f3DC1pAtOzGjgywqcPuNTu+NygUpPkjtPFBbFynElK2ruEnhBgrH39ARqbGx3WlwKBgQDIaEX6t0As0CQ6uc4BI2+niehmH2LG8ELO0S8UWjCJSB0oq3p0iTws190soQHzITsBWADvSs3hHEP/3LY13FhGNF0lLAkO3CsBcA/IpTaF0oS+lwI6hd2zA/iHLzeD44EGTpJc+HZMuZ+GTf7NRdMNFAKbvDQbPe4w1EEf3reaMQKBgQCaT5/jiXbBAoYgBLmbmfng2hGt647mEXCBrLYIdC9sRCCRnoSdUl2SrKa5Hk89RyoOWkD1gpnjCrISaqu/v2Sq+87Q/G0HLiNW3kAqMYWSxTkPRouBtA8h8UD5EcNKaipYQb7boD7E5hk1iuHHFEGM4fN8OzrH+kJiweZYTElnvQKBgF8Iw5ae67nUgjmu/reffEUwqpoy6/521NeKbw7xre6L2ff9STaWFYkWXHXbbDdFXNvIRbkz+el0I/LjUSy9bsbr8fe8qBb55RLrdzCo1/Ah4n0W0yG5dWZ8zZAdne/XJMo+3D1mPYMoyzM/LUNehzS+dnYvi24XsipJnRBl5x8hAoGAd0W7wYvjdoy0xsPldZxqco5f00e3Ka3SYWbIkYPr6wvxoyWwy+VJMWzXEDLXIIRNrcKrpu1Y2XVAZjsHp4ZYjZDjYaRTOcTb8IZ1Ps+ODWFypxZ9VdQBztqp59a8k4fqNM62UHKL/slJ2X4YtSdXaJMJO8kcvA9TUbodoPi9hX8=";
    public static final String RSA_PRIVATE = "";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private String outTradeNo;
    @Override
    protected void initPresenter() {
        presenter=new OrderPresenterImpl();
    }


    @Override
    public void onLeftClick() {
        super.onLeftClick();
        ARouter.getInstance().build("/main/MainActivity").navigation();
    }

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setRecyclerViewListener(new BaseRvAdapter.IBaseRecyclerViewListener() {
            @Override
            public void onItemClick(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PayOrderActivity.this);
                builder.setMessage("确认支付该订单");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FindForPayBean.ResultBean resultBean = findForPayList.get(position);
                        outTradeNo=resultBean.getTradeNo();
                        OrderInfoUtil2_0.setmoney(resultBean.getTotalPrice());
                        payV2(new View(PayOrderActivity.this),resultBean.getOrderInfo()+"");
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void initData() {
        findForPayList = CacheManager.getInstance().getFindForPayList();
        adapter.updataData(findForPayList);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_pay_order;
    }

    @Override
    protected void initView() {
        rvPayorder = (RecyclerView) findViewById(R.id.rv_payorder);
        rvPayorder.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PayOrderAdapter();
        rvPayorder.setAdapter(adapter);
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
                    MessageBean messageBean = new MessageBean();
                    messageBean.setIsNew(true);
                    String payMsg="";
                    if (TextUtils.equals(resultStatus, "9000")) {
                        presenter.confirmServerPayResult(outTradeNo,payResult,true);
                        payMsg="支付成功";
                        Toast.makeText(PayOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        FindForPayBean.ResultBean bean = new FindForPayBean.ResultBean();
                        for (FindForPayBean.ResultBean resultBean : findForPayList) {
                            if(resultBean.getTradeNo().equals(outTradeNo)){
                                bean=resultBean;
                            }
                        }
                        FindForSendBean.ResultBean resultBean = new FindForSendBean.ResultBean();
                        resultBean.setTime(bean.getTime());
                        resultBean.setTotalPrice(bean.getTotalPrice());
                        resultBean.setTradeNo(bean.getTradeNo());
                        CacheManager.getInstance().getFindForSendList().add(resultBean);
                        findForPayList.remove(bean);
                        adapter.updataData(findForPayList);
                    } else {
                        payMsg="支付失败";
                        presenter.confirmServerPayResult(outTradeNo,payResult,false);
                        Toast.makeText(PayOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    messageBean.setTitle("支付消息:");
                    messageBean.setMsg(payMsg);
                    messageBean.setTime(System.currentTimeMillis());
                    MSGManager.getInstance().addMessage(messageBean, new MSGManager.IMessageListener() {
                        @Override
                        public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                            EventBus.getDefault().post("payBack");
                        }
                    });
                    break;
                }
                case SDK_AUTH_FLAG: {
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {

                    } else {

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    public void payV2(View v, final String orderInfo) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            Toast.makeText(PayOrderActivity.this, "1352132123123", Toast.LENGTH_SHORT).show();

            return;
        }
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayOrderActivity.this);
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
    public void onConfirmServerPayResultOk(ConfirmServerPayResultBean bean) {

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
}