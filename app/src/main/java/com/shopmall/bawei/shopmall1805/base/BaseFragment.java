package com.shopmall.bawei.shopmall1805.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<P extends IPresenter,V extends IView> extends Fragment {

    public Context context;
    protected P httpPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPresenter();
        initData();

        if (httpPresenter != null){
            httpPresenter.accatchView((V)this);
        }
    }

    private void initPresenter() {
    }

    private void initData() {
    }

    protected abstract View initView();


}
