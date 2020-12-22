package com.shopmall.bawei.order;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.shopmall.bawei.common.ARouterHelper;
import com.shopmall.bawei.common.FragmentHelper;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.net.mode.OrderInfoBean;
import com.shopmall.bawei.net.mode.ShopCarBean;
import com.shopmall.bawei.order.contract.OrderContract;
import com.shopmall.bawei.order.contract.OrderImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Route(path = ARouterHelper.ORDER_ACTIVITY)
public class OrderActivity extends BaseActivity<OrderImpl, OrderContract.IOrderView> implements OrderContract.IOrderView {
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvAdress;
    private RecyclerView rvOrder;
    private TextView tvPrice;
    private TextView tvPriceJie;
    private Button btBuy;

    private OrderRvAdapter adapter;


    @Override
    protected int layoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        tvName = (TextView) findViewById(R.id.tv_name);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvAdress = (TextView) findViewById(R.id.tv_adress);
        rvOrder = (RecyclerView) findViewById(R.id.rv_order);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvPriceJie = (TextView) findViewById(R.id.tv_price_jie);
        btBuy = (Button) findViewById(R.id.bt_buy);
        adapter = new OrderRvAdapter();
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        rvOrder.setAdapter(adapter);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        tvName.setText(UserManager.getInstance().getName());
        tvPhone.setText(UserManager.getInstance().getPhone());
        tvAdress.setText(UserManager.getInstance().getAddress());
        tvPrice.setText("￥"+CacheManager.getInstance().getMoneyValue());
        tvPriceJie.setText("￥"+CacheManager.getInstance().getMoneyValue());
    }

    @Override
    protected void initData() {
        adapter.updateData(CacheManager.getInstance().getShopCarBeanList());
    }

    @Override
    protected void initListener() {
        btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpPresenter.getOrderInfo(CacheManager.getInstance().getShopCarBeanList());
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new OrderImpl();
    }

    @Override
    public void onOrderInfo(final OrderInfoBean orderInfoBean) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(OrderActivity.this);
                Map<String,String> resultMap = payTask.payV2(orderInfoBean.getOrderInfo(),true);
                if(resultMap.get("resultStatus").equals("9000")){
                    httpPresenter.confirmServerPayResult(orderInfoBean,true);
                    handler.sendEmptyMessage(1);
                } else {
                    httpPresenter.confirmServerPayResult(orderInfoBean,false);
                    handler.sendEmptyMessage(2);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    @Override
    public void onConfirmServerPayResult(boolean payResult) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoaDing() {
        showLoading();
    }

    @Override
    public void showEmpty() {

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(OrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build(ARouterHelper.APP_MAIN).withInt("index",FragmentHelper.ORDER_INDEX).navigation();
                    break;
                case 2:
                    Toast.makeText(OrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build(ARouterHelper.APP_MAIN).withInt("index",FragmentHelper.ORDER_INDEX).navigation();
                    break;
            }

        }
    };
}
