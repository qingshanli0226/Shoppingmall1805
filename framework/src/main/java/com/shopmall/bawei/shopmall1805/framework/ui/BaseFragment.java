package com.shopmall.bawei.shopmall1805.framework.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopmall.bawei.shopmall1805.framework.IPresenter;

public abstract class BaseFragment<P extends IPresenter> extends Fragment {
    protected P iPresenter;
    protected View iView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return iView = inflater.inflate(bandLyoaut(),null);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(iView);
        createPresenter();
        initData();
        initEvent();
    }

    protected abstract void initEvent();

    protected abstract void createPresenter();

    protected abstract void initData();

    protected abstract void initView(View iView);

    protected abstract int bandLyoaut();
}
