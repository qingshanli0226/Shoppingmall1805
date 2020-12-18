package com.shopmall.bawei.shopmall1805.ui.activity;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.framework.example.framework.BaseActivity;
import com.shopmall.bawei.framework.example.framework.IPresenter;
import com.shopmall.bawei.framework.example.framework.IView;
import com.shopmall.bawei.framework.example.framework.MyViewPager;
import com.shopmall.bawei.framework.example.framework.manager.UserManage;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.FragmentAdpter;
import com.shopmall.bawei.shopmall1805.bean.TabEntity;
import com.shopmall.bawei.shopmall1805.home.view.HomeFragment;
import com.shopmall.bawei.shopmall1805.typefragment.TypeFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.FindFragment;
import com.shopmall.bawei.shopcar.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.UserFragment;

import java.util.ArrayList;

@Route(path = "/activity/MainActivity")
public class MainActivity extends BaseActivity<IPresenter, IView> {
    private MyViewPager vr;
    private CommonTabLayout common;
    private ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private FragmentAdpter fragmentAdpter;
    private boolean b = false;

    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {

        //获取登陆状态
        b = UserManage.getInstance().quFlag();


        common.setTabData(tabEntitys);

        //适配器
        fragmentAdpter = new FragmentAdpter(getSupportFragmentManager(),fragmentArrayList);
        vr.setAdapter(fragmentAdpter);
        //点击底部切换fragment
        common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vr.setCurrentItem(position);
                if (position == 3){
                    if (!b){
                        ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
                        finish();
                        return;
                    }
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }


    @Override
    protected void initview() {
        //初始化控件
        vr = findViewById(R.id.vr);
        common = findViewById(R.id.common);

        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new TypeFragment());
        fragmentArrayList.add(new FindFragment());
        fragmentArrayList.add(new ShopCarFragment());
        fragmentArrayList.add(new UserFragment());
        //添加数据
        tabEntitys.add(new TabEntity("首页",R.drawable.main_home_press,R.drawable.main_home));
        tabEntitys.add(new TabEntity("分类",R.drawable.main_type_press,R.drawable.main_type));
        tabEntitys.add(new TabEntity("发现",R.drawable.main_community_press,R.drawable.main_community));
        tabEntitys.add(new TabEntity("购物车",R.drawable.main_cart_press,R.drawable.main_cart));
        tabEntitys.add(new TabEntity("个人中心",R.drawable.main_user_press,R.drawable.main_user));




    }


    @Override
    protected int getlayoutid() {
        return R.layout.activity_main;
    }


}
