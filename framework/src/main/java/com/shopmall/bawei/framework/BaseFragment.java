package com.shopmall.bawei.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.view.LoadingPage;
import com.shopmall.bawei.framework.view.MyToolBar;

public abstract class BaseFragment<P extends BasePresenter,V extends IView> extends Fragment implements MyToolBar.IToolBarClickListner {
    protected P httpPresenter;
    private ProgressBar loadingBar;
    private MyToolBar toolBar;

    protected LoadingPage loadingPage;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(getContext()) {
//            @Override
//            protected int getSuccessLayoutId() {
//                return layoutId();
//            }

            @Override
            protected View getSuccessLayoutView() {
                return inflater.inflate(layoutId(),container,false);
            }
        };

        return loadingPage;
    }

    protected <T extends View> T findViewById(int id){
        return loadingPage.getSuccessView().findViewById(id);
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
        if(httpPresenter != null) {
            httpPresenter.attachView((V) this);
        }
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
        if(httpPresenter != null) {
            httpPresenter.detachView();
        }
    }
}
