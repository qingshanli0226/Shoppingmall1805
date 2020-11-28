package com.bw.user;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.common.BaseActivity;

import com.bw.net.bean.ShopmallConstant;
import com.shopmall.bawei.user.R;


@Route(path = "/usr/LoginRegisterActivity")
public class LoginRegisterActivity extends BaseActivity {
    private int toLoginFromIndex = -1;
    private ViewPager viewPager;
    private LoginRegisterAdapter loginRegisterAdapter;



    @Override
    protected void initView() {

        viewPager = findViewById(R.id.viewPager);
        loginRegisterAdapter = new LoginRegisterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(loginRegisterAdapter);
        viewPager.setCurrentItem(0);

        ARouter.getInstance().inject(this);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_register;
    }


    //Activity提供方法，让Fragment调用，实现Fragment之间的跳转
    public void switchFragment(int index) {
        viewPager.setCurrentItem(index);
    }

//    获取跳转的index值
    public int getToLoginFromIndex() {
        return toLoginFromIndex;
    }

    public interface INameInterface {
        void setName(String name);
    }

}
