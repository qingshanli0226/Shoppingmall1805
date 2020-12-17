package com.example.elevenmonthshoppingproject.classification.view;

import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.adapter.TypeFragmentAdapter;
import com.example.framwork.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class ClassIfiCationFragment extends BaseFragment {
    private ViewPager classPager;
    private List<Fragment> fragments=new ArrayList<>();
    private ShopTypeFragment shopTypeFragment=new ShopTypeFragment();
    private LabelFragment labelFragment=new LabelFragment();
    private TypeFragmentAdapter typeFragmentAdapter;
    private SegmentTabLayout tlSegment;
    private String[] mTitles = {"分类", "标签"};


    @Override
    protected int getlayoutid() {
        return R.layout.classificationfragment;
    }

    @Override
    protected void iniView(View view) {
        fragments.add(shopTypeFragment);
        fragments.add(labelFragment);

        classPager =view.findViewById(R.id.class_pager);
        typeFragmentAdapter=new TypeFragmentAdapter(getChildFragmentManager(),fragments,mTitles);
        classPager.setAdapter(typeFragmentAdapter);
        classPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               tlSegment.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tlSegment = view.findViewById(R.id.tl_segment);
        tlSegment.setTabData(mTitles);
        tlSegment.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position>0||position<=2) {
                    classPager.setCurrentItem(position);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    @Override
    protected void iniData() {

    }
}
