package com.shopmall.bawei.shopmall1805.order;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.shopmall1805.common.ARouterUtils;
import com.shopmall.bawei.shopmall1805.common.ErrorBean;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPActivity;
import com.shopmall.bawei.shopmall1805.net.entity.FindForBean;
import com.shopmall.bawei.shopmall1805.order.adapter.OrDerAdapter;
import com.shopmall.bawei.shopmall1805.order.contract.ForPayContract;
import com.shopmall.bawei.shopmall1805.order.presenter.ForPayPresenterImpl;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterUtils.ORDER_NO_PAYMENT)
public class NonPayMentActivity extends BaseMVPActivity<ForPayPresenterImpl, ForPayContract.ForPayView> implements ForPayContract.ForPayView{

    private RecyclerView orderRv;
    private OrDerAdapter orDerAdapter;
    private List<FindForBean.ResultBean> list=new ArrayList<>();
    @Override
    protected void initData() {
        if(httpPresenter!=null){
            httpPresenter.getFindFor();
        }
        orDerAdapter=new OrDerAdapter(R.layout.item_order,list);
        orderRv.setAdapter(orDerAdapter);
    }
    @Override
    protected void initView() {
        orderRv = findViewById(R.id.order_rv);
        orderRv.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_non_pay_ment;
    }
    @Override
    protected void initPresenter() {
        httpPresenter = new ForPayPresenterImpl();
    }
    @Override
    public void showLoaing() {

    }
    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }
    @Override
    public void showEmpty() {

    }
    @Override
    public void onFindFor(List<FindForBean.ResultBean> resultBeans) {
        list.addAll(resultBeans);
        orDerAdapter.notifyDataSetChanged();
    }
}