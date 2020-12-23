package com.bawei.order;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.PayTask;
import com.bawei.common.view.ErrorBean;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.CacheManager;
import com.bawei.framework.MessageManager;
import com.bawei.framework.greendao.MessageBean;
import com.bawei.net.mode.FindForPayBean;
import com.bawei.net.mode.FindForSendBean;
import com.bawei.net.mode.OrderInfoBean;
import com.bawei.order.adapter.ForPayAdapter;
import com.bawei.order.contact.FindForOrderContact;
import com.bawei.order.contact.FindForOrderContactImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Route(path = "/order/ForPayActivity")
public class ForPayActivity extends BaseActivity<FindForOrderContactImpl, FindForOrderContact.IFindForOrderView> implements FindForOrderContact.IFindForOrderView {

    private RecyclerView forPayRecycler;
    private List<FindForPayBean.ResultBean> resultBeanList = new ArrayList<>();
    private ForPayAdapter forPayAdapter;
    private Thread thread = new Thread();

    @Override
    protected int layoutId() {
        return R.layout.activity_for_pay;
    }

    @Override
    protected void initView() {
        forPayRecycler = findViewById(R.id.for_pay_recycler);
    }

    @Override
    protected void initData() {
        httpPresenter.FindForPay();
        forPayAdapter = new ForPayAdapter(R.layout.item_for_pay, resultBeanList);
        forPayRecycler.setAdapter(forPayAdapter);
        forPayRecycler.setLayoutManager(new LinearLayoutManager(this));

        forPayAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask payTask = new PayTask(ForPayActivity.this);
                        Map<String, String> stringStringMap = payTask.payV2(resultBeanList.get(position).getOrderInfo().toString(), true);
                        OrderInfoBean orderInfoBean = new OrderInfoBean();
                        orderInfoBean.setOrderInfo(resultBeanList.get(position).getOrderInfo().toString());
                        orderInfoBean.setOutTradeNo(resultBeanList.get(position).getTradeNo());
                        if (stringStringMap.get("resultStatus").equals("9000")) {
                            Message message = new Message();
                            message.what = 1;
                            message.obj = orderInfoBean;
                            handler.sendMessage(message);
                        } else {
                            Message message = new Message();
                            message.what = 2;
                            message.obj = orderInfoBean;
                            handler.sendMessage(message);
                        }
                    }
                };
                thread = new Thread(runnable);
                thread.start();
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            OrderInfoBean orderInfoBean = (OrderInfoBean) msg.obj;

            switch (msg.what) {
                case 1:

                    Toast.makeText(ForPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    savePayMessage("支付成功");
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    break;
                case 2:

                    Toast.makeText(ForPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    savePayMessage("支付失败");
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    break;
            }
        }
    };

    private String payMessage;

    private void savePayMessage(String message) {
        final MessageBean messageBean = new MessageBean();
        messageBean.setTime(System.currentTimeMillis());
        messageBean.setBody(message);
        messageBean.setIsRead(false);
        messageBean.setTitle("支付消息");
        messageBean.setType(MessageManager.PAY_TYPE);
        payMessage = message;
        MessageManager.getInstance().addMessage(messageBean, messageListener);
    }

    private MessageManager.IMessageListener messageListener = new MessageManager.IMessageListener() {
        @Override
        public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
            Toast.makeText(ForPayActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(payMessage);
        }
    };

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new FindForOrderContactImpl();
    }

    @Override
    public void onFindForPay(FindForPayBean findForPayBean) {

        List<FindForPayBean.ResultBean> result = findForPayBean.getResult();
        resultBeanList.addAll(result);
        forPayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFindForSend(FindForSendBean findForSendBean) {

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

    @Override
    protected void destroy() {
    }
}
