package com.shopmall.bawei.shopmall1805.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.framework.BaseFragment;
import com.example.net.ClothesBean;
import com.example.net.JavaBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.NameAdapter;
import com.shopmall.bawei.shopmall1805.contract.ClassfiyContract;
import com.shopmall.bawei.shopmall1805.presenter.ClassPresenter;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment2 extends BaseFragment<ClassPresenter, ClassfiyContract.ClassfiyView> implements ClassfiyContract.ClassfiyView {

    private RecyclerView recyclerView;
    private NameAdapter nameAdapter;
    private List<ClothesBean> list = new ArrayList<>();
    @Override
    protected void initView(View inflate) {
        recyclerView = inflate.findViewById(R.id.recv);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ClassPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank2;
    }

    @Override
    protected void initData() {
        httpPresenter.getData1();
        Log.i("wft111", "initData: "+"111");
    }

    @Override
    public void getViewData1(JavaBean clotheslist) {
        Log.i("wft111", "getViewData: "+clotheslist);

        nameAdapter = new NameAdapter(R.layout.item_name, clotheslist.getResult());
        recyclerView.setAdapter(nameAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onError(String message) {

    }
}