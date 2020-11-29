package com.bawei.user.view;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bawei.framework.BaseActivity;
import com.bawei.user.R;

@Route(path = "/user/LoginRegisterActivity")
public class LoginRegisterActivity extends BaseActivity {

    private ViewPager viewPager;
    private LoginRegisterAdapter adapter;
    private int toLoginFromIndex = -1;

    @Override
    protected int layoutId() {
        return R.layout.activity_login_register;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewPager);
        adapter = new LoginRegisterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    public void switchFragemnt(int index, String name) {
        viewPager.setCurrentItem(index);
    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initPresenter() {

    }

    public void switchFragment(int index, String name) {
        viewPager.setCurrentItem(index);
        ((INameInterface) adapter.getItem(index)).setName(name);
    }

    public interface INameInterface {
        void setName(String name);
    }

    public int getToLoginFromIndex() {
        return toLoginFromIndex;
    }
}
