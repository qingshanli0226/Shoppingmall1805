package com.shopmall.bawei.shopmall1805.app.ui.fragment;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.home.HomeAdapter;
import com.shopmall.bawei.shopmall1805.app.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.app.presenter.HomePresenterImpl;
import com.shopmall.bawei.shopmall1805.net.entity.HomeBean;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseMVPFragment<HomePresenterImpl,HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener {

    private RecyclerView rv;
    private List<Object> list=new ArrayList<>();
    private HomeAdapter baseHomeAdapter;
    private TextView errorTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {
        rv = findViewById(R.id.rv);
        errorTv = findViewById(R.id.errorTv);
        errorTv.setOnClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        baseHomeAdapter =new HomeAdapter(getContext());
        rv.setAdapter(baseHomeAdapter);
        toolbar.setToolBarRightImg(R.drawable.new_message_icon);
        toolbar.setRightTvTextSize(15);
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
    public void showLoaing() {
        showLoading();
    }
    @Override
    public void hideLoading() {
        hideLoadingPage(true);
    }
    @Override
    public void showEmpty() {

    }
    @Override
    public void onHomeDatas(HomeBean homeBean) {
        HomeBean.ResultBean result = homeBean.getResult();
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

    @Override
    public void onClick(View view) {

    }
}