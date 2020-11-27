package com.shopmall.bawei.shopmall1805.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.net.BaseBean;
import com.example.net.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.PrimereAdapter;
import com.shopmall.bawei.shopmall1805.contract.PrimereContract;
import com.shopmall.bawei.shopmall1805.presenter.PriPresenter;

import java.util.ArrayList;
import java.util.List;


public class PrimereFragment extends BaseFragment<PriPresenter, PrimereContract.PrimereView> implements PrimereContract.PrimereView {

    private RecyclerView recyclerView;
    private List<HomeBean> list = new ArrayList<>();
    private PrimereAdapter primereAdapter;


    @Override
    protected void initView(View inflate) {
        recyclerView = inflate.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new PriPresenter();
        Log.i("wft", "initPresenter: "+"111");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_primere;
    }

    @Override
    protected void initData() {
        httpPresenter.getData();
        Log.i("wft", "initPresenter: "+"12211");

    }







    @Override
    public void getViewData(BaseBean<HomeBean> clotheslist) {
        Log.i("wft", "getViewData: "+clotheslist.getResult());
        primereAdapter = new PrimereAdapter(list);
        recyclerView.setAdapter(primereAdapter);
        if (list==null){
            list.add(clotheslist.getResult());
            primereAdapter.notifyDataSetChanged();

        }else {
            list.clear();
            list.add(clotheslist.getResult());
            Toast.makeText(getContext(), ""+"1", Toast.LENGTH_SHORT).show();
            primereAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {
        showLoading();
    }

    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {

    }

    @Override
    public void showEmpty() {

    }
}