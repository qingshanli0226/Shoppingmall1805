package com.shopmall.bawei.user;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.adapter.UserfragmentAdapter;
import com.shopmall.bawei.fragment.UserLoginFragment;
import com.shopmall.bawei.fragment.UserRegistFragment;
import com.shopmall.bawei.framework.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
@Route(path = "/user/UserMainActivity")
public class UserMainActivity extends BaseActivity {
    private ViewPager userMainViewpager;
    private List<Fragment> fragments=new ArrayList<>();
    private UserfragmentAdapter userfragmentAdapter;

    @Override
    protected void oncreatePresenter() {

    }

    @Override
    protected void initEnvent() {

    }

    @Override
    protected void initview() {

        userMainViewpager = findViewById(R.id.user_main_viewpager);

    }

    @Override
    protected void initData() {
          fragments.add(new UserLoginFragment());
          fragments.add(new UserRegistFragment());


          userfragmentAdapter=new UserfragmentAdapter(getSupportFragmentManager(),fragments);
          userMainViewpager.setAdapter(userfragmentAdapter);

    }

    @Override
    protected int layoutid() {
        return R.layout.activity_main_user;
    }
}
