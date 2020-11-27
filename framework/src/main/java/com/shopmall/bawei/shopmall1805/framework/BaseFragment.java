package com.shopmall.bawei.shopmall1805.framework;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;
    private String TAG;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        loadingPage = new LoadingPage(getActivity()) {
            @Override
            protected int getSuccessLyaout() {
                return getLayoutId();
            }
        };
        return loadingPage;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    //注解。表示一个资源id，不能随便传递一个整型
    public <T extends View> T findViewById(@IdRes int id) {
        return loadingPage.findViewById(id);
    }
    protected void printLog(String message) {
        Log.d(TAG, message);
    }
    protected void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
    protected abstract void initData();

    protected abstract void initView();

    public void showLoading() {
        loadingPage.showLoadingPage();
    }
    public void hideLoadingPage(boolean isSuccess) {
        showSuccess();
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
