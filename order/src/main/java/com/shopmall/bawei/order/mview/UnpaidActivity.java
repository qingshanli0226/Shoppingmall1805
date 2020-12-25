package com.shopmall.bawei.order.mview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.adaper.UnpaoidAdaper;
import com.shopmall.bawei.order.presenter.OrderPresenter;

import java.util.ArrayList;
import java.util.List;

import framework.BaseActivity;
import framework.Contact;
import framework.IOrderData;
import view.SkipFinalUlis;
import view.loadinPage.ErrorBean;
import view.loadinPage.UnpaidBean;

@Route(path = SkipFinalUlis.UNPAID_ACTIVITY)
public class UnpaidActivity extends BaseActivity<OrderPresenter> implements View.OnClickListener, Contact.ICenterOrderIview {
    private List<UnpaidBean.ResultBean> resultBeans = new ArrayList<>();
    private UnpaoidAdaper unpaoidAdaper;
    private RecyclerView Rv;
    @Override
    protected void createPresenter() {
        jsonPresenter = new OrderPresenter(this);
    }
    @Override
    protected void OnClickListener() { }

    @Override
    protected void initData() {
        tooBar = findViewById(R.id.tooBar);
        jsonPresenter.goUnpaidOrder();

        Rv = (RecyclerView) findViewById(R.id.Rv);

        Rv.setLayoutManager(new LinearLayoutManager(this));
        unpaoidAdaper = new UnpaoidAdaper(R.layout.item_unpaid_pay,resultBeans);
        Rv.setAdapter(unpaoidAdaper);

    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_unpaid;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onUnpaidSuccess(UnpaidBean unpaidBean) {
        Log.i("====","获得的数据"+unpaidBean.getResult());
        resultBeans.addAll(unpaidBean.getResult());
        unpaoidAdaper.notifyDataSetChanged();
        Log.i("====",resultBeans.size()+"尺寸");
    }

    @Override
    public void onError(String Error) {

    }

    @Override
    public void showLoaDing() {
        showLoaDing();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoading(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }

}