package com.bw.order.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bw.framework.BaseFragment;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.framework.OrderManager;
import com.bw.net.ForPayBean;
import com.bw.order.adapter.ForPayAdapter;
import com.shopmall.bawei.order.R;

import java.util.List;

public class ForPayFragment extends BaseFragment<IPresenter, IView> {

    private RecyclerView forPayRv;
    private ForPayAdapter forPayAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forpay;
    }

    @Override
    protected void initView(View view) {
        List<ForPayBean> forPayBeanList = OrderManager.getInstance().getForPayBeanList();
        forPayAdapter = new ForPayAdapter();
        Log.i("---", "initView forPayBeanListSize: "+forPayBeanList.size());
        forPayRv = findViewById(R.id.forPayRv);
        forPayRv.setAdapter(forPayAdapter);
        forPayAdapter.updataData(forPayBeanList);
        forPayRv.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    public void onRightClick() {

    }
}
