package com.shopmall.bawei.shopmall1805.type;

import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.framework.base.BaseFragment;
import com.example.framework.view.MyVP;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.type.list.ListFragment;
import com.shopmall.bawei.shopmall1805.type.tag.TagFragment;
import com.shoppmall.common.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;


public class TypeFragment extends BaseFragment {
    private SegmentTabLayout segTab;
    private ImageView ivTypeSearch;
    private MyVP vpType;
    private List<Fragment> fragments=new ArrayList<>();
    @Override
    protected void initDate() {
        segTab.setTabData(new String[]{"分类","标签"});
        if(fragments.size()!=0){
            fragments.clear();
        }
        fragments.add(new ListFragment());
        fragments.add(new TagFragment());
        vpType.setScanScroll(false);
        vpType.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments));
    }

    @Override
    protected void initLisenter() {


        segTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpType.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initView() {
        segTab = (SegmentTabLayout) findViewById(R.id.segtab);
        ivTypeSearch = (ImageView)findViewById(R.id.iv_type_search);
        vpType = (MyVP) findViewById(R.id.vp_type);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_type;
    }
}