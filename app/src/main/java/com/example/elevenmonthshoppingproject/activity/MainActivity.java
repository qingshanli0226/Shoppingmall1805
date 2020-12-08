package com.example.elevenmonthshoppingproject.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.adapter.FragAdapter;
import com.example.elevenmonthshoppingproject.commontablayout.MyCommonTab;
import com.example.elevenmonthshoppingproject.fragment.FirstShops;
import com.example.elevenmonthshoppingproject.fragment.ShopTypeFragment;
import com.example.elevenmonthshoppingproject.fragment.Fragment3;
import com.example.elevenmonthshoppingproject.fragment.Fragment4;
import com.example.elevenmonthshoppingproject.fragment.Fragment5;
import com.example.net.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;

    private List<Fragment> fragments=new ArrayList<>();
    private FirstShops fragment1=new FirstShops();
    private ShopTypeFragment fragment2=new ShopTypeFragment();
    private Fragment3 fragment3=new Fragment3();
    private Fragment4 fragment4=new Fragment4();
    private Fragment5 fragment5=new Fragment5();

    private CommonTabLayout common;

    private ArrayList<CustomTabEntity> tabEntitys=new ArrayList<>();
    private FragAdapter fragAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void iniData() {



        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        viewPager = findViewById(R.id.view_pager);
        fragAdapter=new FragAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(fragAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    common.setCurrentTab(0);
                }else if (position==1){
                    common.setCurrentTab(1);
                }else if (position==2){
                    common.setCurrentTab(2);
                }else if (position==3){
                    common.setCurrentTab(3);
                }else if (position==4){
                    common.setCurrentTab(4);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        common = findViewById(R.id.common);
        tabEntitys.add(new MyCommonTab("首页",R.drawable.four,R.drawable.four2));
        tabEntitys.add(new MyCommonTab("分类",R.drawable.four,R.drawable.four2));
        tabEntitys.add(new MyCommonTab("发现",R.drawable.four,R.drawable.four2));
        tabEntitys.add(new MyCommonTab("购物车",R.drawable.four,R.drawable.four2));
        tabEntitys.add(new MyCommonTab("个人中心",R.drawable.four,R.drawable.four2));

        common.setTabData(tabEntitys);
        common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position==0){
                    viewPager.setCurrentItem(0);
                }else if (position==1){
                    viewPager.setCurrentItem(1);
                }else if (position==2){
                    viewPager.setCurrentItem(2);
                }else if (position==3){
                    viewPager.setCurrentItem(3);
                }else if (position==4){
                    viewPager.setCurrentItem(4);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void iniView() {

    }


}
