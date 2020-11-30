package com.shopmall.framework.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopmall.framework.mvp.Presenter;
import com.shopmall.framework.view.LoadingPage;

public abstract class BaseMVPFragment<P extends Presenter> extends Fragment {
    protected P mPresenter;
    protected LoadingPage loadingPage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        loadingPage = new LoadingPage(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return fragmentid();
            }
        };

        return loadingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createViewid(loadingPage);
        createData();
        createEnvent();
    }

    /**
     * 获取控件id
     */
    protected abstract void createViewid(View inflate);

    /**
     * 初始化事件
     */
    protected abstract void createEnvent();

    /**
     * 初始化数据
     */
    protected abstract void createData();

    /**
     * 获取fragment布局idn
     */
    protected abstract int fragmentid();

    /**
     * 创建P 层
     */
    protected abstract void createPresenter();
}
