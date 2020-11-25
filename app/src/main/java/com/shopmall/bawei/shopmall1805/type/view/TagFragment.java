package com.shopmall.bawei.shopmall1805.type.view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.net.mode.TagBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.type.contract.TagContract;
import com.shopmall.bawei.shopmall1805.type.contract.TagImpl;

import java.util.ArrayList;
import java.util.List;

public class TagFragment<P extends TagImpl,V extends TagContract.ITagView> extends BaseFragment<P,V> implements TagContract.ITagView {
    private RecyclerView rvTag;
    private TagAdapter adapter;
    private List<TagBean.ResultBean> list = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.fragment_tag;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        rvTag = (RecyclerView) findViewById(R.id.rv_tag);
        rvTag.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
        rvTag.setAdapter(adapter = new TagAdapter());
        adapter.updateData(list);
    }

    @Override
    protected void initData() {
        httpPresenter.tag();
    }

    @Override
    protected void initPresenter() {
        httpPresenter = (P) new TagImpl();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onTag(TagBean tagBean) {
        list.addAll(tagBean.getResult());
        adapter.updateData(list);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}