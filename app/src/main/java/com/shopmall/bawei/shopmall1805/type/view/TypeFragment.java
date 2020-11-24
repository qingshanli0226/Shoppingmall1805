package com.shopmall.bawei.shopmall1805.type.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.net.TagBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.type.contract.TagContract;
import com.shopmall.bawei.shopmall1805.type.contract.TagImpl;

public class TypeFragment<P extends TagImpl,V extends TagContract.ITagView> extends BaseFragment implements TagContract.ITagView {

    @Override
    protected int layoutId() {
        return R.layout.fragment_type;
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
    public void onError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}