package com.shopmall.bawei.shopmall1805.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.R;

import com.shopmall.bawei.shopmall1805.base.BaseFragment;
import com.shopmall.bawei.shopmall1805.home.adapter.HomeFragmentAdapter;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomePresenter, HomeContract.IHomeView> implements HomeContract.IHomeView {


    private RecyclerView homeRv;
    private List<Object> list =new ArrayList<>();
    private  HomeFragmentAdapter homeFragmentAdapter;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        homeRv = view.findViewById(R.id.rv);
//        homeListAdapter = new HomeListAdapter();
//        homeRv.setAdapter(homeListAdapter);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeFragmentAdapter = new HomeFragmentAdapter();
        homeRv.setAdapter(homeFragmentAdapter);


    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new HomePresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        httpPresenter.onGetHomeData();
    }

    @Override
    public void onOk(HomeFragmentBean homeFragmentBean) {
//        list.add(homeFragmentBean.getResult());
        list.add(homeFragmentBean.getResult().getBanner_info());
        list.add(homeFragmentBean.getResult().getChannel_info());
        list.add(homeFragmentBean.getResult().getAct_info());
        list.add(homeFragmentBean.getResult().getRecommend_info());
        list.add(homeFragmentBean.getResult().getSeckill_info().getList());
        list.add(homeFragmentBean.getResult().getHot_info());
        homeFragmentAdapter.updataData(list);
    }

    @Override
    public void onError(String message) {

    }
}
