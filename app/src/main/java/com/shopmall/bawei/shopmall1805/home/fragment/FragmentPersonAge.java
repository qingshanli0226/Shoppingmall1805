package com.shopmall.bawei.shopmall1805.home.fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.R;

import framework.BaseFragment;
import framework.mvpc.JsonPresenter;
import view.ShopmallConstant;
import view.SkipFinalUlis;
import view.loadinPage.ErrorBean;
import view.ToolBar;

public
class FragmentPersonAge extends BaseFragment<JsonPresenter> implements View.OnClickListener,ToolBar.IToolBarClickListner {
    private ImageButton ibUserIconAvator;
    private TextView tvUserPay;
    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {
        findViewById(R.id.ib_user_icon_avator).setOnClickListener(this);
        findViewById(R.id.tv_user_pay).setOnClickListener(this);
    }

    @Override
    protected void InitData() {

        tvUserPay = (TextView) findViewById(R.id.tv_user_pay);
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
            case R.id.tv_user_pay:
                ARouter.getInstance().build(SkipFinalUlis.UNPAID_ACTIVITY).navigation();
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
