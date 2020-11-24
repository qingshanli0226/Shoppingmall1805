package com.shopmall.bawei.shopmall1805.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.net.bean.MyTab;
import com.shopmall.bawei.shopmall1805.fragment.CartFragment;
import com.shopmall.bawei.shopmall1805.fragment.CommunFragment;
import com.shopmall.bawei.shopmall1805.fragment.HomeFragment;
import com.shopmall.bawei.shopmall1805.fragment.MyFragment;
import com.shopmall.bawei.shopmall1805.fragment.TypeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CommonTabLayout comm;
    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
    private HomeFragment homeFragment = new HomeFragment();
    private TypeFragment typeFragment = new TypeFragment();
    private CommunFragment communFragment = new CommunFragment();
    private CartFragment cartFragment = new CartFragment();
    private MyFragment myFragment = new MyFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comm = (CommonTabLayout) findViewById(R.id.comm);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame,homeFragment)
                .add(R.id.frame,typeFragment)
                .add(R.id.frame,communFragment)
                .add(R.id.frame,cartFragment)
                .add(R.id.frame,myFragment)
                .commit();
        showFragment(homeFragment);

        tabEntities.add(new MyTab("首页", R.drawable.main_home_press, R.drawable.main_home));
        tabEntities.add(new MyTab("分类", R.drawable.main_type_press, R.drawable.main_type));
        tabEntities.add(new MyTab("发现", R.drawable.main_community_press, R.drawable.main_community));
        tabEntities.add(new MyTab("购物车", R.drawable.main_cart_press, R.drawable.main_cart));
        tabEntities.add(new MyTab("个人中心", R.drawable.main_user_press, R.drawable.main_user));

        comm.setTabData(tabEntities);

        comm.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(position == 0){
                    showFragment(homeFragment);
                }else  if(position == 1){
                    showFragment(typeFragment);
                }else  if(position == 2){
                    showFragment(communFragment);
                }else  if(position == 3){
                    showFragment(cartFragment);
                }else  if(position == 4){
                    showFragment(myFragment);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(typeFragment)
                .hide(communFragment)
                .hide(cartFragment)
                .hide(myFragment)
                .show(fragment)
                .commit();
    }
}
