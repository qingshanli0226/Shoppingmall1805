package com.shopmall.bawei.shopmall1805.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.Fragment1;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.Fragment2;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.Fragment3;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.Fragment4;
import com.shopmall.bawei.shopmall1805.ui.fragment_main.Fragment5;
import com.shopmall.bean.Cus;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FrameLayout viewpagerMain;
    private CommonTabLayout commonMain;
    private ArrayList<CustomTabEntity> custom=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;

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
         fragment1=new Fragment1();
         fragment2=new Fragment2();
        fragment3= new Fragment3();
        fragment4= new Fragment4();
        fragment5= new Fragment5();
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
