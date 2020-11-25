package com.shopmall.bawei.shopmall1805.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.TagAdapter;
import com.shopmall.common.Constants;
import com.shopmall.framework.base.BaseMVPFragment;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.presenter.TagPresenter;
import com.shopmall.net.bean.TagBean;

public class TagFragment extends BaseMVPFragment<TagPresenter> implements Constart.TagConstartView {
    private RecyclerView rvTag;
    private TagAdapter tagAdapter;

    @Override
    protected void createViewid(View inflate) {
        rvTag = (RecyclerView) inflate.findViewById(R.id.rv_tag);
    }

    @Override
    protected void createEnvent() {

    }

    @Override
    protected void createData() {
        mPresenter.tag(Constants.TAG_URL2);
        rvTag.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_tag;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new TagPresenter(this);
    }

    @Override
    public void Success(Object... objects) {
        TagBean tagBean = (TagBean)objects[0];
        tagAdapter = new TagAdapter(R.layout.item_tab_gridview,tagBean.getResult());
        rvTag.setAdapter(tagAdapter);
    }

    @Override
    public void Error(String s) {

    }
}