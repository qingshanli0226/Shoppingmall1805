package com.bw.pay;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bw.framework.BaseActivity;
import com.bw.framework.CacheManager;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.framework.MessageManager;
import com.bw.framework.OrderManager;
import com.bw.framework.dao.ShopcarMessage;
import com.bw.net.ForPayBean;
import com.bw.net.ForSendBean;
import com.bw.net.InventoryBean;
import com.bw.net.OrderInfoBean;
import com.bw.net.bean.Bean;
import com.bw.net.bean.ShopCarBean;
import com.bw.pay.contract.AddressContract;
import com.bw.pay.presenter.AddressPresenter;
import com.shopmall.bawei.pay.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Route(path = "/pay/payActivity")
public class PayActivity extends BaseActivity<AddressPresenter, AddressContract.AddressView> implements AddressContract.AddressView {

    private ImageButton btnBack;
    private TextView orderPrice;
    private Button btnPay;
    private Bean bean ;
    private boolean flag;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){

                Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                CacheManager.getInstance().removeSelectedProducts();
                httpPresenter.confirmServerPayResult(bean);
                //支付成功，发送消息
                savePayMessage("支付成功");
                finish();

            }else {
                Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                CacheManager.getInstance().removeSelectedProducts();
                savePayMessage("支付失败");
                finish();
            }
        }
    };

    private void savePayMessage(String message) {
        ShopcarMessage shopcarMessage = new ShopcarMessage();
        shopcarMessage.setBody(message);
        shopcarMessage.setType(MessageManager.PAY_TYPE);
        shopcarMessage.setIsRead(false);
        shopcarMessage.setTime(System.currentTimeMillis());
        shopcarMessage.setTitle("支付消息");

        MessageManager.getInstance().addMessage(shopcarMessage, new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
        orderPrice = (TextView) findViewById(R.id.order_price);
        btnPay = (Button) findViewById(R.id.btnPay);



        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                boolean flag = intent.getBooleanExtra("flag", true);
                if (flag) {
                    orderPrice.setText(CacheManager.getInstance().getMoneyValues());
                    checkInventory();
                } else {
                    ForPayBean forPayBean = (ForPayBean) intent.getSerializableExtra("forPayBean");
                    OrderInfoBean orderInfoBean = (OrderInfoBean) forPayBean.getOrderInfo();
                    orderPrice.setText(forPayBean.getTotalPrice());
                    pay(orderInfoBean);
                }
            }
        });




        //沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.

    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new AddressPresenter();
    }

    private void checkInventory() {
        httpPresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onUpdateNumberOk(String result) {

    }

    @Override
    public void onUpdateAddressOk(String result) {

    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBeans) {
        if (checkInventoryIsEnough(inventoryBeans)){
            httpPresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
        }else {
            Toast.makeText(this, inventoryBeans.get(0).getProductName()+"库存不充足", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<ShopCarBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for(InventoryBean inventoryBean:inventoryBeans) {
            for(ShopCarBean shopcarBean:shopcarBeanList) {
                if (inventoryBean.getProductId().equals(shopcarBean.getProductId())) {
                    int inventoryNum = Integer.parseInt(inventoryBean.getProductNum());
                    int needNum = Integer.parseInt(inventoryBean.getProductNum());
                    if (needNum > inventoryNum) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void onOrderInfo(final OrderInfoBean orderInfoBean) {

        pay(orderInfoBean);

    }

    private void pay(final OrderInfoBean orderInfoBean) {
        bean = new Bean();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(PayActivity.this);
                Map<String, String> resultMap = payTask.payV2(orderInfoBean.getOrderInfo(), true);


                if (resultMap.get("resultStatus").equals("9000")){

                    ForSendBean forSendBean = new ForSendBean();
                    forSendBean.setBody(orderInfoBean.getOrderInfo());
                    forSendBean.setTime(new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
                    forSendBean.setTradeNo(orderInfoBean.getOutTradeNo());

                    OrderManager.getInstance().addForSendBean(forSendBean);

                    bean.setOutTradeNo(orderInfoBean.getOutTradeNo());
                    bean.setResult(orderInfoBean.getOrderInfo());
                    bean.setClientPayResult(true);

                    httpPresenter.confirmServerPayResult(bean);
                    handler.sendEmptyMessage(1);
                }else {

                    List<ForPayBean> forPayBeanList = OrderManager.getInstance().getForPayBeanList();

                    boolean flag = true;
                    for (ForPayBean forPayBean : forPayBeanList) {
                        if (orderInfoBean.getOutTradeNo().equals(forPayBean.getTradeNo())){
                            flag =false;
                        }
                    }


                    if (flag){
                        ForPayBean forPayBean = new ForPayBean();
                        forPayBean.setTradeNo(orderInfoBean.getOutTradeNo());
                        forPayBean.setOrderInfo(orderInfoBean);
                        forPayBean.setTime(new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
                        forPayBean.setStatus(resultMap.get("resultStatus"));
                        OrderManager.getInstance().addForPayBean(forPayBean);
                    }

                    handler.sendEmptyMessage(2);
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void onConfirmServerPayResult(String result) {
        Log.i("---", "onConfirmServerPayResult: "+result);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showsLoaing() {

    }

    @Override
    public void hidesLoading(boolean isSuccess) {

    }

    @Override
    public void showEmpty() {

    }
}
