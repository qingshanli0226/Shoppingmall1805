package com.shopmall.bawei.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.MyViewPager;
import com.shopmall.bawei.user.fragment.LoginFragment;
import com.shopmall.bawei.user.fragment.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/duoduo/user")
public class LoginRegisterActivity extends AppCompatActivity {

    public static MyViewPager vrLoginRegister;
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        ARouter.getInstance().inject(this);
        initView();
        list.add(new LoginFragment());
        list.add(new RegisterFragment());

        vrLoginRegister.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    private void initView() {
        vrLoginRegister = (MyViewPager) findViewById(R.id.vr_login_register);
    }
}