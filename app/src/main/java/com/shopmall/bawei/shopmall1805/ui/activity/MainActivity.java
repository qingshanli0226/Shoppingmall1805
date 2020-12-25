package com.shopmall.bawei.shopmall1805.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.PayBean;
import com.example.framework.ShopUsermange;
import com.example.net.Confing;
import com.example.net.bean.ShopcarBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.typefragment.TypeFragment;
import com.shopmall.bawei.shopmall1805.home.view.HomeFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.FindFragment;
import com.shopmall.bawei.shopcar.view.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.ui.fragment.view.UserFragment;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.FragmentAdpter;
import com.example.framework.MyViewPager;
import com.shopmall.bawei.shopmall1805.bean.TabEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

@Route(path="/main/MainActivity")
public class MainActivity extends BaseActivity<IPresenter, IView> implements CacheManager.IShopcarDataCharListerter{
    private MyViewPager vr;
    private CommonTabLayout common;
    private ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private FragmentAdpter fragmentAdpter;
    private int number;
    @Override
    protected void initpreseter() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getindex(PayBean payBean){
        Toast.makeText(this, ""+payBean.getIndex(), Toast.LENGTH_SHORT).show();
        vr.setCurrentItem(payBean.getIndex());
    }
    @Override
    protected void initdate() {



        common.setTabData(tabEntitys);

        //适配器
        fragmentAdpter = new FragmentAdpter(getSupportFragmentManager(),fragmentArrayList);
        vr.setAdapter(fragmentAdpter);
        //点击底部切换fragment
        common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                if (position == 3){
                    if (!ShopUsermange.getInstance().isUserLogin()){
                        ARouter.getInstance().build("/user/LoginRegisterActivity").withInt(Confing.TO_LOGIN_KEY,position).navigation();
                        common.setCurrentTab(number);
                    }else {
                        vr.setCurrentItem(position);

                    }
                } else {
                   vr.setCurrentItem(position);
                   number=position;

                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }
    @Subscribe
    public void tablelayout(int index){
        if (index!=0){
            vr.setCurrentItem(0);
        }

    }

    @Override
    protected void initview() {
//        ARouter.getInstance().inject(this);
//        Intent intent = getIntent();
//        int index = intent.getIntExtra("index", 0);
//        if (index==0){
//            common.setCurrentTab(index);
//        }

        //初始化控件
        vr = findViewById(R.id.vr);
        common = findViewById(R.id.common);
        CacheManager.getInstance().setshopcarChangedListenter(this);
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new TypeFragment());
        fragmentArrayList.add(new FindFragment());
        fragmentArrayList.add(new ShopCarFragment());
        fragmentArrayList.add(new UserFragment());
        //添加数据
        tabEntitys.add(new TabEntity("首页",R.mipmap.select_1,R.mipmap.default_1));
        tabEntitys.add(new TabEntity("分类",R.mipmap.select_2,R.mipmap.default_2));
        tabEntitys.add(new TabEntity("发现",R.mipmap.select_3,R.mipmap.default_3));
        tabEntitys.add(new TabEntity("购物车",R.mipmap.select_4,R.mipmap.default_4));
        tabEntitys.add(new TabEntity("个人中心",R.mipmap.select_1,R.mipmap.default_1));



    }


    @Override
    protected int getlayoutid() {
        return R.layout.activity_main;
    }


    @Override
    public void ondataChanged(List<ShopcarBean> shopcarBeanList) {
        if (shopcarBeanList.size()!=0){
            common.showMsg(3,shopcarBeanList.size());
        }else {
            common.hideMsg(3);
        }
    }

    @Override
    public void onOneChanged(int position, ShopcarBean shopcarBean) {

    }

    @Override
    public void onManeyvhanged(String moneyValue) {

    }

    @Override
    public void onAllselected(boolean isAllSelect) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(this);
        EventBus.getDefault().unregister(this);
    }


}
