package com.bw.order.fragment;

import android.view.View;

import com.bw.framework.BaseFragment;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.shopmall.bawei.order.R;

public class AllOrderFragment extends BaseFragment<IPresenter, IView> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_allorder;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void onRightClick() {

    }
}
