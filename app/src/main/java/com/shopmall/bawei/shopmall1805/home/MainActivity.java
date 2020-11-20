package com.shopmall.bawei.shopmall1805.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.Fragment.item_order;
import com.shopmall.bawei.shopmall1805.Fragment.item_pay;
import com.shopmall.bawei.shopmall1805.Fragment.item_point;
import com.shopmall.bawei.shopmall1805.Fragment.item_shopcar;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.tliteUser;

import java.util.ArrayList;

import framework.BaseActivity;

public class MainActivity extends BaseActivity {
    private ViewPager ViewPager;
    private CommonTabLayout commont;
    private ArrayList<Fragment> fragmentlist = new ArrayList<>();
    private ArrayList<CustomTabEntity> tiltelist = new  ArrayList<>();


    @Override
    protected void OnClickListener() {
        fragmentlist.add(new item_shopcar());//主页面
        fragmentlist.add(new item_order());//分类
        fragmentlist.add(new item_pay());//购物车
        fragmentlist.add(new item_point());//个人中心

        tiltelist.add(new tliteUser("主页",0,0));
        tiltelist.add(new tliteUser("分类",0,0));
        tiltelist.add(new tliteUser("购物车",0,0));
        tiltelist.add(new tliteUser("个人中心",0,0));

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
        ViewPager.setAdapter(adapter);
        commont.setTabData(tiltelist);
        ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                ViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    @Override
    protected void initData() {
        ViewPager = (ViewPager) findViewById(R.id.ViewPager);
        commont = (CommonTabLayout) findViewById(R.id.commont);
        fragmentlist.clear();
        tiltelist.clear();

    }
    @Override
    protected int getlayoutId() {
        return R.layout.activity_main ;
    }
}
