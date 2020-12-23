package com.shopmall.bawei.pay.view;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.PayBean;
import com.example.framework.dao.ShopcarMessage;
import com.example.framework.view.manager.MessageManager;
import com.example.net.bean.ConfirmBean;
import com.example.net.bean.FindPayBean;
import com.example.net.bean.IntonVoryBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.pay.R;
import com.shopmall.bawei.pay.contract.PayContract;
import com.shopmall.bawei.pay.presenter.PayPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Route(path="/pay/Activity")
public class PayActivity extends BaseActivity<PayPresenter, PayContract.IOrderView> implements PayContract.IOrderView{
    private ImageButton ibShopcartBack;
    private RadioButton btWei;
    private RadioButton btZhi;
    private Button btPay;
    private TextView tvPrice;
    private ConfirmBean confirmBean;
    private List<ShopcarBean> selectedShopBeans = new ArrayList<>();
    private boolean flag;
    private OrderInfoBean orderInfoBean = new OrderInfoBean();
    String moneys;
    @Override
    protected void initpreseter() {
        httpresenter = new PayPresenter();
    }

    @Override
    protected void initdate() {
        String money = CacheManager.getInstance().getMoney();
        moneys = money;
        selectedShopBeans= CacheManager.getInstance().getSelectedShopBeans();
        Log.e("zld11",""+selectedShopBeans.size());
        tvPrice.setText(CacheManager.getInstance().getMoney());
        btPay.setText("微信支付"+CacheManager.getInstance().getMoney());
        btWei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (btWei.isChecked()){
                    btPay.setText("微信支付"+CacheManager.getInstance().getMoney());
                }
            }
        });
        btZhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(btZhi.isChecked()){
                    btPay.setText("支付宝支付"+CacheManager.getInstance().getMoney());
                }
            }
        });
        //点击进行支付
        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btWei.isChecked()){
                    Toast.makeText(PayActivity.this, "该功能未支付，请选择别的支付", Toast.LENGTH_SHORT).show();
                }else if(btZhi.isChecked()){
                    //首先检查选中商品的数量是否满足
                    httpresenter.checkIntonvory(CacheManager.getInstance().getSelectedShopBeans());
                }
            }
        });
    }

    @Override
    protected void initview() {
        //初始化空间控件
        ibShopcartBack = findViewById(R.id.ib_shopcart_back);
        btWei = findViewById(R.id.bt_wei);
        btZhi = findViewById(R.id.bt_zhi);
        btPay = findViewById(R.id.bt_pay);
        tvPrice = findViewById(R.id.tv_price);

        //沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_pay;
    }

    @Override
    public void onCheckIntonvory(List<IntonVoryBean> intonVoryBeans) {
        if (checkIntontnvory(intonVoryBeans)){
            //库存足够的时候，发起订单
            httpresenter.orderinfo(CacheManager.getInstance().getSelectedShopBeans());
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

    PayBean payBean = new PayBean();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post("增加");
                    CacheManager.getInstance().setSelectedPaySucessBeans(selectedShopBeans);
                    savePayMessage("支付成功");
                    //然后跳转的主页面，并且显示HomeFragment
                    payBean.setIndex(0);
                    EventBus.getDefault().post(payBean);
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    httpresenter.ConfirmServerPayResult(orderInfoBean,flag);

                    break;
                case 2:
                    //将支付失败的存到代付款的缓存当中
                    FindPayBean findPayBean = new FindPayBean();
                    findPayBean.setTime(""+System.currentTimeMillis());
                    findPayBean.setTradeNo(orderInfoBean.getOutTradeNo());
                    findPayBean.setOrderInfo(orderInfoBean.getOrderInfo());
                    findPayBean.setTotalPrice(moneys);
                    CacheManager.getInstance().addPayList(findPayBean);
                    Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    CacheManager.getInstance().setSelectedPayEorryBeans(CacheManager.getInstance().getSelectedShopBeans());
                    savePayMessage("支付失败");
                    //然后跳转的主页面，并且显示HomeFragment
                    payBean.setIndex(0);
                    EventBus.getDefault().post(payBean);
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    httpresenter.ConfirmServerPayResult(orderInfoBean,flag);
                    break;
            }
        }
    };

    private void savePayMessage(String message) {
        final ShopcarMessage shopcarMessage = new ShopcarMessage();
        shopcarMessage.setTime(System.currentTimeMillis());
        shopcarMessage.setBody(message);
        shopcarMessage.setIsRead(false);
        shopcarMessage.setTitle("支付消息");
        shopcarMessage.setType(MessageManager.PAY_TYPE);
        MessageManager.getInstance().addMessge(shopcarMessage, new MessageManager.IMessageListenter() {
            @Override
            public void onresult(boolean issucess, List<ShopcarMessage> shopcarMessages) {
                //使用Eventbus发送到
                EventBus.getDefault().post(shopcarMessage);
            }
        });
    }

    @Override
    public void getOrderInfo(final OrderInfoBean orderInfoBeans) {

        orderInfoBean.setOutTradeNo(orderInfoBeans.getOutTradeNo());
        orderInfoBean.setOrderInfo(orderInfoBeans.getOrderInfo());
        //首先将你选中的商品从购物车删除，因为提交订单之后，商品就没了
        CacheManager.getInstance().removeselectshopBean();
        //服务端成功下单
        //使用支付宝完成支付功能
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(PayActivity.this);
                Map<String, String> stringStringMap = payTask.payV2(orderInfoBeans.getOrderInfo(), true);
                if (stringStringMap.get("resultStatus").equals("9000")){//9000代表支付成功
                    //在子线程进行完成
                    handler.sendEmptyMessage(1);
                    flag = true;

                }else {
                    //在子线程进行完成
                    handler.sendEmptyMessage(2);
                   flag = false;
                }




            }
        };
        //开启线程
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void getConfirmServerPayResult(String result) {
        Log.e("###",result);
    }

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {
        showloading();
    }

    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
        showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
    }


}
