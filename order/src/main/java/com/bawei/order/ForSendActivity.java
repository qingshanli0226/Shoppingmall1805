package com.bawei.order;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bawei.common.view.ErrorBean;
import com.bawei.framework.BaseActivity;
import com.bawei.net.mode.FindForPayBean;
import com.bawei.net.mode.FindForSendBean;
import com.bawei.order.adapter.ForSendAdapter;
import com.bawei.order.contact.FindForOrderContact;
import com.bawei.order.contact.FindForOrderContactImpl;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/order/ForSendActivity")
public class ForSendActivity extends BaseActivity<FindForOrderContactImpl, FindForOrderContact.IFindForOrderView> implements FindForOrderContact.IFindForOrderView {

    private RecyclerView forSendRecycler;
    private List<FindForSendBean.ResultBean> resultBeanList = new ArrayList<>();
    private ForSendAdapter forSendAdapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_for_send;
    }

    @Override
    protected void initView() {
        forSendRecycler = findViewById(R.id.for_send_recycler);
    }

    @Override
    protected void initData() {
        httpPresenter.FindForSend();
        forSendAdapter = new ForSendAdapter(R.layout.item_for_pay, resultBeanList);
        forSendRecycler.setAdapter(forSendAdapter);
        forSendRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new FindForOrderContactImpl();
    }

    @Override
    public void onFindForPay(FindForPayBean findForPayBean) {

    }

    @Override
    public void onFindForSend(FindForSendBean findForSendBean) {
        List<FindForSendBean.ResultBean> result = findForSendBean.getResult();
        resultBeanList.addAll(result);
        forSendAdapter.notifyDataSetChanged();
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
