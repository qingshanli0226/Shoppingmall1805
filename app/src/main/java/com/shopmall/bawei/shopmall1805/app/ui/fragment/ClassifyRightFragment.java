package com.shopmall.bawei.shopmall1805.app.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.View;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.fenlei.ClassifyTagAdapter;
import com.shopmall.bawei.shopmall1805.app.contract.ClassifyRightContract;
import com.shopmall.bawei.shopmall1805.app.presenter.ClassifyRightPresenter;
import com.shopmall.bawei.shopmall1805.common.FenLeiVpTwoEntity;
import com.shopmall.bawei.shopmall1805.framework.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class ClassifyRightFragment extends BaseFragment<ClassifyRightContract.FenleiTwoPresenter> implements ClassifyRightContract.FenleiTwoView {
    private RecyclerView fenleiTwoRv;
    private ClassifyTagAdapter fenLeiTwoAdapter;
    private List<FenLeiVpTwoEntity.ResultBean> list=new ArrayList<>();
    @Override
    protected void initEvent() {

    }
    @Override
    protected void createPresenter() {
        iPresenter=new ClassifyRightPresenter(this);
        iPresenter.setFenTwoData();
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void initView(View iView) {
        fenleiTwoRv = iView.findViewById(R.id.fenlei_two_rv);
        fenleiTwoRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        fenLeiTwoAdapter=new ClassifyTagAdapter(R.layout.classify_tag_item,list);
        fenleiTwoRv.setAdapter(fenLeiTwoAdapter);
    }
    @Override
    protected int bandLyoaut() {
        return R.layout.fragment_classify_right;
    }

    @Override
    public void setSkirtData(List<FenLeiVpTwoEntity.ResultBean> resultBeanList) {
        Log.i("TAG", "setSkirtData: "+resultBeanList.get(0).getName().toString());
        list.addAll(resultBeanList);
        fenLeiTwoAdapter.notifyDataSetChanged();
    }
}