package com.example.framwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framwork.view.LoginPage;
import com.example.framwork.view.ToolBar;
import com.example.net.NetBusinessException;
import com.example.net.bean.ErrorBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment extends Fragment implements ToolBar.IToolBarClickListner {
    private LoginPage loadingPage;
    protected ToolBar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoginPage(getActivity()) {
            @Override
            protected int getsuccessId() {
                return getLayoutId();
            }
        };
        toolbar = findViewById(R.id.toolbar);//在这里实例化toolbar
//        toolbar.setToolBarClickListner(this);

//        EventBus.getDefault().register(this);

        return loadingPage;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    protected abstract void initData();
    protected abstract void initView();

    //注解。表示一个资源id，不能随便传递一个整型
    public <T extends View> T findViewById(@IdRes int id) {
        return loadingPage.findViewById(id);
    }

    protected void launchActivity(Class launcActivityClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(getActivity(), launcActivityClass);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectChange(Boolean isConnected) {
        if (isConnected) {
            onConnected();
        } else {
            onDisConnected();
        }
    }

    protected void onDisConnected() {

    }

    protected void onConnected() {

    }

    @Override
    public void onLeftClick() {
        getActivity().finish();//左侧点击事件，大部分都是销毁当前的Activity，所以定义一个默认实现
    }

    @Override
    public void onRightClick() {//右侧不能确定点击之后，子类的行为，所以在基类中没必要定义一个默认实现，具体实现让子类完成.
    }

    public void showLoading() {
        loadingPage.loadingPage();
    }

    protected abstract int getLayoutId();
    public void hideLoadingPage(boolean isSuccess, ErrorBean errorBean) {
        if (isSuccess) {
            showSuccess();
        } else {
            showError(errorBean.getErrorMessage());
        }
    }
    public void showError(String errorMsg) {
        loadingPage.showError(errorMsg);
    }
    public void showSuccess() {
        loadingPage.showsuccessPage();
    }

    public void showEmptyPage() {
        loadingPage.showEnptyPage();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
    }




}
