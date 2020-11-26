package com.shopmall.bawei.shopmall1805.app.fragment.fenfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.fenlei.FenLeiTwoAdapter;
import com.shopmall.bawei.shopmall1805.app.contract.FenleiVpTwoContract;
import com.shopmall.bawei.shopmall1805.app.presenter.FenleiTwoPresenter;
import com.shopmall.bawei.shopmall1805.common.FenLeiVpTwoEntity;
import com.shopmall.bawei.shopmall1805.framework.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class TwoFragment extends BaseFragment<FenleiVpTwoContract.FenleiTwoPresenter> implements FenleiVpTwoContract.FenleiTwoView {
    private RecyclerView fenleiTwoRv;
    private FenLeiTwoAdapter fenLeiTwoAdapter;
    private List<FenLeiVpTwoEntity.ResultBean> list=new ArrayList<>();
    @Override
    protected void initEvent() {

    }
    @Override
    protected void createPresenter() {
        iPresenter=new FenleiTwoPresenter(this);
        iPresenter.setFenTwoData();
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void initView(View iView) {
        fenleiTwoRv = iView.findViewById(R.id.fenlei_two_rv);
        fenleiTwoRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        fenLeiTwoAdapter=new FenLeiTwoAdapter(R.layout.layout_fenlei_two_item,list);
        fenleiTwoRv.setAdapter(fenLeiTwoAdapter);
    }
    @Override
    protected int bandLyoaut() {
        return R.layout.fragment_two;
    }

    @Override
    public void setSkirtData(List<FenLeiVpTwoEntity.ResultBean> resultBeanList) {
        Log.i("TAG", "setSkirtData: "+resultBeanList.get(0).getName().toString());
        list.addAll(resultBeanList);
        fenLeiTwoAdapter.notifyDataSetChanged();
    }
}