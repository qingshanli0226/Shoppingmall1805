package com.shopmall.bawei.user;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.MyViewPager;
import com.shopmall.bawei.user.adpter.FragmentAdpter;
import com.shopmall.bawei.user.view.LoginFragment;
import com.shopmall.bawei.user.view.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path="/goodcar/Shpping_car_Activity")
public class LoginRegisterActivity extends BaseActivity<IPresenter,IView> {
    public static MyViewPager vrLoginRegister;
    private LoginFragment loginFragment = new LoginFragment();
    private RegisterFragment registerFragment = new RegisterFragment();
    private List<Fragment> list = new ArrayList<>();
    private FragmentAdpter fragmentAdpter;
    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {
        ARouter.getInstance().inject(this);
        fragmentAdpter = new FragmentAdpter(getSupportFragmentManager(),list);
        vrLoginRegister.setAdapter(fragmentAdpter);
    }

    @Override
    protected void initview() {
        //初始化控件
        vrLoginRegister = findViewById(R.id.vr_login_register);
        //添加fragment
        list.add(loginFragment);
        list.add(registerFragment);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_login_register;
    }
}
