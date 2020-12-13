package com.shopmall.bawei.shopmall1805.home.fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.R;

import framework.BaseFragment;
import framework.mvpc.JsonPresenter;
import view.loadinPage.ErrorBean;
import view.ToolBar;

public
class FragmentShop extends BaseFragment<JsonPresenter> implements ToolBar.IToolBarClickListner {
    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData() {
        tooBar = (ToolBar) findViewById(R.id.tooBar);
        ARouter.getInstance().inject(getContext());

    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_shop;
    }


    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}
