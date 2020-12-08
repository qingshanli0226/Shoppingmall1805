package com.bawei.shopmall.find;


import androidx.fragment.app.Fragment;

import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.MyToolBar;
import com.bawei.framework.BaseFragment;
import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.shopmall.bawei.shopmall1805.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment<BasePresenter, IView> implements IView,MyToolBar.IToolBarClickListner{

    @Override
    protected int layoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {

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
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }
}
