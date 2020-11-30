package com.shopmall.bawei.shopmall1805.fragment;

import android.view.View;
import android.widget.ImageButton;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.R;

import framework.BaseFragment;
import view.ShopmallConstant;

public
class Fragmentpersonage extends BaseFragment implements View.OnClickListener {
    private ImageButton ibUserIconAvator;
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
}
