package com.shopmall.bawei.shopmall1805.Fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.net.bean.BaseBean;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.FragmentAdpter;
import com.shopmall.bawei.shopmall1805.adpter.PrimereAdpter;
import com.shopmall.bawei.shopmall1805.contract.PrimereContract;
import com.shopmall.bawei.shopmall1805.presenter.PrimerePresenter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Primere extends BaseFragment<PrimerePresenter, PrimereContract.SkerakView> implements PrimereContract.SkerakView {
    private RecyclerView rv;
    private PrimereAdpter primereAdpter;
    @Override
    protected void initPreseter() {
        httpresetnter= new PrimerePresenter();
    }

    @Override
    protected void initView(View inflate) {
        rv = inflate.findViewById(R.id.rv);
        primereAdpter = new PrimereAdpter();
        rv.setAdapter(primereAdpter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected void initdate() {
        httpresetnter.getskerak();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_fragment__primere;
    }


    @Override
    public void onErroy(String message) {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onskerk(HomeBean homeBeanList) {
        Toast.makeText(getContext(), ""+homeBeanList.getHot_info().size(), Toast.LENGTH_SHORT).show();
        primereAdpter.addOneData(homeBeanList.getBanner_info());
        primereAdpter.addOneData(homeBeanList.getChannel_info());
        primereAdpter.addOneData(homeBeanList.getAct_info());
        primereAdpter.addOneData(homeBeanList.getSeckill_info().getList());
        primereAdpter.addOneData(homeBeanList.getRecommend_info());
        primereAdpter.addOneData(homeBeanList.getHot_info());
    }
}
