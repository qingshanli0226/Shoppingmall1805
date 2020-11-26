package com.shopmall.bawei.shopmall1805.app.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.fragment.fenfragment.OneFragment;
import com.shopmall.bawei.shopmall1805.app.fragment.fenfragment.TwoFragment;
import com.shopmall.bawei.shopmall1805.framework.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class FenLeiFragment extends BaseFragment {

    private SegmentTabLayout tl1;
    private FrameLayout framelt;
    private List<Fragment> list=new ArrayList<>();
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    @Override
    protected void initEvent() {

    }
    @Override
    protected void createPresenter() {

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
    protected void initView(View iView) {
        tl1 = iView.findViewById(R.id.tl_1);
        framelt = iView.findViewById(R.id.framelt);

        oneFragment=new OneFragment();
        twoFragment = new TwoFragment();
    }
    @Override
    protected int bandLyoaut() {
        return R.layout.fragment_fen_lei;
    }
}