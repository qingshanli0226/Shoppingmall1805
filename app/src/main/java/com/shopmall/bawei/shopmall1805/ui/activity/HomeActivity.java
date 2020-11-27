package com.shopmall.bawei.shopmall1805.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.IPresenter;
import com.example.framework.IVIew;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.entity.CommonEntity;
import com.shopmall.bawei.shopmall1805.ui.fragment.ClassfiyFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.FindFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.PersonalFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.PrimereFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.ShoppingFragment;
import com.shopmall.bawei.shopmall1805.ui.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<IPresenter, IVIew> {

    private CommonTabLayout commonTabLayout;
    private NoScrollViewPager viewPager;
    private ArrayList<CustomTabEntity> commonList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initView() {
        commonTabLayout = findViewById(R.id.commontablayout);
        viewPager = findViewById(R.id.viewpager);
        addData();
    }


    private void addData() {
        commonList.add(new CommonEntity("首页",R.mipmap.default_1,R.mipmap.select_1));
        commonList.add(new CommonEntity("分类",R.mipmap.default_2,R.mipmap.select_2));
        commonList.add(new CommonEntity("发现",R.mipmap.default_3,R.mipmap.select_3));
        commonList.add(new CommonEntity("购物车",R.mipmap.default_4,R.mipmap.select_4));
        commonList.add(new CommonEntity("个人中心",R.mipmap.default_1,R.mipmap.select_1));

        fragmentList.add(new PrimereFragment());
        fragmentList.add(new ClassfiyFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new ShoppingFragment());
        fragmentList.add(new PersonalFragment());
    }

    @Override
    protected void initData() {
        addFragment();
        addCommontab();
    }

    private void addCommontab() {
        commonTabLayout.setTabData(commonList);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                if (position==3){
                    ARouter.getInstance()
                            .build("/duoduo/shopcar")
                            .navigation();
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void addFragment() {
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
