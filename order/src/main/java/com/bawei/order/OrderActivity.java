package com.bawei.order;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
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
import com.bawei.common.view.ErrorBean;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.CacheManager;
import com.bawei.framework.MessageManager;
import com.bawei.framework.ShopUserManager;
import com.bawei.framework.greendao.MessageBean;
import com.bawei.net.mode.OrderInfoBean;
import com.bawei.order.adapter.OderGoodsAdapter;
import com.bawei.order.contact.OrderContact;
import com.bawei.order.contact.OrderContactImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

//预支付订单页面
@Route(path = "/order/OrderActivity")
public class OrderActivity extends BaseActivity<OrderContactImpl, OrderContact.IOrderView> implements View.OnClickListener, OrderContact.IOrderView {

    private TextView tvName;
    private TextView tvPhone;
    private TextView tvAddress;
    private RecyclerView rvOrder;
    private TextView tvPrice;
    private TextView tvBottomPrice;
    private Button btBuy;
    private RelativeLayout llGoodsRoot;

    private OderGoodsAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {
        tvName = (TextView) findViewById(R.id.tv_name);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        rvOrder = (RecyclerView) findViewById(R.id.rv_order);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvBottomPrice = (TextView) findViewById(R.id.tv_bottom_price);
        btBuy = (Button) findViewById(R.id.bt_buy);
        llGoodsRoot = (RelativeLayout) findViewById(R.id.ll_goods_root);

        btBuy.setOnClickListener(this);

        tvName.setText(ShopUserManager.getInstance().getName());
        tvPhone.setText(ShopUserManager.getInstance().getPhone());
        tvAddress.setText(ShopUserManager.getInstance().getAddress());
        tvPrice.setText(CacheManager.getInstance().getMoneyValue());
        tvBottomPrice.setText(CacheManager.getInstance().getMoneyValue());

        adapter = new OderGoodsAdapter(R.layout.item_order, CacheManager.getInstance().getSelectedProductBeanList());
        rvOrder.setAdapter(adapter);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new OrderContactImpl();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_buy) {
            httpPresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
        }
    }

    @Override
    public void onOrderInfo(final OrderInfoBean orderInfoBean) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(OrderActivity.this);
                Map<String, String> stringStringMap = payTask.payV2(orderInfoBean.getOrderInfo(), true);
                if (stringStringMap.get("resultStatus").equals("9000")) {
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendEmptyMessage(2);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(OrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    savePayMessage("支付成功");
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    break;
                case 2:
                    Toast.makeText(OrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    savePayMessage("支付失败");
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    break;
            }
        }
    };

    private void savePayMessage(String message) {
        final MessageBean messageBean = new MessageBean();
        messageBean.setTime(System.currentTimeMillis());
        messageBean.setBody(message);
        messageBean.setIsRead(false);
        messageBean.setTitle("支付消息");
        messageBean.setType(MessageManager.PAY_TYPE);
        MessageManager.getInstance().addMessage(messageBean, new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<MessageBean> messageManagerList) {
                Toast.makeText(OrderActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(messageBean);
            }
        });

    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }
}
