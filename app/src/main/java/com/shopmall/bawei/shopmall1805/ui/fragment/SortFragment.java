package com.shopmall.bawei.shopmall1805.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.fragmentsort.ClassFragment_Sort;
import com.shopmall.bawei.shopmall1805.ui.fragmentsort.TagFragment_Sort;

import java.util.ArrayList;
import java.util.List;

public class SortFragment extends BaseFragment {

    private SegmentTabLayout segmentF2;
    private FrameLayout frameF2;

   private ClassFragment_Sort classFragment_Sort;
   private TagFragment_Sort tagFragment_Sort;
    private List<Fragment> fragments=new ArrayList<>();


    @Override
    protected void createViewid(View inflate) {
        segmentF2 =inflate.findViewById(R.id.segment_f2);
        frameF2 = inflate.findViewById(R.id.frame_f2);
    }

    @Override
    protected void createEnvent() {
        String[] strings={"分类","标签"};

        segmentF2.setTabData(strings);

        classFragment_Sort=new ClassFragment_Sort();
        tagFragment_Sort=new TagFragment_Sort();
        fragments.add(classFragment_Sort);
        fragments.add(tagFragment_Sort);
        getChildFragmentManager().beginTransaction()
                .add(R.id.frame_f2,classFragment_Sort)
                .add(R.id.frame_f2,tagFragment_Sort)
                .commit();
        setfragment(classFragment_Sort);

        segmentF2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
                setfragment(fragments.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void createData() {

    }

    @Override
    protected int fragmentid() {
        return R.layout.sort_fragment;
    }

    @Override
    protected void createPresenter() {

    }

    private void setfragment(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .hide(classFragment_Sort)
                .hide(tagFragment_Sort)
                .show(fragment)
                .commit();
    }

}
