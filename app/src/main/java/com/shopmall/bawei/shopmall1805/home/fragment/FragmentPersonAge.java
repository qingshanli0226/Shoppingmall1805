package com.shopmall.bawei.shopmall1805.home.fragment;

import android.view.View;
import android.widget.ImageButton;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.R;

import framework.BaseFragment;
import view.ShopmallConstant;
import view.loadinPage.ErrorBean;
import view.ToolBar;

public
class FragmentPersonAge extends BaseFragment implements View.OnClickListener,ToolBar.IToolBarClickListner {
    private ImageButton ibUserIconAvator;
    //private ToolBar tooBarPerSonAge;

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {
        findViewById(R.id.ib_user_icon_avator).setOnClickListener(this);
    }

    @Override
    protected void InitData() {
        ibUserIconAvator = (ImageButton) findViewById(R.id.ib_user_icon_avator);
        tooBar = (ToolBar) findViewById(R.id.tooBar);

    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_personage; }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_user_icon_avator:
                ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH).
                        withInt(ShopmallConstant.TO_LOGIN_KEY, ShopmallConstant.TO_LOGIN_FROM_MINE_FRAGMENT).navigation();
                break;
        }
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
