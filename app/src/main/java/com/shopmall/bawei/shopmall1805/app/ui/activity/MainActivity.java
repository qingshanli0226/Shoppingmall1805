package com.shopmall.bawei.shopmall1805.app.ui.activity;



import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.entity.HomeDownTabEntity;
import com.shopmall.bawei.shopmall1805.app.ui.fragment.ClassifyFragment;
import com.shopmall.bawei.shopmall1805.app.ui.fragment.HomeFragment;
import com.shopmall.bawei.shopmall1805.app.ui.fragment.PeoPleCenterFragment;
import com.shopmall.bawei.shopmall1805.app.ui.fragment.SendFragment;
import com.shopmall.bawei.shopmall1805.app.ui.fragment.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.framework.BaseActivity;
import com.shopmall.bawei.shopmall1805.framework.ShopUserManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private FrameLayout framlayout;
    private CommonTabLayout commons;
    private ArrayList<CustomTabEntity> list_top;
    private List<Fragment> list;
    private HomeFragment firstFragment;
    private ClassifyFragment fenLeiFragment;
    private SendFragment sendFragment;
    private ShopCarFragment shopCarFragment;
    private PeoPleCenterFragment peoPleFragment;
    private int number = 0;
    @Override
    protected void initView() {
        framlayout = findViewById(R.id.framlayout);
        commons = findViewById(R.id.commons);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initData() {
        list_top=new ArrayList<>();
        list=new ArrayList<>();
        list_top.add(new HomeDownTabEntity("首页",R.drawable.main_home_press,R.drawable.main_home));
        list_top.add(new HomeDownTabEntity("分类",R.drawable.main_type_press,R.drawable.main_type));
        list_top.add(new HomeDownTabEntity("发现",R.drawable.main_community_press,R.drawable.main_community));
        list_top.add(new HomeDownTabEntity("购物车",R.drawable.main_cart_press,R.drawable.main_cart));
        list_top.add(new HomeDownTabEntity("个人中心",R.drawable.main_user_press,R.drawable.main_user));
        commons.setTabData(list_top);

        firstFragment = new HomeFragment();
        fenLeiFragment = new ClassifyFragment();
        sendFragment = new SendFragment();
        shopCarFragment = new ShopCarFragment();
        peoPleFragment = new PeoPleCenterFragment();

        list.add(firstFragment);
        list.add(fenLeiFragment);
        list.add(sendFragment);
        list.add(shopCarFragment);
        list.add(peoPleFragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.framlayout,firstFragment)
                .add(R.id.framlayout,fenLeiFragment)
                .add(R.id.framlayout,sendFragment)
                .add(R.id.framlayout,shopCarFragment)
                .add(R.id.framlayout,peoPleFragment)
                .hide(fenLeiFragment)
                .hide(sendFragment)
                .hide(shopCarFragment)
                .hide(peoPleFragment)
                .commit();

        commons.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                number = position ;
                if(position == 0){
                    getSupportFragmentManager().beginTransaction()
                            .show(firstFragment)
                            .hide(fenLeiFragment)
                            .hide(sendFragment)
                            .hide(shopCarFragment)
                            .hide(peoPleFragment)
                            .commit();
                }else if(position == 1){
                    getSupportFragmentManager().beginTransaction()
                            .show(fenLeiFragment)
                            .hide(firstFragment)
                            .hide(sendFragment)
                            .hide(shopCarFragment)
                            .hide(peoPleFragment)
                            .commit();
                }else if(position == 2){
                    getSupportFragmentManager().beginTransaction()
                            .show(sendFragment)
                            .hide(fenLeiFragment)
                            .hide(firstFragment)
                            .hide(shopCarFragment)
                            .hide(peoPleFragment)
                            .commit();
                }else if(position == 3){
                    if(ShopUserManager.getInstance().isUserLogin()){
                        getSupportFragmentManager().beginTransaction()
                                .show(shopCarFragment)
                                .hide(fenLeiFragment)
                                .hide(sendFragment)
                                .hide(firstFragment)
                                .hide(peoPleFragment)
                                .commit();
                    }else {
                        Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                        ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH)
                                .withInt(ShopmallConstant.TO_LOGIN_KEY,ShopmallConstant.TO_LOGIN_FROM_SHOPCAR_FRAGMTNT)
                                .navigation();
                    }
                }else if(position == 4){
                    getSupportFragmentManager().beginTransaction()
                            .show(peoPleFragment)
                            .hide(fenLeiFragment)
                            .hide(sendFragment)
                            .hide(shopCarFragment)
                            .hide(firstFragment)
                            .commit();
                }

            }
            @Override
            public void onTabReselect(int position) {

            }
        });

    }

}
