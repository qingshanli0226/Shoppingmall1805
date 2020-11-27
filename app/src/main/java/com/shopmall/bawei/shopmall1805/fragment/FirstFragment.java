package com.shopmall.bawei.shopmall1805.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.countroller.UserCountroller;
import com.bawei.deom.countroller.UserIMPL;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.PrimereAdpter;

import java.util.List;

import bean.HomeBean;
import bean.TAGBean;


public class FirstFragment extends BaseFragment<UserIMPL, UserCountroller.UserView> implements UserCountroller.UserView {
    private RecyclerView rv;
    private PrimereAdpter primereAdpter;



    @Override
    protected void inPrine() {
        prine=new UserIMPL();


    }

    @Override
    protected void initData() {
        prine.getskerak();

    }

    @Override
    protected void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv);
        primereAdpter = new PrimereAdpter();
        rv.setAdapter(primereAdpter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected int getlayoutview() {
        return R.layout.firstfragment;
    }



    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void onskerk(HomeBean homeBeanList) {
        primereAdpter.addOneData(homeBeanList.getBanner_info());
        primereAdpter.addOneData(homeBeanList.getChannel_info());
        primereAdpter.addOneData(homeBeanList.getAct_info());
        primereAdpter.addOneData(homeBeanList.getSeckill_info().getList());
        primereAdpter.addOneData(homeBeanList.getRecommend_info());
        primereAdpter.addOneData(homeBeanList.getHot_info());
    }

    @Override
    public void TagBiew(List<TAGBean.ResultBean> resultBeanList) {

    }





}
