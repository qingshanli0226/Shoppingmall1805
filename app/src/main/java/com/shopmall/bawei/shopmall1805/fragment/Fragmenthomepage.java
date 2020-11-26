package com.shopmall.bawei.shopmall1805.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.shopmall.bawei.shopmall1805.Adper.JsonCallbackInterface;
import com.shopmall.bawei.shopmall1805.Adper.homeadper.PrimereAdper;
import com.shopmall.bawei.shopmall1805.R;

import android.util.Log;
import android.widget.Toast;

import framework.BaseFragment;
import framework.User2;
import framework.mvpc.jsonPresenter;
import mode.HomeBean;
import view.loadinPage.ErrorBean;

public
class Fragmenthomepage extends BaseFragment {
    private RecyclerView rv;
    private PrimereAdper primereAdpter;
    @Override
    protected void createPresenter() {
        Presenter = new jsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData() {
        JsonData();
        rv = (RecyclerView)findViewById(R.id.rv);
        primereAdpter = new PrimereAdper();
        rv.setAdapter(primereAdpter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void JsonData() {
            Presenter.getshopcal(6, new JsonCallbackInterface() {
                @Override
                public void successHome(HomeBean homeBean) {
                    primereAdpter.addOneData(homeBean.getResult().getBanner_info());
                    primereAdpter.addOneData(homeBean.getResult().getChannel_info());
                    primereAdpter.addOneData(homeBean.getResult().getAct_info());
                    primereAdpter.addOneData(homeBean.getResult().getSeckill_info().getList());
                    primereAdpter.addOneData(homeBean.getResult().getRecommend_info());
                    primereAdpter.addOneData(homeBean.getResult().getHot_info());
                    primereAdpter.notifyDataSetChanged();
                }
            });
    }



    @Override
    protected int getlayoutId() {
        return R.layout.fragment_homepage;
    }


}
