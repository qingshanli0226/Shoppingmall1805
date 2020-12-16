package com.shopmall.bawei.shopmall1805.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.fragment.CartFragment;
import com.shopmall.bawei.shopmall1805.fragment.CommunFragment;
import com.shopmall.bawei.shopmall1805.fragment.HomeFragment;
import com.shopmall.bawei.shopmall1805.fragment.MyFragment;
import com.shopmall.bawei.shopmall1805.fragment.TypeFragment;
import com.shopmall.framework.base.BaseMVPActivity;
import com.shopmall.framework.manager.CacheManager;
import com.shopmall.framework.service.LoginService;
import com.shopmall.net.bean.MyTab;
import com.shopmall.framework.manager.ShopUserManager;
import com.shopmall.net.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseMVPActivity implements CacheManager.IShopCarDataChangeListener {

    private CommonTabLayout comm;
    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private HomeFragment homeFragment;
    private TypeFragment typeFragment;
    private CommunFragment communFragment;
    private CartFragment cartFragment;
    private MyFragment myFragment;
    private int num;

    private Intent intent;

    @Override
    protected void initEvent() {
        comm.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(position == 3){
                    if (ShopUserManager.getInstance().isUserLogin()==false){
                        ARouter.getInstance().build("/user/UserActivity").navigation();
                        comm.setCurrentTab(num);
                    }else {
                        showFragment(fragments.get(position));
                    }
                }else{
                    showFragment(fragments.get(position));
                    num = position;
                }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initData() {
        homeFragment = new HomeFragment();
        typeFragment = new TypeFragment();
        communFragment = new CommunFragment();
        cartFragment = new CartFragment();
        myFragment = new MyFragment();

        fragments.add(homeFragment);
        fragments.add(typeFragment);
        fragments.add(communFragment);
        fragments.add(cartFragment);
        fragments.add(myFragment);

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
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        CacheManager.getInstance().setShopCarDataChangeListener(this);

        comm = (CommonTabLayout) findViewById(R.id.comm);

        intent = new Intent(MainActivity.this, LoginService.class);
        startService(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
        CacheManager.getInstance().unSetShopCarDataChangerListener(this);
    }

    @Override
    public void onDataChanged(List<ShopcarBean.ResultBean> shopCarBeanList) {
        if (shopCarBeanList.size() != 0){
            comm.showMsg(3,shopCarBeanList.size());
        }else {
            comm.hideMsg(3);
        }
    }

    @Override
    public void onOneDataChanged(int position, ShopcarBean.ResultBean shopcarBean) {

    }

    @Override
    public void onMoneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }

    @Override
    public void getDeleteAllSelect(boolean isAllSelect) {

    }
}
