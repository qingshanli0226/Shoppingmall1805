package com.shopmall.bawei.shopmall1805.typefragment.view;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.net.bean.Biaobean;
import com.shopmall.bawei.framework.example.framework.BaseFragment;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.LableAdpter;
import com.shopmall.bawei.shopmall1805.typefragment.contract.LableContract;
import com.shopmall.bawei.shopmall1805.typefragment.presenter.LablePresenter;

import java.util.List;

public class Fragment_Lable extends BaseFragment<LablePresenter, LableContract.biaoView> implements LableContract.biaoView {


    private RecyclerView rvBiao;
    private LableAdpter lableAdpter = new LableAdpter();
    @Override
    protected void initPreseter() {
        httpresetnter = new LablePresenter();
    }

    @Override
    protected void initView(View inflate) {
        //初始化控件
        rvBiao = inflate.findViewById(R.id.rv_biao);
        rvBiao.setLayoutManager(new GridLayoutManager(getContext(),3));
        rvBiao.setAdapter(lableAdpter);
    }

    @Override
    protected void initdate() {
        httpresetnter.getbiao();
    }


    @Override
    protected int getlayoutid() {
        return R.layout.fragment_biao;
    }

    @Override
    public void onbiao(List<Biaobean.ResultBean> beans) {
        //Toast.makeText(getContext(), ""+beans.get(0).getName(), Toast.LENGTH_SHORT).show();
        lableAdpter.updataData(beans);
        lableAdpter.notifyDataSetChanged();
    }

    @Override
    public void onErroy(String message) {
        showerror(message);
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
}
