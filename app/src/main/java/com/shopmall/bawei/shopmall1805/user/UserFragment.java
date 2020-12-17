package com.shopmall.bawei.shopmall1805.user;

import android.view.View;
import android.widget.TextView;

import com.example.framework.base.BaseFragment;
import com.example.framework.manager.UserManager;
import com.example.net.bean.LoginBean;
import com.shopmall.bawei.shopmall1805.R;


public class UserFragment extends BaseFragment implements UserManager.IUserLoginChangedListener {
    private TextView tvUsername;
    private TextView tvUserPay;

    @Override
    protected void initDate() {
        UserManager.getInstance().registerUserLoginChangeListener(this);
        if(UserManager.getInstance().isLogin()){
            tvUsername.setText(UserManager.getInstance().getName());
        }
    }

    @Override
    protected void initLisenter() {
        tvUserPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initView() {

        tvUsername = (TextView) findViewById(R.id.tv_username);
        tvUserPay = (TextView) findViewById(R.id.tv_user_pay);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_user;
    }

    @Override
    public void onUserLogin(LoginBean.ResultBean user) {
        tvUsername.setText(UserManager.getInstance().getName());
    }

    @Override
    public void onUserLogout() {
        tvUsername.setText("登陆/注册");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UserManager.getInstance().unRegisterUserLoginChangeListener(this);
    }
}