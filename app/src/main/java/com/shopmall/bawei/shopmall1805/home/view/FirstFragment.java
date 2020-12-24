package com.shopmall.bawei.shopmall1805.home.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.common2.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.adapter.HomeMultiLayoutAdapter;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomePresenterImpl;


import java.util.ArrayList;
import java.util.List;

import mvp.view.BaseMVPFragment;

public class FirstFragment extends BaseMVPFragment<HomePresenterImpl, HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener {
    private RecyclerView main_rv;
    private List<Object> h_data=new ArrayList<>();
    private TextView errorTv;
    private RelativeLayout normalContent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        h_data.clear();
        errorTv=findViewById(R.id.errorTv);
        errorTv.setOnClickListener(this);
        normalContent=findViewById(R.id.normalContent);
        main_rv = findViewById(R.id.main_rv);
        main_rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    @Override
    public void showError(String code, String message) {
        errorTv.setVisibility(View.VISIBLE);
        normalContent.setVisibility(View.GONE);
        errorTv.setText(message + " 点击刷新数据");
    }

    @Override
    public void showLoaing() {
        errorTv.setVisibility(View.GONE);
        normalContent.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.VISIBLE);
        errorTv.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }


    @Override
    public void onHomeData(HomeBean homeBean) {

        List<HomeBean.BannerInfoBean> banner_info = homeBean.getBanner_info();
        h_data.add(banner_info);

        List<HomeBean.ChannelInfoBean> channel_info = homeBean.getChannel_info();
        h_data.add(channel_info);

        List<HomeBean.ActInfoBean> act_info = homeBean.getAct_info();
        h_data.add(act_info);

        HomeBean.SeckillInfoBean seckill_info = homeBean.getSeckill_info();
        List<HomeBean.SeckillInfoBean.ListBean> list1 = seckill_info.getList();
        h_data.add(list1);

        List<HomeBean.RecommendInfoBean> recommend_info = homeBean.getRecommend_info();
        h_data.add(recommend_info);

        List<HomeBean.HotInfoBean> hot_info = homeBean.getHot_info();
        h_data.add(hot_info);

        HomeMultiLayoutAdapter homeMultiLayoutAdapter = new HomeMultiLayoutAdapter(getContext());
        main_rv.setAdapter(homeMultiLayoutAdapter);
        homeMultiLayoutAdapter.updataData(h_data);
    }

    @Override
    protected void initHttpData() {
        ihttpPresenter.getHomeData();
    }

    @Override
    protected void initPresenter() {

        ihttpPresenter = new HomePresenterImpl();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.errorTv:
                ihttpPresenter.getHomeData();
                break;
            default:break;
        }
    }


}