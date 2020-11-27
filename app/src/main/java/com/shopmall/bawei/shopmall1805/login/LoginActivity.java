package com.shopmall.bawei.shopmall1805.login;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.MyFragmentPager;
import com.shopmall.bawei.shopmall1805.fragment2.LoginFragment;
import com.shopmall.bawei.shopmall1805.fragment2.RegisterFragment;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private ViewPager pager;

    MyFragmentPager myFragmentPager;
    ArrayList<Fragment> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pager = (ViewPager) findViewById(R.id.pager);
        arrayList.add(new LoginFragment());
        arrayList.add(new RegisterFragment());
        myFragmentPager=new MyFragmentPager(getSupportFragmentManager(),arrayList);
        pager.setAdapter(myFragmentPager);
    }
}
