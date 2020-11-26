package com.shopmall.bawei.shopmall1805.home;

import android.support.v4.app.Fragment;

import com.example.framework.BaseActivity;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.Fragment.TypeFragment;
import com.shopmall.bawei.shopmall1805.Fragment.HomeFragment;
import com.shopmall.bawei.shopmall1805.Fragment.FindFragment;
import com.shopmall.bawei.shopmall1805.Fragment.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.Fragment.UserFragment;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.FragmentAdpter;
import com.example.framework.MyViewPager;
import com.shopmall.bawei.shopmall1805.bean.TabEntity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<IPresenter, IView> {
    private MyViewPager vr;
    private CommonTabLayout common;
    private ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private FragmentAdpter fragmentAdpter;
    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {

        common.setTabData(tabEntitys);

        //适配器
        fragmentAdpter = new FragmentAdpter(getSupportFragmentManager(),fragmentArrayList);
        vr.setAdapter(fragmentAdpter);
        //点击底部切换fragment
        common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vr.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }


    @Override
    protected void initview() {
        //初始化控件
        vr = findViewById(R.id.vr);
        common = findViewById(R.id.common);

        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new TypeFragment());
        fragmentArrayList.add(new FindFragment());
        fragmentArrayList.add(new ShopCarFragment());
        fragmentArrayList.add(new UserFragment());
        //添加数据
        tabEntitys.add(new TabEntity("首页",R.mipmap.select_1,R.mipmap.default_1));
        tabEntitys.add(new TabEntity("分类",R.mipmap.select_2,R.mipmap.default_2));
        tabEntitys.add(new TabEntity("发现",R.mipmap.select_3,R.mipmap.default_3));
        tabEntitys.add(new TabEntity("购物车",R.mipmap.select_4,R.mipmap.default_4));
        tabEntitys.add(new TabEntity("个人中心",R.mipmap.select_1,R.mipmap.default_1));



    }


    @Override
    protected int getlayoutid() {
        return R.layout.activity_main;
    }
}
