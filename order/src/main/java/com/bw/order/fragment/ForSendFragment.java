package com.bw.order.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.framework.BaseFragment;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.framework.OrderManager;
import com.bw.net.ForSendBean;
import com.shopmall.bawei.order.R;

import java.util.List;

public class ForSendFragment extends BaseFragment<IPresenter, IView> {

    private RecyclerView forSendRv;
    private ForSendAdapter forSendAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forsend;
    }

    @Override
    protected void initView(View view) {

        forSendRv = findViewById(R.id.forSendRv);
        forSendAdapter = new ForSendAdapter();
        forSendRv.setAdapter(forSendAdapter);
        forSendRv.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ForSendBean> forSendBeanList = OrderManager.getInstance().getForSendBeanList();
        forSendAdapter.updataData(forSendBeanList);

    }

    @Override
    public void onRightClick() {

    }
}
