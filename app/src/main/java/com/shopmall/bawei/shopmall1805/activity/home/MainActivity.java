package com.shopmall.bawei.shopmall1805.activity.home;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.bawei.deom.BaseActivity;
import com.bawei.deom.CacheManager;
import com.bawei.deom.MyServer;


import com.bawei.deom.ShopUserManager;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.CoutomEntiy;
import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.MyFragmentPager;
import com.shopmall.bawei.shopmall1805.fragment.FoundFragment;
import com.shopmall.bawei.shopmall1805.fragment.HomepageFragment;
import com.shopmall.bawei.shopmall1805.fragment.Myfragment;
import com.shopmall.bawei.shopmall1805.fragment.ShoppingFragment;
import com.shopmall.bawei.shopmall1805.fragment.SpeciesFragment;
import com.shopmall.bawei.shopmall1805.activity.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import bean.Shoppingcartproducts;


public class MainActivity extends BaseActivity {

    private ViewPager pager;
    private CommonTabLayout com;
    ArrayList<CustomTabEntity> tabEntitys=new ArrayList<>();
    ArrayList<Fragment> arrayList=new ArrayList<>();
    MyFragmentPager myFragmentPager;
   MyServer myServer;
    Intent intent;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;

    }
    @Override
    protected void intView() {
        token= getSharedPreferences("login", MODE_PRIVATE).getString("login", "123");
        pager = (ViewPager) findViewById(R.id.pager);
        com = (CommonTabLayout) findViewById(R.id.com);
        intent=new Intent(MainActivity.this, MyServer.class);


    }
    @Override
    protected void inPresone() {

    }
    String token;
    @Override
    protected void inData() {

        if (arrayList!=null||arrayList.size()!=0){
            arrayList.clear();
        }



        Log.e("logintoken",token);

        indata();
        myFragmentPager=new MyFragmentPager(getSupportFragmentManager(),arrayList);
        pager.setAdapter(myFragmentPager);
        com.setTabData(tabEntitys);


        com.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                pager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            com.setCurrentTab(i);
                Log.e("页数",pager.getCurrentItem()+"");
                if (!ShopUserManager.getInstance().isUserLogin()){
                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }
    CoutomEntiy coutomEntiy3=new CoutomEntiy("购物车",R.mipmap.main_cart,R.mipmap.main_cart);;
    int count;
    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener=new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<Shoppingcartproducts.ResultBean> shopcarBeanlist) {
              count=shopcarBeanlist.size();
//            tabEntitys.set(3,new CoutomEntiy(coutomEntiy3.getTabTitle()+count,R.mipmap.main_cart,R.mipmap.main_cart));

            Log.e("coutomEntiys",coutomEntiy3.getTabTitle());
        }

        @Override
        public void onOneDataChanger(int pstion, Shoppingcartproducts.ResultBean shopcarBeanlist) {

        }

        @Override
        public void onMeneyChanged(String moneyValue) {

        }

        @Override
        public void onAllSelected(boolean isAllSelect) {

        }

        @Override
        public void onAllSelectedNum(int num) {

        }
    };
    private void initShopcarDataChangeListener() {
        CacheManager.getInstance().setShopcarDataChangeListerner(iShopcarDataChangeListener);
    }

    private void updateShopcarCount() {
        //如果用户登录了，才可以刷新购物车数据，否则购物车数据并没有初始化，没必要刷新
        if (ShopUserManager.getInstance().isUserLogin()) {
            count = CacheManager.getInstance().getShopcarBeanlist().size();
//            if (count!=0){
//                tabEntitys.set(3,new CoutomEntiy(coutomEntiy3.getTabTitle()+count,R.mipmap.main_cart,R.mipmap.main_cart));
//            }


        } else {
            Toast.makeText(this,"当前用户没有登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void indata() {
        updateShopcarCount();
        initShopcarDataChangeListener();
           Log.e("coutomEntiy",coutomEntiy3.getTabTitle());
        Log.e("counts",count+"");
        arrayList.add(new HomepageFragment());
        arrayList.add(new SpeciesFragment());
        arrayList.add(new FoundFragment());
        arrayList.add(new ShoppingFragment());
        arrayList.add(new Myfragment());
        tabEntitys.add(new CoutomEntiy("首页",R.mipmap.top_bar_right_home_btn,R.mipmap.top_bar_right_home_btn));
        tabEntitys.add(new CoutomEntiy("分类",R.mipmap.main_type,R.mipmap.main_type));
        tabEntitys.add(new CoutomEntiy("发现",R.mipmap.icon_search_white,R.mipmap.icon_search_white));
        tabEntitys.add(new CoutomEntiy(coutomEntiy3.getTabTitle(),R.mipmap.main_cart,R.mipmap.main_cart));
        tabEntitys.add(new CoutomEntiy("个人中心",R.mipmap.icon_callserver_unpressed,R.mipmap.icon_callserver_unpressed));

    }





    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(intent);
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
    }

}
