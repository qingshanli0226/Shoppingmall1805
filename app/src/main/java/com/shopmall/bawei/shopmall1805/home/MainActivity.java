package com.shopmall.bawei.shopmall1805.home;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.framework.BaseActivity;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.bean.TabEntity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<IPresenter, IView> {
    private ViewPager vr;
    private CommonTabLayout common;
    private ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {

        common.setTabData(tabEntitys);
    }

    @Override
    protected void initview() {
        //初始化控件
        vr = findViewById(R.id.vr);
        common = findViewById(R.id.common);

        //添加数据
        tabEntitys.add(new TabEntity("首页",R.mipmap.select_1,R.mipmap.default_1));
        tabEntitys.add(new TabEntity("分类",R.mipmap.select_2,R.mipmap.default_2));
        tabEntitys.add(new TabEntity("发现",R.mipmap.select_3,R.mipmap.default_3));
        tabEntitys.add(new TabEntity("购物车",R.mipmap.select_4,R.mipmap.default_4));
        tabEntitys.add(new TabEntity("我的",R.mipmap.select_1,R.mipmap.default_1));
    }


    @Override
    protected int getlayoutid() {
        return R.layout.activity_main;
    }
}
