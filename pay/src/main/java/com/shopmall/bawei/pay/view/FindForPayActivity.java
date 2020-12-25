package com.shopmall.bawei.pay.view;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.framework.BaseActivity;
import com.example.framework.BaseRVAdapter;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.dao.ShopcarMessage;
import com.example.framework.view.manager.MessageManager;
import com.example.net.FindSendBean;
import com.example.net.bean.ConfirmBean;
import com.example.net.bean.FindPayBean;
import com.example.net.bean.IntonVoryBean;
import com.example.net.bean.OrderInfoBean;
import com.shopmall.bawei.pay.FindPayAdpter;
import com.shopmall.bawei.pay.R;
import com.shopmall.bawei.pay.contract.PayContract;
import com.shopmall.bawei.pay.presenter.PayPresenter;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;


public class FindForPayActivity extends BaseActivity<PayPresenter, PayContract.IOrderView> implements PayContract.IOrderView,CacheManager.IShopcarPayCharListerter {
    private RecyclerView rvPay;
    private FindPayAdpter findPayAdpter;
    private ConfirmBean confirmBean;
    private OrderInfoBean orderInfoBean;
    private FindPayBean findPayBean = new FindPayBean();
    private FindSendBean findSendBean = new FindSendBean();
    private boolean flag;
    @Override
    protected void initpreseter() {
        httpresenter = new PayPresenter();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ongetpay(String s){
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
        CacheManager.getInstance().getfindpay();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(FindForPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    savePayMessage("支付成功");
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    httpresenter.ConfirmServerPayResult(orderInfoBean ,flag);
                    //将缓存中数据删除
                    CacheManager.getInstance().removePay(findPayBean);
                    //往待收货中添加
                    findSendBean.setSubject("buy");
                    findSendBean.setBody("????");
                    findSendBean.setTime(""+System.currentTimeMillis());
                    findSendBean.setTotalPrice(""+findPayBean.getTotalPrice());
                    findSendBean.setOrderInfo(findPayBean.getOrderInfo());
                    findSendBean.setTradeNo(findPayBean.getTradeNo());
                    CacheManager.getInstance().addSendList(findSendBean);
                    break;
                case 2:
                    Toast.makeText(FindForPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    CacheManager.getInstance().setSelectedPayEorryBeans(CacheManager.getInstance().getSelectedShopBeans());
                    savePayMessage("支付失败");
                    //然后跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    httpresenter.ConfirmServerPayResult(orderInfoBean ,flag);

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
    protected void initdate() {
        //注册监听
        CacheManager.getInstance().setshopcarpayListenter(this);
        //沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.
//        CacheManager.getInstance().getfindpay();
        final List<FindPayBean> findPayBeans = CacheManager.getInstance().getfindpayList();
        findPayAdpter.updataData(findPayBeans);
        //点击对未支付订单进行支付
        findPayAdpter.setiRecyclerViewItemClickListener(new BaseRVAdapter.IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                //使用支付宝完成支付功能
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        orderInfoBean =new OrderInfoBean();
                        orderInfoBean.setOrderInfo(findPayBeans.get(position).getOrderInfo());
                        orderInfoBean.setOutTradeNo(findPayBeans.get(position).getTradeNo());
                        PayTask payTask = new PayTask(FindForPayActivity.this);
                        Map<String, String> stringStringMap = payTask.payV2(findPayBeans.get(position).getOrderInfo()+"", true);
                        if (stringStringMap.get("resultStatus").equals("9000")){//9000代表支付成功
                            //在子线程进行完成
                            handler.sendEmptyMessage(1);
                            flag = true;
                            findPayBean = findPayBeans.get(position);
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
        });
    }

    @Override
    protected void initview() {
        //初始化控件
        rvPay = findViewById(R.id.rv_pay);
        findPayAdpter = new FindPayAdpter();
        rvPay.setAdapter(findPayAdpter);
        rvPay.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_find_for_pay;
    }

    @Override
    public void onCheckIntonvory(List<IntonVoryBean> intonVoryBeans) {

    }

    @Override
    public void getOrderInfo(OrderInfoBean orderInfoBean) {

    }

    @Override
    public void getConfirmServerPayResult(String result) {
        Log.e("###",""+result);
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
    //刷新Ui
    @Override
    public void onPayList(List<FindPayBean> shopcarBeanList) {
        findPayAdpter.updataData(shopcarBeanList);
    }

    @Override
    public void onSendList(List<FindSendBean> findSendBeans) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当销毁页面，将监听也去了
        CacheManager.getInstance().unSetShopcarpayChangerListener(this);
    }
}
