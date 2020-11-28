package com.bw.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.common.view.LoadingPage;


public abstract class BaseFragment<P extends IPresenter,V extends IView> extends Fragment {

    public Context context;
    protected P httpPresenter;
    private LoadingPage loadingPage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(getLayoutId(), null);

        loadingPage = new LoadingPage(getActivity()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        return loadingPage;
    }

    protected abstract int getLayoutId();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(loadingPage.successView());
        initPresenter();
        if (httpPresenter != null){
            httpPresenter.accatchView((V)this);
        }
        initData();


    }

    protected void initPresenter() {
    }

    protected void initData() {
    }

    protected abstract void initView(View view);

    protected  void myToast(String message){
        Toast.makeText(getContext(),message+"",Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        loadingPage.showLoadingPage();
    }
    public void hideLoadingPage(boolean isSuccess) {
        if (isSuccess) {
            showSuccess();
        } else {
            showError("正在加载。。。。。。。");
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
