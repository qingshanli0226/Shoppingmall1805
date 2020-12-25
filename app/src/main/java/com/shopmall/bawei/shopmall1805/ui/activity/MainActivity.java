package com.shopmall.bawei.shopmall1805.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.framework.manager.ShopCarmanager;
import com.shopmall.bawei.framework.manager.ShopUserManager;
import com.shopmall.bawei.framework.service.LoginService;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.fragment.FindFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.HomeFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.IndividualFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.SortFragment;
import com.shopmall.bean.Cus;
import com.shopmall.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity implements ShopCarmanager.IShopcarDataChangeListener {
    private FrameLayout viewpagerMain;
    private CommonTabLayout commonMain;
    private ArrayList<CustomTabEntity> custom=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private HomeFragment homeFragment;
    private SortFragment sortFragment;
    private FindFragment findFragment;
    private ShopCarFragment shopCarFragment;
    private IndividualFragment individualFragment;
    private int num;
    private Intent intent;


    @Override
    protected void oncreatePresenter() {

    }

    @Override
    protected void initEnvent() {
        /**
         * tab监听
         */
        commonMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position==3){
                    if (ShopUserManager.getInstance().isLogin()==false){
                        ARouter.getInstance().build("/user/UserMainActivity").navigation();
                        commonMain.setCurrentTab(num);
                    }else {
                        setfragment(fragments.get(position));
                    }
                }else {
                    setfragment(fragments.get(position));
                    num=position;
                }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initview() {
        ARouter.getInstance().inject(this);
        intent = new Intent(MainActivity.this, LoginService.class);
        startService(intent);
        ShopCarmanager.getShopCarmanager().registiShopcarDataChangeListener(this);
        viewpagerMain = findViewById(R.id.viewpager_main);
        commonMain = findViewById(R.id.common_main);


    }

    @Override
    protected void initData() {
        custom.add(new Cus("首页",R.mipmap.main_home,R.mipmap.main_home_press));
        custom.add(new  Cus("分类",R.mipmap.main_type,R.mipmap.main_type_press));
        custom.add(new  Cus("发现",R.mipmap.main_community,R.mipmap.main_community_press));
        custom.add(new  Cus("购物车",R.mipmap.icon_good_detail_cart,R.mipmap.main_cart_press));
        custom.add(new  Cus("个人中心",R.mipmap.main_user,R.mipmap.main_user_press));

        commonMain.setTabData(custom);





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

    @Override
    protected int layoutid() {
        return R.layout.activity_main;
    }


    /**
     * 选择显示fragmnet页面
     * @param fragment
     */
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
        ShopCarmanager.getShopCarmanager().uniShopcarDataChangeListener(this);
    }

    @Override
    public void shopcarData(List<ShopcarBean.ResultBean> shopcarBeans) {
        if (shopcarBeans.size()!=0){
            commonMain.showMsg(3,shopcarBeans.size());
        }else {
            commonMain.hideMsg(3);
        }
         Log.e("num",""+shopcarBeans.size());
    }

    @Override
    public void undateshopcar(int positon, ShopcarBean.ResultBean shopcar) {

    }

    @Override
    public void getMoney(String money) {

    }

    @Override
    public void getallselect(boolean isallselect) {

    }

    @Override
    public void getdeleteallselect(boolean isallselect) {

    }


}
