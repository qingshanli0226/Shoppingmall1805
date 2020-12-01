package com.example.framework.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.mvp.IPresenter;
import com.example.framework.mvp.IView;
import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;
import com.shopmall.bawei.framework.R;
import com.shoppmall.common.adapter.error.ErrorBean;


public abstract class BaseFragment<T extends IPresenter,V extends IView> extends Fragment implements ToolBar.IToolBarClickListner {
    private LoadingPage loadingPage;
    protected T presenter;
    protected ToolBar toolBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        loadingPage=new LoadingPage(getActivity()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutID();
            }
        };
        toolBar = findViewById(R.id.toolbar);//在这里实例化toolbar
        toolBar.setToolBarClickListner(this);
        initView();

        if(presenter!=null){
            presenter.attchView((V)this);
        }
        return loadingPage;
    }
    public <T extends View> T findViewById(@IdRes int id){
        return loadingPage.findViewById(id);
    }
    @Override
    public void onRightClick() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
        initLisenter();
    }

    protected abstract   void initDate();

    protected abstract void initLisenter();

    protected abstract void initView();

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
    public void showEmptyCarPage() {
        loadingPage.showEmptyCarPage();
    }
}
