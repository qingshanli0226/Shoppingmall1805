package com.shopmall.bawei.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends BasePresenter,V extends IView> extends Fragment {
    private View view;
    protected P httpPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layoutId(),container,false);
        return view;
    }

    protected abstract int layoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initListener();
        initPresenter();
        httpPresenter.attachView((V)this);
        initData();
    }

    private void initView() {

    }

    protected abstract void initListener();

    protected abstract void initPresenter();

    private void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        httpPresenter.detachView();
    }
}
