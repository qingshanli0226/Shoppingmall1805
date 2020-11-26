package com.shopmall.bawei.shopmall1805.app.fragment;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.home.BaseHomeAdapter;
import com.shopmall.bawei.shopmall1805.app.contract.FirstHomeContract;
import com.shopmall.bawei.shopmall1805.app.presenter.BannerPresenter;
import com.shopmall.bawei.shopmall1805.common.HomeBean;
import com.shopmall.bawei.shopmall1805.framework.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends BaseFragment<FirstHomeContract.BannerHomePresenter> implements FirstHomeContract.BannerHomeView {

    private RecyclerView rv;
    private List<Object> list=new ArrayList<>();
    private BaseHomeAdapter baseHomeAdapter=new BaseHomeAdapter(getContext());
    @Override
    protected void initEvent() {

    }
    @Override
    protected void createPresenter() {
        iPresenter = new BannerPresenter(this);
        iPresenter.getHomeDatas();
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void initView(View iView) {
        rv = iView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(baseHomeAdapter);
    }
    @Override
    protected int bandLyoaut() {
        return R.layout.fragment_first;
    }
    @Override
    public void setHomeData(HomeBean homeData) {
        HomeBean.ResultBean result = homeData.getResult();
        List<HomeBean.ResultBean.BannerInfoBean> banner_info = result.getBanner_info();
        List<HomeBean.ResultBean.ChannelInfoBean> channel_info = result.getChannel_info();
        List<HomeBean.ResultBean.ActInfoBean> act_info = result.getAct_info();
        List<HomeBean.ResultBean.SeckillInfoBean.ListBean> lists = result.getSeckill_info().getList();
        List<HomeBean.ResultBean.RecommendInfoBean> recommend_info = result.getRecommend_info();
        List<HomeBean.ResultBean.HotInfoBean> hot_info = result.getHot_info();


        list.add(banner_info);
        list.add(channel_info);
        list.add(act_info);
        list.add(lists);
        list.add(recommend_info);
        list.add(hot_info);
        baseHomeAdapter.upDataText(list);
    }
}