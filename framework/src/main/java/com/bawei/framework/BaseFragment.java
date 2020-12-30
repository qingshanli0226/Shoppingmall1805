package com.bawei.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.LoadingPage;
import com.bawei.common.view.MyToolBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class BaseFragment<P extends BasePresenter, V extends IView> extends Fragment implements MyToolBar.IToolBarClickListner {

    protected P httpPresenter;
    private LoadingPage loadingPage;
    protected MyToolBar toolBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return layoutId();
            }
        };
        return loadingPage;
    }

    protected <T extends View> T findViewById(int id) {
        return loadingPage.getSuccessView().findViewById(id);
    }

    public void showloading() {
        loadingPage.showLoadingView();
    }

    public void hideLoadingPage(boolean isSuccess, ErrorBean errorBean) {
        if (errorBean == null) {
            errorBean = new ErrorBean();
            errorBean.setErrorCode("0");
            errorBean.setErrorMessage("1");
        }
        if (isSuccess) {
            showSuccess();
        } else {
            showError(errorBean.getErrorMessage());
        }
    }

    public void showError(String errorString) {
        loadingPage.showErrorView(errorString);
    }

    public void showSuccess() {
        loadingPage.showSuccessView();
    }

    public void showEmptyPage() {
        loadingPage.showEmptyView();
    }

    protected abstract int layoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolBarClickListner(this);
        initView();
        initListener();
        initPresenter();
        initHttpData();
        if (httpPresenter != null) {
            httpPresenter.attachView((V) this);
        }
        initData();
    }

    protected void initView() {}

    protected void initHttpData() {}

    protected abstract void initListener();

    protected abstract void initPresenter();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectChange(boolean isConnected){
        if (isConnected){
            onConnected();
        }else {
            onDisConnected();
        }
    }

    protected void onDisConnected(){}

    protected void onConnected(){}

    @Override
    public void onRightClick() {}

    protected void initData() {}

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (httpPresenter != null) {
            httpPresenter.detachView();
        }
    }
}
