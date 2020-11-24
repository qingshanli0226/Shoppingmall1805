package com.shopmall.bawei.shopmall1805.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.HomeAdapter;
import com.shopmall.bawei.shopmall1805.mvp.contract.HContract;
import com.shopmall.bawei.shopmall1805.mvp.model.HModel;
import com.shopmall.bawei.shopmall1805.mvp.prsenter.HPresenter;

import com.shopmall.bawei.shopmall1805.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

import mvp.view.BaseFragment;

public class FirstFragment extends BaseFragment<HPresenter> implements HContract.geteview {
    private View view;
    private RecyclerView main_rv;
    private List<Object> h_data=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view= inflater.inflate(bandlayout(), container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        initdata();
    }


    @Override
    public int bandlayout() {
        return R.layout.fragment_first;
    }

    @Override
    public void initview() {

        main_rv = view.findViewById(R.id.main_rv);
        main_rv.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    public void initdata() {

        ipresenter = new HPresenter(new HModel(),this);
        ipresenter.inithomedata();


    }

    @Override
    public void getdata(HomeBean list) {
        HomeBean.ResultBean result = list.getResult();

        List<HomeBean.ResultBean.BannerInfoBean> banner_info = result.getBanner_info();
        h_data.add(banner_info);

        List<HomeBean.ResultBean.ChannelInfoBean> channel_info = result.getChannel_info();
        h_data.add(channel_info);

        List<HomeBean.ResultBean.ActInfoBean> act_info = result.getAct_info();
        h_data.add(act_info);

        HomeBean.ResultBean.SeckillInfoBean seckill_info = result.getSeckill_info();
        List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list1 = seckill_info.getList();
        h_data.add(list1);

        List<HomeBean.ResultBean.RecommendInfoBean> recommend_info = result.getRecommend_info();
        h_data.add(recommend_info);

        List<HomeBean.ResultBean.HotInfoBean> hot_info = result.getHot_info();
        h_data.add(hot_info);

        HomeAdapter homeAdapter = new HomeAdapter(getContext());
        main_rv.setAdapter(homeAdapter);
        homeAdapter.updataData(h_data);

    }
}