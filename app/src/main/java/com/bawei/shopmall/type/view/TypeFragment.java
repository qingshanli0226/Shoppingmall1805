package com.bawei.shopmall.type.view;


import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.MyToolBar;
import com.bawei.framework.BaseFragment;
import com.bawei.net.mode.TagBean;
import com.bawei.shopmall.type.contract.TagContract;
import com.bawei.shopmall.type.contract.TagImpl;
import com.shopmall.bawei.shopmall1805.R;

public class TypeFragment<P extends TagImpl, V extends TagContract.ITagView> extends BaseFragment<P, V> implements TagContract.ITagView {

    private MyToolBar toolBar;

    @Override
    protected int layoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initView() {
        toolBar = findViewById(R.id.toolbar);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onTag(TagBean tagBean) {

    }

    @Override
    public void showLoaDing() {
        showloading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess, errorBean);
    }


    @Override
    public void showEmpty() {
        showEmptyPage();
    }
}