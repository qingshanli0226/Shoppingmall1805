package com.shopmall.bawei.shopmall1805.type;

import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.framework.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.MyVP;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.type.typefragment.ListFragment;
import com.shopmall.bawei.shopmall1805.type.typefragment.TagFragment;
import com.shoppmall.common.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;


public class TypeFragment extends BaseFragment {
    private SegmentTabLayout tl1;
    private ImageView ivTypeSearch;
    private MyVP vpType;
    private List<Fragment> fragments=new ArrayList<>();
    @Override
    protected void initDate() {




    }

    @Override
    protected void initLisenter() {
        tl1.setTabData(new String[]{"分类","标签"});
        if(fragments.size()!=0){
            fragments.clear();
        }
        fragments.add(new ListFragment());
        fragments.add(new TagFragment());
        vpType.setScanScroll(false);
        vpType.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments));
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
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
    protected void initView(View inflate) {
        tl1 = (SegmentTabLayout) inflate.findViewById(R.id.tl_1);
        ivTypeSearch = (ImageView) inflate.findViewById(R.id.iv_type_search);
        vpType = inflate.findViewById(R.id.vp_type);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_type;
    }
}