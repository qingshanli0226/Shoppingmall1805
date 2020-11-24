package com.shopmall.bawei.shopmall1805.type.view;

import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.net.TypeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.type.contract.TypeContract;
import com.shopmall.bawei.shopmall1805.type.contract.TypeImpl;

public class ListFragment<P extends TypeImpl,V extends TypeContract.ITypeView> extends BaseFragment<P,V> implements TypeContract.ITypeView {
    @Override
    protected int layoutId() {
        return R.layout.fragment_list;
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
    public void onType(TypeBean typeBean) {

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
