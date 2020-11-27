package com.shopmall.bawei.shopmall1805.app.ui.fragment;

import androidx.fragment.app.Fragment;

import android.widget.FrameLayout;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.framework.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class ClassifyFragment extends BaseFragment {

    private SegmentTabLayout tl1;
    private FrameLayout framelt;
    private List<Fragment> list=new ArrayList<>();
    private ClassifyLeftFragment oneFragment;
    private ClassifyRightFragment twoFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }
    @Override
    protected void initData() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.framelt,oneFragment)
                .add(R.id.framelt,twoFragment)
                .show(oneFragment)
                .hide(twoFragment)
                .commit();

        tl1.setTabData(new String[]{"分类","标签"});
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(position == 0){
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .show(oneFragment)
                            .hide(twoFragment)
                            .commit();
                }else  if(position == 1){
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .show(twoFragment)
                            .hide(oneFragment)
                            .commit();
                }
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    @Override
    protected void initView() {
        tl1 =  findViewById(R.id.tl_1);
        framelt = findViewById(R.id.framelt);
        oneFragment=new ClassifyLeftFragment();
        twoFragment = new ClassifyRightFragment();
    }


}