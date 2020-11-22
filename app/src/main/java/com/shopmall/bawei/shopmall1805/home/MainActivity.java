package com.shopmall.bawei.shopmall1805.home;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.IPresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.view.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<IPresenter, IView> {

    private Fragment[] fragments = new Fragment[] {
            new HomeFragment()
    };

    private Fragment currentFragment;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        initPermission();
        Fragment fragment = fragments[0];
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(currentFragment != null){
            fragmentTransaction.hide(currentFragment);
        }
        if(fragment.isAdded()){
            fragmentTransaction.show(fragment).commit();
        } else {
            fragmentTransaction.add(R.id.frameLayoutId,fragment,fragment.getClass().getSimpleName()).commit();
        }
        currentFragment = fragment;
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断当前系统版本是不是大于等于23
            Toast.makeText(this, "系统版本大于23，需动态申请权限", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            Toast.makeText(this, "系统版本低于23，所以无需动态申请权限", Toast.LENGTH_SHORT).show();
        }//这个就是在代码里做了版本适配(兼容适配),确保了应用程序在15到29之间，动态权限申请不会出现找不到方法的错误

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initPresenter() {

    }
}
