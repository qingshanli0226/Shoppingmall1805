package com.shopmall.bawei.shopmall1805.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.fragment.classIfys.Fragment_classify2;
import com.shopmall.bawei.shopmall1805.fragment.classIfys.Fragment_lable;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.mvpc.jsonPresenter;

public
class Fragmentclassify extends BaseFragment {

    private LinearLayout linnerButton;
    private TextView textOne;
    private TextView textTow;
    private android.support.v4.view.ViewPager ViewPager;
    private boolean isshow = true;
    private List<Fragment> list = new ArrayList<>();
    @Override
    protected void createPresenter() {
        Presenter = new jsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {
        linnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isshow){
                    isshow = false;
                    textOne.setTextColor(Color.RED);
                    textTow.setTextColor(Color.WHITE);
                    ViewPager.setCurrentItem(0);
                }else {
                    isshow  = true;
                    textOne.setTextColor(Color.WHITE);
                    textTow.setTextColor(Color.RED);
                    ViewPager.setCurrentItem(1);
                }
            }
        });
    }

    @Override
    protected void InitData(View inflate) {
        linnerButton = (LinearLayout) inflate.findViewById(R.id.linner_button);
        textOne = (TextView) inflate.findViewById(R.id.text_one);
        textTow = (TextView) inflate.findViewById(R.id.text_tow);
        ViewPager = (android.support.v4.view.ViewPager) inflate.findViewById(R.id.View_pager);
        list.add(new Fragment_classify2());
        list.add(new Fragment_lable());

         FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }
        };
         ViewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_classifs; }
}
