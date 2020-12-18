package com.shopmall.bawei.user;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.example.framework.BaseActivity;
import com.shopmall.bawei.framework.example.framework.IPresenter;
import com.shopmall.bawei.framework.example.framework.IView;
import com.shopmall.bawei.framework.example.framework.MyViewPager;
import com.shopmall.bawei.user.adpter.FragmentAdpter;
import com.shopmall.bawei.user.fragment.LoginFragment;
import com.shopmall.bawei.user.fragment.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path="/user/LoginRegisterActivity")
public class LoginRegisterActivity extends BaseActivity<IPresenter, IView> {
    public static MyViewPager loginRegister;
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
        loginRegister.setAdapter(fragmentAdpter);
    }

    @Override
    protected void initview() {
        //初始化控件
        loginRegister = findViewById(R.id.login_register);
        //添加fragment
        list.add(loginFragment);
        list.add(registerFragment);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_login_register;
    }
}
