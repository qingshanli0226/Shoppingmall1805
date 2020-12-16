package com.shopmall.bawei.shopmall1805.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.HomeAdapter;
import com.shopmall.common.Constants;
import com.shopmall.framework.base.BaseMVPFragment;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.mvptest.presenter.HomePresenter;
import com.shopmall.net.bean.HomeData;

public class HomeFragment extends BaseMVPFragment<HomePresenter> implements Contract.HomeConstartView {
    private RecyclerView homeRv;
    private HomeAdapter homeAdapter;

    @Override
    protected void createViewid(View inflate) {
        homeRv = (RecyclerView) inflate.findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter();
        homeRv.setAdapter(homeAdapter);
    }

    @Override
    protected void createEnvent() {

    }

    @Override
    protected void createData() {
        mPresenter.homec(Constants.HOME_URL2,loadingPage);
    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_home;
    }

    @Override
    protected void createPresenter() {
        mPresenter=new HomePresenter(this);
    }

    @Override
    public void Success(Object... objects) {
        HomeData homeData = (HomeData) objects[0];
        Toast.makeText(getContext(), ""+homeData, Toast.LENGTH_SHORT).show();

        homeAdapter.addOneData(homeData.getResult().getBanner_info());
        homeAdapter.addOneData(homeData.getResult().getChannel_info());
        homeAdapter.addOneData(homeData.getResult().getAct_info());
        homeAdapter.addOneData(homeData.getResult().getSeckill_info().getList());
        homeAdapter.addOneData(homeData.getResult().getRecommend_info());
        homeAdapter.addOneData(homeData.getResult().getHot_info());
    }

    @Override
    public void Error(String s) {

    }
}