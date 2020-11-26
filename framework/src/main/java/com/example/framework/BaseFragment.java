package com.example.framework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<P extends IPresenter,V extends IVIew> extends Fragment {

    protected P httpPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(),container,false);

        initView(inflate);
        initPresenter();
        initData();
        if (httpPresenter!=null){
            httpPresenter.attchView((V)this);
        }
        return inflate;
    }



    protected abstract void initView(View inflate);

    protected abstract void initPresenter();


    protected abstract int getLayoutId();



    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (httpPresenter!=null){
            httpPresenter.ondechView();
        }
    }
}

