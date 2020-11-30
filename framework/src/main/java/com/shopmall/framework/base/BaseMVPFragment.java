package com.shopmall.framework.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.shopmall.bawei.framework.R;
import com.shopmall.framework.mvp.IPresenter;

public abstract class BaseMVPFragment<P extends IPresenter> extends Fragment {
    protected P mPresenter;
    protected ProgressBar loadingBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(fragmentid(), null);
        createViewid(inflate);
        loadingBar = inflate.findViewById(R.id.loadingBar);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
