package com.shopmall.bawei.shopmall1805.type.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.framework.BaseFragment;
import com.bw.net.bean.TagBean;
import com.shopmall.bawei.shopmall1805.R;

import com.shopmall.bawei.shopmall1805.type.adapter.MyTagAdapter;
import com.shopmall.bawei.shopmall1805.type.contract.TagContract;
import com.shopmall.bawei.shopmall1805.type.presenter.TagPresenter;

import java.util.ArrayList;
import java.util.List;

public class TagFragment extends BaseFragment<TagPresenter, TagContract.TagView> implements TagContract.TagView {

    private RecyclerView recyclerView;
    private MyTagAdapter myTagAdapter;
    private List<TagBean.ResultBean> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tag;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.tagRv);
        myTagAdapter = new MyTagAdapter();
        recyclerView.setAdapter(myTagAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    @Override
    public void onOk(TagBean tagBean) {
        list.addAll(tagBean.getResult());

        myTagAdapter.updataData(list);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new TagPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        httpPresenter.getTag();
    }

    @Override
    public void onError(String message) {
        myToast(R.string.getDataError+message);
    }

    @Override
    public void showsLoaing() {
        showLoading();
    }

    @Override
    public void hidesLoading(boolean isSuccess) {
        hideLoadingPage(isSuccess);
    }



    @Override
    public void showEmpty() {

    }
}
