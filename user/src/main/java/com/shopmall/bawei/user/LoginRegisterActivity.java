package com.shopmall.bawei.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.net.MyNetApi;

@Route(path = "/usr/LoginRegisterActivity")
public class LoginRegisterActivity extends AppCompatActivity {
    private int toLoginFromIndex = -1;
    private ViewPager viewPager;
    private LoginRegisterAdapter loginRegisterAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        ARouter.getInstance().inject(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();//通过intent拿到Aroutter传递的参数值
        toLoginFromIndex = intent.getIntExtra(Constants.TO_LOGIN_KEY,-1);

        viewPager = findViewById(R.id.viewPager);
        loginRegisterAdapter = new LoginRegisterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(loginRegisterAdapter);

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
