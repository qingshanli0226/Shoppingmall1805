package com.shopmall.bawei.shopmall1805.app.ui;



import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.entity.MyTopEntity;
import com.shopmall.bawei.shopmall1805.app.fragment.FenLeiFragment;
import com.shopmall.bawei.shopmall1805.app.fragment.FirstFragment;
import com.shopmall.bawei.shopmall1805.app.fragment.PeoPleFragment;
import com.shopmall.bawei.shopmall1805.app.fragment.SendFragment;
import com.shopmall.bawei.shopmall1805.app.fragment.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.framework.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private FrameLayout framlayout;
    private CommonTabLayout commons;
    private ArrayList<CustomTabEntity> list_top;
    private List<Fragment> list;
    private FirstFragment firstFragment;
    private FenLeiFragment fenLeiFragment;
    private SendFragment sendFragment;
    private ShopCarFragment shopCarFragment;
    private PeoPleFragment peoPleFragment;
    @Override
    protected int bandLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        framlayout = findViewById(R.id.framlayout);
        commons = findViewById(R.id.commons);
    }
    @Override
    protected void initData() {
        list_top=new ArrayList<>();
        list=new ArrayList<>();
        list_top.add(new MyTopEntity("首页",R.drawable.main_home_press,R.drawable.main_home));
        list_top.add(new MyTopEntity("分类",R.drawable.main_type_press,R.drawable.main_type));
        list_top.add(new MyTopEntity("发现",R.drawable.main_community_press,R.drawable.main_community));
        list_top.add(new MyTopEntity("购物车",R.drawable.main_cart_press,R.drawable.main_cart));
        list_top.add(new MyTopEntity("个人中心",R.drawable.main_user_press,R.drawable.main_user));
        commons.setTabData(list_top);

        firstFragment = new FirstFragment();
        fenLeiFragment = new FenLeiFragment();
        sendFragment = new SendFragment();
        shopCarFragment = new ShopCarFragment();
        peoPleFragment = new PeoPleFragment();

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
    }
    @Override
    protected void initEvent() {
        commons.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
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
                        getSupportFragmentManager().beginTransaction()
                                .show(shopCarFragment)
                                .hide(fenLeiFragment)
                                .hide(sendFragment)
                                .hide(firstFragment)
                                .hide(peoPleFragment)
                                .commit();
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
    @Override
    protected void createPresenter() {

    }
}
