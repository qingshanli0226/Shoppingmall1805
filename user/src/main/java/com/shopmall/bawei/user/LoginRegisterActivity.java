package com.shopmall.bawei.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ARouterHelper;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.IPresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.user.login.view.LoginFragment;
import com.shopmall.bawei.user.register.view.RegisterFragment;

import java.util.ArrayList;
import java.util.List;
@Route(path = ARouterHelper.USER_LOGIN)
public class LoginRegisterActivity extends BaseActivity<IPresenter, IView> implements SwitchFragmentListener,IView {
    private List<Fragment> list = new ArrayList<>();

    public static final int TO_REG = 0;
    public static final int TO_LOGIN = 1;

    private FragmentManager manager;

    private int toLoginFromIndex = -1;


    @Override
    public void switchFragment(int index) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (index){
            case TO_REG:
                transaction.show(list.get(1)).hide(list.get(0)).commit();
                break;
            case TO_LOGIN:
                transaction.show(list.get(0)).hide(list.get(1)).commit();
                break;
        }
    }



    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login_register;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        toLoginFromIndex = intent.getIntExtra(UrlHelper.TO_LOGIN_KEY,-1);
        ARouter.getInstance().inject(this);

        manager = getSupportFragmentManager();
        list.add(new LoginFragment<>());
        list.add(new RegisterFragment<>());
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_login_register,list.get(0));
        transaction.add(R.id.fl_login_register,list.get(1));
        transaction.show(list.get(0));
        transaction.hide(list.get(1));
        transaction.commit();
    }


    public int getToLoginFromIndex(){
        return toLoginFromIndex;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }


    //    @Override
//    public void onItemClick(View v) {
//        FragmentTransaction transaction = manager.beginTransaction();
//        if(v.getId() == R.id.tv_login_register){
//            transaction.show(list.get(1)).hide(list.get(0)).commit();
//        } else if(v.getId() == R.id.tv_reg_register) {
//            transaction.show(list.get(0)).hide(list.get(1)).commit();
//        }
//    }
}
