package com.shopmall.bawei.shopmall1805.home;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.fragment.Fragmentclassify;
import com.shopmall.bawei.shopmall1805.fragment.Fragmenthomepage;
import com.shopmall.bawei.shopmall1805.fragment.Fragmentpersonage;
import com.shopmall.bawei.shopmall1805.fragment.Fragmentshop;
import com.shopmall.bawei.shopmall1805.tliteUser;

import java.util.ArrayList;

import framework.BaseActivity;
import framework.ShopUserManager;
import view.ShopmallConstant;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private CommonTabLayout commont;
    private ArrayList<Fragment> fragmentlist = new ArrayList<>();
    private ArrayList<CustomTabEntity> tiltelist = new  ArrayList<>();


    @Override
    protected void OnClickListener() {
        fragmentlist.add(new Fragmenthomepage());//主页
        fragmentlist.add(new Fragmentclassify());//分类
        fragmentlist.add(new Fragmentshop());//购物车
        fragmentlist.add(new Fragmentpersonage());//个人中心
        tiltelist.add(new tliteUser("主页",0,0));
        tiltelist.add(new tliteUser("分类",0,0));
        tiltelist.add(new tliteUser("购物车",0,0));
        tiltelist.add(new tliteUser("个人中心",0,0));
        //ARouter注入
        ARouter.getInstance().inject(this);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentlist.size();
            }

            @Override
            public Fragment getItem(int i) {
                return fragmentlist.get(i);
            }
        };
        viewPager.setAdapter(adapter);
        commont.setTabData(tiltelist);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                    commont.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        commont.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                if (position==2 && !ShopUserManager.getInstance().isUserLogin()){
                    //如果用户没有登录
                    ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH).withInt(ShopmallConstant.TO_LOGIN_KEY, ShopmallConstant.TO_LOGIN_FROM_SHOPCAR_FRAGMTNT).navigation();//跳转到loginActivity
                    //finish();
                    return;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    @Override
    protected void initData() {
        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        commont = (CommonTabLayout) findViewById(R.id.commont);
        fragmentlist.clear();
        tiltelist.clear();

        switchFragmentByIndex(getIntent());

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        switchFragmentByIndex(intent);
    }
    private void switchFragmentByIndex(Intent intent) {
        int index = intent.getIntExtra("index", 0);
        setLog(">>",""+index);
        if (index == ShopmallConstant.BUTTON_LOGIN_INDEX1){
            viewPager.setCurrentItem(2);
            setLog(">>","进入购物车"+index);
        }else if (index == ShopmallConstant.BUTTON_LOGIN_INDEX2){
            viewPager.setCurrentItem(0);
            setLog(">>","进入首页"+index);
        }
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_main ;
    }
}
