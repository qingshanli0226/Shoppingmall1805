package com.shopmall.bawei.shopmall1805.user.view;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.shopmall.bawei.common.ARouterHelper;
import com.shopmall.bawei.common.FragmentHelper;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.PayBean;
import com.shopmall.bawei.order.OrderActivity;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.user.contract.IPayContract;
import com.shopmall.bawei.shopmall1805.user.contract.PayImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnPaidActivity extends BaseActivity<PayImpl, IPayContract.IPayView> implements IPayContract.IPayView {
    private RecyclerView rvPay;
    private UnpaidRvAdapter adapter;

    private List<PayBean> payBeans = new ArrayList<>();


    @Override
    protected int layoutId() {
        return R.layout.activity_unpaid;
    }

    @Override
    protected void initListener() {
        adapter.setIRecyclerViewItemClickListener(new BaseRvAdapter.IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask payTask = new PayTask(UnPaidActivity.this);
                        Map<String,String> resultMap = payTask.payV2(payBeans.get(position).getOrderInfo(),true);
                        if(resultMap.get("resultStatus").equals("9000")){
                            handler.sendEmptyMessage(1);
                        } else {
                            handler.sendEmptyMessage(2);
                        }
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(UnPaidActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build(ARouterHelper.APP_MAIN).withInt("index", FragmentHelper.ORDER_INDEX).navigation();
                    finish();
                    break;
                case 2:
                    Toast.makeText(UnPaidActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build(ARouterHelper.APP_MAIN).withInt("index",FragmentHelper.ORDER_INDEX).navigation();
                    finish();
                    break;
            }

        }
    };

    @Override
    protected void initView() {
        rvPay = (RecyclerView) findViewById(R.id.rv_pay);
        rvPay.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UnpaidRvAdapter();
        rvPay.setAdapter(adapter);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);


    }

    @Override
    protected void initData() {
        httpPresenter.getPayBeans();
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new PayImpl();
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

    @Override
    public void onPay(List<PayBean> payBeanList) {
        for (int i = 0; i < payBeanList.size(); i++) {
            Log.i("status", "onPay: "+payBeanList.get(i));
        }

        payBeans.clear();
        payBeans.addAll(payBeanList);
        adapter.updateData(payBeans);
    }
}
