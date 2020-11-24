package com.shopmall.bawei.shopmall1805.app;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.framework.base.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.MyVP;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.bean.VPTab;
import com.shopmall.bawei.shopmall1805.find.FindFragment;
import com.shopmall.bawei.shopmall1805.home.HomeFragment;
import com.shopmall.bawei.shopmall1805.shoppingcar.ShoppingCarFragment;
import com.shopmall.bawei.shopmall1805.type.TypeFragment;
import com.shopmall.bawei.shopmall1805.user.UserFragment;
import com.shoppmall.common.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private ArrayList<CustomTabEntity> tabs=new ArrayList<>();
    private List<Fragment> fragments=new ArrayList<>();

    @BindView(R.id.vp_main)
    MyVP vpMain;
    @BindView(R.id.comm_main)
    CommonTabLayout commMain;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        tabs.add(new VPTab("首页",R.drawable.main_home_press,R.drawable.main_home));
        tabs.add(new VPTab("分类",R.drawable.main_type_press,R.drawable.main_type));
        tabs.add(new VPTab("发现",R.drawable.main_community_press,R.drawable.main_community));
        tabs.add(new VPTab("购物车",R.drawable.main_cart_press,R.drawable.main_cart));
        tabs.add(new VPTab("个人中心",R.drawable.main_user_press,R.drawable.main_user));
        commMain.setTabData(tabs);
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new FindFragment());
        fragments.add(new ShoppingCarFragment());
        fragments.add(new UserFragment());
        vpMain.setScanScroll(false);
        vpMain.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments));
        commMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpMain.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        vpMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                commMain.setCurrentTab(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

}