package com.shopmall.bawei.user;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.BaseActivity;


@Route(path = "/usr/LoginRegisterActivity")
public class LoginRegisterActivity extends BaseActivity {
    private int toLoginFromIndex = -1;
    private ViewPager viewPager;
    private LoginRegisterAdapter loginRegisterAdapter;



    @Override
    protected void initView() {
        Intent intent = getIntent();//通过intent拿到Aroutter传递的参数值
        toLoginFromIndex = intent.getIntExtra(ShopmallConstant.TO_LOGIN_KEY,-1);

        viewPager = findViewById(R.id.viewPager);
        loginRegisterAdapter = new LoginRegisterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(loginRegisterAdapter);

        ARouter.getInstance().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_register;
    }


    //Activity提供方法，让Fragment调用，实现Fragment之间的跳转
    public void switchFragment(int index, String name) {
        viewPager.setCurrentItem(index);
        ((INameInterface)loginRegisterAdapter.getItem(index)).setName(name);
    }

    //获取跳转的index值
    public int getToLoginFromIndex() {
        return toLoginFromIndex;
    }

    public interface INameInterface {
        void setName(String name);
    }

}
