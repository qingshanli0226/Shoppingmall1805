package com.shopmall.bawei.shopmall1805.fragment2;

import android.view.View;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.login.LoginCountroller;
import com.bawei.deom.login.LoginImpl;
import com.shopmall.bawei.shopmall1805.R;

import bean.LoginBean;
import bean.RegisterBean;

public class RegisterFragment extends BaseFragment<LoginImpl, LoginCountroller.LoginView>implements LoginCountroller.LoginView {
    @Override
    protected void inPrine() {
         prine=new LoginImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getlayoutview() {
        return R.layout.registerfragment;
    }

    @Override
    public void login(LoginBean loginBean) {

    }

    @Override
    public void register(RegisterBean registerBean) {

    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }
}
