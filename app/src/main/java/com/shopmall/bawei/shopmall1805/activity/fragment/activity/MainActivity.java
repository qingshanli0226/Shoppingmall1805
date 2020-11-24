package com.shopmall.bawei.shopmall1805.activity.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.net.bean.Cus;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.activity.ui.fragment_main.HomeFragment;
import com.shopmall.bawei.shopmall1805.activity.ui.fragment_main.FenLeiFragment;
import com.shopmall.bawei.shopmall1805.activity.ui.fragment_main.FindFragment;
import com.shopmall.bawei.shopmall1805.activity.ui.fragment_main.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.activity.ui.fragment_main.MineFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FrameLayout viewpagerMain;
    private CommonTabLayout commonMain;
    private ArrayList<CustomTabEntity> custom=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private HomeFragment fragment1;
    private FenLeiFragment fragment2;
    private FindFragment fragment3;
    private ShopCarFragment fragment4;
    private MineFragment fragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviewid();

        initfragment();

        custom.add(new  Cus("首页",R.drawable.main_home,R.drawable.main_home_press));
        custom.add(new Cus("分类",R.drawable.main_type,R.drawable.main_type_press));
        custom.add(new  Cus("发现",R.drawable.main_community,R.drawable.main_community_press));
        custom.add(new  Cus("购物车",R.drawable.icon_good_detail_cart,R.drawable.main_cart_press));
        custom.add(new  Cus("个人中心",R.drawable.main_user,R.drawable.main_user_press));

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
         fragment1=new HomeFragment();
         fragment2=new FenLeiFragment();
        fragment3= new FindFragment();
        fragment4= new ShopCarFragment();
        fragment5= new MineFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.viewpager_main,fragment1)
                .add(R.id.viewpager_main,fragment2)
                .add(R.id.viewpager_main,fragment3)
                .add(R.id.viewpager_main,fragment4)
                .add(R.id.viewpager_main,fragment5)
                .commit();
       setfragment(fragment1);
    }


    private void setfragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .hide(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .hide(fragment4)
                .hide(fragment5)
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
