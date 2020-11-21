package com.shopmall.bawei.shopmall1805.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.CoutomEntiy;
import com.shopmall.bawei.shopmall1805.fragment.FaxianFragment;
import com.shopmall.bawei.shopmall1805.fragment.FirstFragment;
import com.shopmall.bawei.shopmall1805.fragment.Myfragment;
import com.shopmall.bawei.shopmall1805.fragment.ShoppingFragment;
import com.shopmall.bawei.shopmall1805.fragment.TypeFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private CommonTabLayout com;
    ArrayList<CustomTabEntity> tabEntitys=new ArrayList<>();
      ArrayList<Fragment> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);
        com = (CommonTabLayout) findViewById(R.id.com);
        indata();
        com.setTabData(tabEntitys);
    }

    private void indata() {
        arrayList.add(new FaxianFragment());
        arrayList.add(new FirstFragment());
        arrayList.add(new ShoppingFragment());
        arrayList.add(new TypeFragment());
        arrayList.add(new Myfragment());
        tabEntitys.add(new CoutomEntiy("首页",0,0));
        tabEntitys.add(new CoutomEntiy("",0,0));
        tabEntitys.add(new CoutomEntiy("",0,0));
        tabEntitys.add(new CoutomEntiy("",0,0));
        tabEntitys.add(new CoutomEntiy("",0,0));
    }
}
