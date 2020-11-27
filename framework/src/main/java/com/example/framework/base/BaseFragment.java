package com.example.framework.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.mvp.IPresenter;
import com.example.framework.mvp.IView;
import com.example.framework.view.LoadingPage;
import com.shoppmall.common.adapter.error.ErrorBean;


public abstract class BaseFragment<T extends IPresenter,V extends IView> extends Fragment {
    private LoadingPage loadingPage;
    protected T presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutID(), container, false);
        initView(inflate);
        loadingPage=new LoadingPage(getActivity()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutID();
            }
        };
        if(presenter!=null){
            presenter.attchView((V)this);
        }
        return loadingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
        initLisenter();
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
    public void showLoading() {
        loadingPage.showLoadingPage();
    }
    public void hideLoadingPage(boolean isSuccess, ErrorBean errorBean) {
        if (isSuccess) {
            showSuccess();
        } else {
            showError(errorBean.getErrorMessage());
        }
    }
    public void showError(String errorMsg) {
        loadingPage.showErrorPage(errorMsg);
    }
    public void showSuccess() {
        loadingPage.showSuccessView();
    }
    public void showEmptyPage() {
        loadingPage.showEmptyPage();
    }
}
