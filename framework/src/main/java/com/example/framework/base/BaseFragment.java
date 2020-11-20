package com.example.framework.base;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.framework.mvp.IPresenter;
import com.example.framework.mvp.IView;


public abstract class BaseFragment<T extends IPresenter,V extends IView> extends Fragment {
    protected T presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutID(), container, false);
        initView(inflate);
        initDate();
        initLisenter();
        if(presenter!=null){
            presenter.attchView((V)this);
        }
        return inflate;
    }

    protected abstract   void initDate();

    protected abstract void initLisenter();

    protected abstract void initView(View inflate);

    protected abstract int getLayoutID();

    @Override
    public void onDestroy() {
        super.onDestroy();
       if(presenter!=null){
           presenter.detachView();
       }
    }
}
