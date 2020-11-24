package com.shopmall.bawei.shopmall1805.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.CoutomEntiy;
import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.MyFragmentPager;
import com.shopmall.bawei.shopmall1805.fragment.FaxianFragment;
import com.shopmall.bawei.shopmall1805.fragment.FirstFragment;
import com.shopmall.bawei.shopmall1805.fragment.Myfragment;
import com.shopmall.bawei.shopmall1805.fragment.ShoppingFragment;
import com.shopmall.bawei.shopmall1805.fragment.TypeFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private CommonTabLayout com;
    ArrayList<CustomTabEntity> tabEntitys=new ArrayList<>();
      ArrayList<Fragment> arrayList=new ArrayList<>();
    MyFragmentPager myFragmentPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);
        com = (CommonTabLayout) findViewById(R.id.com);
        indata();
        myFragmentPager=new MyFragmentPager(getSupportFragmentManager(),arrayList);
        pager.setAdapter(myFragmentPager);
        com.setTabData(tabEntitys);
        com.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                pager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void indata() {
        arrayList.add(new FirstFragment());
        arrayList.add(new TypeFragment());
        arrayList.add(new FaxianFragment());
        arrayList.add(new ShoppingFragment());
        arrayList.add(new Myfragment());
        tabEntitys.add(new CoutomEntiy("首页",R.mipmap.top_bar_right_home_btn,R.mipmap.top_bar_right_home_btn));
        tabEntitys.add(new CoutomEntiy("分类",R.mipmap.main_type,R.mipmap.main_type));
        tabEntitys.add(new CoutomEntiy("发现",R.mipmap.icon_search_white,R.mipmap.icon_search_white));
        tabEntitys.add(new CoutomEntiy("购物车",R.mipmap.main_cart,R.mipmap.main_cart));
        tabEntitys.add(new CoutomEntiy("个人中心",R.mipmap.icon_callserver_unpressed,R.mipmap.icon_callserver_unpressed));
    }
}
