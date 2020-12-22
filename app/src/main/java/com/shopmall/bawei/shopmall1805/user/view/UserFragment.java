package com.shopmall.bawei.shopmall1805.user.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ARouterHelper;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.shopmall1805.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UserFragment extends BaseFragment<BasePresenter, IView> implements IView, View.OnClickListener, UserManager.IUserLoginChangedListener {
    private ImageButton ibUserSetting;
    private ImageButton ibUserMessage;
    private ImageButton ibUserIconAvator;
    private TextView tvUsername;
    private TextView tvUserPay;

    @Override
    protected void initView() {
        tvUserPay = (TextView) findViewById(R.id.tv_user_pay);
        ibUserSetting = (ImageButton) findViewById(R.id.ib_user_setting);
        ibUserMessage = (ImageButton) findViewById(R.id.ib_user_message);
        ibUserIconAvator = (ImageButton) findViewById(R.id.ib_user_icon_avator);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        if(UserManager.getInstance().isUserLogin()){
            tvUsername.setText(UserManager.getInstance().getName());
        } else {
            UserManager.getInstance().registerUserLoginChangeListener(this);
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_user_setting:

                break;
            case R.id.ib_user_message:

                break;
            case R.id.ib_user_icon_avator:
                toLogin();
                break;
            case R.id.tv_user_pay:
                toPay();
                break;
        }
    }

    private void toPay() {
        if(UserManager.getInstance().isUserLogin()) {
            startActivity(new Intent(getContext(), UnPaidActivity.class));
        } else {
            toLogin();
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onLogin(LoginBean loginBean){

    }

    private void toLogin() {
        if(!UserManager.getInstance().isUserLogin()) {
            ARouter.getInstance().build(ARouterHelper.USER_LOGIN).navigation();
        }
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initListener() {
        ibUserSetting.setOnClickListener(this);
        ibUserMessage.setOnClickListener(this);
        ibUserIconAvator.setOnClickListener(this);
        tvUserPay.setOnClickListener(this);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onUserLogin(LoginBean loginBean) {
        tvUsername.setText(loginBean.getName());

    }

    @Override
    public void onUserLogout() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        UserManager.getInstance().unRegisterUserLoginChangeListener(this);
    }
}
