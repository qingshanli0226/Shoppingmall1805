package com.bawei.shopmall.home.view;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.common.view.ErrorBean;
import com.bawei.framework.BaseFragment;
import com.bawei.net.mode.HomeBean;
import com.bawei.shopmall.home.contract.HomeContract;
import com.bawei.shopmall.home.contract.HomeImpl;
import com.shopmall.bawei.shopmall1805.R;

public class HomeFragment extends BaseFragment<HomeImpl, HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener {

    private RelativeLayout normalContent;
    private RecyclerView homeRv;
    private TextView errorTv;

    private HomeAdapter homeAdapter;


    @Override
    protected void initView() {
        normalContent = (RelativeLayout) findViewById(R.id.normalContent);
        homeRv = (RecyclerView) findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter();
        homeRv.setAdapter(homeAdapter);
        errorTv = (TextView) findViewById(R.id.errorTv);
        errorTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.errorTv:
                httpPresenter.getHomeData();
                break;
            default:
                break;
        }
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new HomeImpl();

    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        homeAdapter.addOneData(homeBean.getResult().getBanner_info());
        homeAdapter.addOneData(homeBean.getResult().getChannel_info());
        homeAdapter.addOneData(homeBean.getResult().getAct_info());
        homeAdapter.addOneData(homeBean.getResult().getSeckill_info());
        homeAdapter.addOneData(homeBean.getResult().getRecommend_info());
        homeAdapter.addOneData(homeBean.getResult().getHot_info());
    }

    @Override
    public void showLoaDing() {
        showloading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess, errorBean);
    }



    @Override
    public void showEmpty() {
        showEmptyPage();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
    }
}
