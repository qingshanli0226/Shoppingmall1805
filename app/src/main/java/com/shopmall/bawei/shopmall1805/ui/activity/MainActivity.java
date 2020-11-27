package com.shopmall.bawei.shopmall1805.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.FindFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.HomeFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.IndividualFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.SortFragment;
import com.shopmall.bean.Cus;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FrameLayout viewpagerMain;
    private CommonTabLayout commonMain;
    private ArrayList<CustomTabEntity> custom=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private HomeFragment homeFragment;
    private SortFragment sortFragment;
    private FindFragment findFragment;
    private ShopCarFragment shopCarFragment;
    private IndividualFragment individualFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initviewid();

        initfragment();

        custom.add(new  Cus("首页",R.mipmap.main_home,R.mipmap.main_home_press));
        custom.add(new  Cus("分类",R.mipmap.main_type,R.mipmap.main_type_press));
        custom.add(new  Cus("发现",R.mipmap.main_community,R.mipmap.main_community_press));
        custom.add(new  Cus("购物车",R.mipmap.icon_good_detail_cart,R.mipmap.main_cart_press));
        custom.add(new  Cus("个人中心",R.mipmap.main_user,R.mipmap.main_user_press));

        commonMain.setTabData(custom);


        /**
         * tab监听
         */
        commonMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                  setfragment(fragments.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });


    }

    /**
     * 添加fragment页面
     */
    private void initfragment() {
        homeFragment=new HomeFragment();
        sortFragment=new SortFragment();
        findFragment= new FindFragment();
        shopCarFragment= new ShopCarFragment();
        individualFragment= new IndividualFragment();
        fragments.add(homeFragment);
        fragments.add(sortFragment);
        fragments.add(findFragment);
        fragments.add(shopCarFragment);
        fragments.add(individualFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.viewpager_main,homeFragment)
                .add(R.id.viewpager_main,sortFragment)
                .add(R.id.viewpager_main,findFragment)
                .add(R.id.viewpager_main,shopCarFragment)
                .add(R.id.viewpager_main,individualFragment)
                .commit();
       setfragment(homeFragment);
    }


    private void setfragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(sortFragment)
                .hide(findFragment)
                .hide(shopCarFragment)
                .hide(individualFragment)
                .show(fragment)
                .commit();

    }

    /**
     * 初始化id
     */
    private void initviewid() {



        viewpagerMain = findViewById(R.id.viewpager_main);
        commonMain = findViewById(R.id.common_main);





    }
}
