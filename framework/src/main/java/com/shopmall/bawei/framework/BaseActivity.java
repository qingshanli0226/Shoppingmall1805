package com.shopmall.bawei.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.view.LoadingPage;
import com.shopmall.bawei.framework.view.MyToolBar;

public abstract class BaseActivity<P extends IPresenter,V extends IView> extends AppCompatActivity implements MyToolBar.IToolBarClickListner {

    protected P httpPresenter;
    protected LoadingPage loadingPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return layoutId();
            }
        };
        setContentView(loadingPage);
        initView();
        initListener();
        initPresenter();
        if(httpPresenter != null){
            httpPresenter.attachView((V)this);
        }
        initData();
    }

    protected abstract int layoutId();

    protected void initView(){

    }

    protected abstract void initListener();

    protected abstract void initPresenter();

    protected void initData(){

    }

    public void showLoading(){
        loadingPage.showLoadingPage();
    }

    public void hideLoading(boolean isSuccess, ErrorBean errorBean){
        if (isSuccess) {
            showSuccess();
        } else {
            showError(errorBean.getErrorMessage());
        }
    }
    public void showError(String errorMessage) {
        loadingPage.showErrorPage(errorMessage);
    }

    public void showSuccess() {
        loadingPage.showSuccessView();
    }
    public void hideLoadingPage(boolean isSuccess, ErrorBean errorBean) {
        if (isSuccess) {
            showSuccess();
        } else {
            showError(errorBean.getErrorMessage());
        }
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(httpPresenter != null) {
            httpPresenter.detachView();
        }
    }
}
