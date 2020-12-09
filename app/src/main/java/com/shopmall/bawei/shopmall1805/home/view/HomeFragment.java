package com.shopmall.bawei.shopmall1805.home.view;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.PrimereAdpter;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomePresenter;

public class HomeFragment extends BaseFragment<HomePresenter, HomeContract.SkerakView> implements HomeContract.SkerakView {
    private RecyclerView rv;
    private PrimereAdpter primereAdpter;
    private EditText ed;

    @Override
    protected void initPreseter() {
        httpresetnter= new HomePresenter();
    }

    @Override
    protected void initView(View inflate) {

        ed = inflate.findViewById(R.id.ed);


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
        return R.layout.fragment_home;
    }


    @Override
    public void onErroy(String message) {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showsloading() {
        showloading();
    }


    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
        showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
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
}
