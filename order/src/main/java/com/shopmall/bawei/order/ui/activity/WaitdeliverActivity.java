package com.shopmall.bawei.order.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.mvptest.presenter.ShopcarPresenter;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.adapter.WaitdeliverAdapter;
import com.shopmall.bean.OrderPaybean;
@Route(path = "/order/WaitdeliverActivity")
public class WaitdeliverActivity extends BaseActivity<ShopcarPresenter> implements Constant.ShopcarConstartView {

    private ImageView waitdeliverBack;
    private RecyclerView waitdeliverRecycle;
    private WaitdeliverAdapter waitdeliverAdapter;
    @Override
    protected void oncreatePresenter() {
         mPresenter=new ShopcarPresenter(this);
    }

    @Override
    protected void initEnvent() {
            waitdeliverBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }

    @Override
    protected void initview() {

        waitdeliverBack = findViewById(R.id.waitdeliver_back);
        waitdeliverRecycle = findViewById(R.id.waitdeliver_recycle);

        waitdeliverRecycle.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
         mPresenter.findForsend(Constants.FINDFORSEND);
        waitdeliverAdapter=new WaitdeliverAdapter();
        waitdeliverRecycle.setAdapter(waitdeliverAdapter);
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_waitdeliver;
    }

    @Override
    public void Success(Object... objects) {
        OrderPaybean orderPaybean=(OrderPaybean)objects[0];
        waitdeliverAdapter.updataData(orderPaybean.getResult());

    }

    @Override
    public void Error(String s) {

    }
}