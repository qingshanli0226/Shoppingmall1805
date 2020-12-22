package com.shopmall.bawei.shopmall1805.app.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.classify.ClassifyTagAdapter;
import com.shopmall.bawei.shopmall1805.app.contract.ClassifyRightContract;
import com.shopmall.bawei.shopmall1805.app.presenter.ClassifyRightPresenterImpl;
import com.shopmall.bawei.shopmall1805.common.ErrorBean;
import com.shopmall.bawei.shopmall1805.net.entity.ClassifyTagEntity;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPFragment;

import java.util.ArrayList;
import java.util.List;


public class ClassifyRightFragment extends BaseMVPFragment<ClassifyRightPresenterImpl,ClassifyRightContract.FenleiTwoView> implements ClassifyRightContract.FenleiTwoView {
    private RecyclerView fenleiTwoRv;
    private ClassifyTagAdapter fenLeiTwoAdapter;
    private List<ClassifyTagEntity.ResultBean> list=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify_right;
    }
    @Override
    protected void initData() {
        fenleiTwoRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        fenLeiTwoAdapter=new ClassifyTagAdapter(R.layout.classify_tag_item,list);
        fenleiTwoRv.setAdapter(fenLeiTwoAdapter);
    }
    @Override
    protected void initView() {
        fenleiTwoRv =findViewById(R.id.fenlei_two_rv);

    }
    @Override
    public void onFenleiRightData(List<ClassifyTagEntity.ResultBean> resultBeanList) {
        Log.i("TAG", "setSkirtData: "+resultBeanList.get(0).getName().toString());
        list.addAll(resultBeanList);
        fenLeiTwoAdapter.notifyDataSetChanged();
    }
    @Override
    protected void initHttpData() {
        ihttpPresenter.getFenLeiRightView();
    }
    @Override
    protected void initPresenter() {
        ihttpPresenter = new ClassifyRightPresenterImpl();
    }
    @Override
    public void showLoaing() {

    }
    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }
    @Override
    public void showEmpty() {

    }
}