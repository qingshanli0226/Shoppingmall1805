package com.shopmall.bawei.shopmall1805.home.view.fragment;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomeImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MineFragment extends BaseFragment<HomeImpl, HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener,ShopUserManager.IUserLoginChangedListener {
    TextView loginTv;
    @Override
    protected int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        super.initView();
        loginTv = findViewById(R.id.username);
        if (ShopUserManager.getInstance().isUserLogin()) {
            loginTv.setText(ShopUserManager.getInstance().getName());
        } else {
            ShopUserManager.getInstance().registerUserLoginChangeListener(this);
        }
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ShopUserManager.getInstance().isUserLogin()) {
                    ARouter.getInstance().build(Constants.LOGIN_ACTIVITY_PATH).navigation();
                }
            }
        });
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onLogin(LoginBean loginBean) {
        if (ShopUserManager.getInstance().getToken()!=null){
            ShopUserManager.getInstance().registerUserLoginChangeListener(this);
        }else {
            ARouter.getInstance().build(Constants.LOGIN_ACTIVITY_PATH).navigation();
        }
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new HomeImpl();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onUserLogin(LoginBean loginBean) {
        loginTv.setText("欢迎您："+loginBean.getName());
    }

    @Override
    public void onUserLogout() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ShopUserManager.getInstance().unRegisterUserLoginChangeListener(this);
    }
}