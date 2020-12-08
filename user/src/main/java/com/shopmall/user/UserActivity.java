package com.shopmall.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.user.R;
import com.shopmall.user.fragment.UserLoginFragment;
import com.shopmall.user.fragment.UserRegisterFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/user/UserActivity")
public class UserActivity extends AppCompatActivity {
    private ViewPager userVp;

    private UserFragmentAdapter userFragmentAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userVp = (ViewPager) findViewById(R.id.userVp);

        fragments.add(new UserLoginFragment());
        fragments.add(new UserRegisterFragment());

        userFragmentAdapter = new UserFragmentAdapter(getSupportFragmentManager(),fragments);
        userVp.setAdapter(userFragmentAdapter);
    }
}