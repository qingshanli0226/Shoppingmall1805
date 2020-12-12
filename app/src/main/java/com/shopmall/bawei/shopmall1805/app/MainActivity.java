package com.shopmall.bawei.shopmall1805.app;


import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.UserManager;
import com.example.framework.view.MyVP;
import com.example.net.bean.ShopCarBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
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
@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity implements CacheManager.IShopcarDataChangeListener {
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
        vpMain.setOffscreenPageLimit(5);
        CacheManager.getInstance().setShopcarDataChangeListener(this);
        List<ShopCarBean.ResultBean> list = CacheManager.getInstance().getShopCarList();
        if(list.size()>0){
            commMain.showMsg(3,list.size());
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String key = intent.getStringExtra("position");
        if(key!=null&&!key.equals("")){

            int anInt = Integer.parseInt(key);
            commMain.setCurrentTab(anInt);
            vpMain.setCurrentItem(anInt);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        commMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(UserManager.isLogin()){
                    vpMain.setCurrentItem(position);
            }else {
                Toast.makeText(MainActivity.this, "请先登录账户", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/user/LoginActivity").withString("key",position+"").navigation();
                commMain.setCurrentTab(0);
            }

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

    @Override
    public void onDataChanged(List<ShopCarBean.ResultBean> shopCarBeanList) {
        if(shopCarBeanList.size()>0){
            commMain.showMsg(3,shopCarBeanList.size());
        }else {
            commMain.hideMsg(3);
        }
    }

    @Override
    public void onOneDataChanged(int position, ShopCarBean.ResultBean shopCarBean) {

    }


    @Override
    public void onMoneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }
}