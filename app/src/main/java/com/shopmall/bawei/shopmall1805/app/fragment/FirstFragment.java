package com.shopmall.bawei.shopmall1805.app.fragment;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.MyHomeAdapter;
import com.shopmall.bawei.shopmall1805.app.contract.FirstHomeContract;
import com.shopmall.bawei.shopmall1805.app.presenter.BannerPresenter;
import com.shopmall.bawei.shopmall1805.common.HomeBean;
import com.shopmall.bawei.shopmall1805.framework.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment  extends BaseFragment<FirstHomeContract.BannerHomePresenter> implements FirstHomeContract.BannerHomeView {
    private RecyclerView rv;
    private List<HomeBean> list=new ArrayList<>();
    private MyHomeAdapter myHomeAdapter;
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
        myHomeAdapter = new MyHomeAdapter(list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(myHomeAdapter);



    }
    @Override
    protected int bandLyoaut() {
        return R.layout.fragment_first;
    }
    @Override
    public void setHomeData(HomeBean bannerInfoBeans) {
        if(bannerInfoBeans!=null){
            list.add(bannerInfoBeans);
            myHomeAdapter.notifyDataSetChanged();
        }
    }
}