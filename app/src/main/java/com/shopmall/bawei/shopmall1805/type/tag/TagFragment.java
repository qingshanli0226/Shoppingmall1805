package com.shopmall.bawei.shopmall1805.type.tag;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.example.net.bean.TagBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.util.ArrayList;
import java.util.List;

public class TagFragment extends BaseFragment<TagPresenterImpl, TagContract.TagView> implements TagContract.TagView {
    private RecyclerView rvTag;
    private List<TagBean.ResultBean> list=new ArrayList<>();
    private TagAdapter tagAdapter;
    @Override
    protected void initDate() {
        presenter.showTag();
    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initView(View inflate) {
        presenter=new TagPresenterImpl();
        rvTag = (RecyclerView) inflate.findViewById(R.id.rv_tag);
        rvTag.setLayoutManager(new GridLayoutManager(getContext(),3));
        tagAdapter=new TagAdapter();
        rvTag.setAdapter(tagAdapter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tag;
    }

    @Override
    public void onOk(TagBean bean) {
        list.clear();
        List<TagBean.ResultBean> result = bean.getResult();
        list.addAll(result);
        tagAdapter.updataData(list);
    }

    @Override
    public void showloading() {
        showLoading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmptyPage();
    }
}