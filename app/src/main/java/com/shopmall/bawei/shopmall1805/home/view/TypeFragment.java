package com.shopmall.bawei.shopmall1805.home.view;


import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.shopmall1805.R;

public class TypeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        showMessage("点击了搜索按钮");
    }
}
