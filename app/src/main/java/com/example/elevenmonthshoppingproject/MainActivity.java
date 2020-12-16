package com.example.elevenmonthshoppingproject;

import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.elevenmonthshoppingproject.adapter.PageFragment;
import com.example.elevenmonthshoppingproject.classification.view.ClassIfiCationFragment;
import com.example.elevenmonthshoppingproject.foundfragment.view.FoundFragment;
import com.example.elevenmonthshoppingproject.home.view.HomeFragment;
import com.example.elevenmonthshoppingproject.personalcenterfragment.view.PersonalCenterFragment;
import com.example.framwork.ShopUserManager;
import com.example.shopercar.view.ShopCarFragment;
import com.example.framwork.BaseActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;
@Route(path = "/Main/MainActivity")
public class MainActivity extends BaseActivity implements View.OnClickListener, OnTabSelectListener {
    private BottomBar bottomBar;
    private ViewPager viewPager;
    private List<Fragment> fragments =new ArrayList<>();
    private HomeFragment homeFragment=new HomeFragment();
    private ClassIfiCationFragment classIfiCationFragment=new ClassIfiCationFragment();
    private FoundFragment foundFragment=new FoundFragment();
    private ShopCarFragment shopCarFragment=new ShopCarFragment();
    private PersonalCenterFragment personalCenterFragment=new PersonalCenterFragment();
    private PageFragment pageFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void iniView() {
        //获取token值
//        iniToken();



        fragments.add(homeFragment);
        fragments.add(classIfiCationFragment);
        fragments.add(foundFragment);
        fragments.add(shopCarFragment);
        fragments.add(personalCenterFragment);
        //初始化ID
        bottomBar = findViewById(R.id.bottomBar);
        viewPager = findViewById(R.id.view_pager);
        //点击事件
        bottomBar.setOnTabSelectListener(this);

        //适配器
        pageFragment=new PageFragment(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pageFragment);



    }

//    private void iniToken() {

//        Log.i("---","45"+token);
//
//
//    }



    @Override
    protected void iniData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }

    @Override
    public void onTabSelected(int tabId) {
        if (tabId==R.id.tab_home){
            viewPager.setCurrentItem(0);
        }else if (tabId==R.id.tab_classification){
            viewPager.setCurrentItem(1);
        }else if (tabId==R.id.tab_found){
            viewPager.setCurrentItem(2);
        }else if (tabId==R.id.tab_shop){
            if (!ShopUserManager.getInstance().isUserLogin()) {
                onBottomBarLogin();
            }else {
                viewPager.setCurrentItem(3);

//                ARouter.getInstance().build("/Main/MainActivity").navigation();
            }

        }else if (tabId==R.id.tab_personal_center){
            viewPager.setCurrentItem(4);
        }
    }

    private void onBottomBarLogin() {
        //如果bottom当前id和用户没有登陆的情况下 跳转登陆
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build("/login/loginActivity").navigation();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
