package com.shopmall.bawei.order.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.mvptest.presenter.ShopcarPresenter;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.adapter.OrderPayAdapter;
import com.shopmall.bean.OrderPaybean;
@Route(path = "/order/OrderPayActivity")
public class OrderPayActivity extends BaseActivity<ShopcarPresenter> implements Constant.ShopcarConstartView {

    private ImageView orderpayBack;
    private RecyclerView orderpayRecycle;
    private OrderPayAdapter orderPayAdapter;
    @Override
    protected void oncreatePresenter() {
       mPresenter=new ShopcarPresenter(this);
    }

    @Override
    protected void initEnvent() {
       //返回按钮
        orderpayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initview() {
        ARouter.getInstance().inject(this);

        orderpayBack = findViewById(R.id.orderpay_back);
        orderpayRecycle = findViewById(R.id.orderpay_recycle);

        orderpayRecycle.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initData() {
        mPresenter.findForPay(Constants.FINDFORPAY);
        orderPayAdapter=new OrderPayAdapter();
        orderpayRecycle.setAdapter(orderPayAdapter);
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_order_pay;
    }

    @Override
    public void Success(Object... objects) {
        OrderPaybean orderPaybean=(OrderPaybean)objects[0];
        orderPayAdapter.updataData(orderPaybean.getResult());
    }

    @Override
    public void Error(String s) {

    }
}