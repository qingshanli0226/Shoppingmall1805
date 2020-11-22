package com.shopmall.bawei.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shopmall.bawei.common.MyToolBar;

public abstract class BaseFragment<P extends BasePresenter,V extends IView> extends Fragment implements MyToolBar.IToolBarClickListner {
    private View view;
    protected P httpPresenter;
    private ProgressBar loadingBar;
    private MyToolBar toolBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layoutId(),container,false);
        return view;
    }

    protected <T extends View> T findViewById(int id){
        return view.findViewById(id);
    }

    protected abstract int layoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolBarClickListner(this);
        initView();
        initListener();
        initPresenter();
        httpPresenter.attachView((V)this);
        initData();
    }

    protected void initView() {

    }



    protected abstract void initListener();

    protected abstract void initPresenter();

    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        httpPresenter.detachView();
    }
}
