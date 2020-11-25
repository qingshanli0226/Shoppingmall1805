package com.shopmall.bawei.shopmall1805.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends Fragment {
    private SegmentTabLayout tl1;
    private FrameLayout flType;

    private ListFragment listFragment;
    private TagFragment tagFragment;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_type, container, false);
        tl1 = (SegmentTabLayout) view.findViewById(R.id.tl_1);
        flType = (FrameLayout) view.findViewById(R.id.fl_type);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] strings={"分类","标签"};
        tl1.setTabData(strings);
        listFragment = new ListFragment();
        tagFragment = new TagFragment();
        fragments.add(listFragment);
        fragments.add(tagFragment);
        getChildFragmentManager().beginTransaction()
                .add(R.id.fl_type,listFragment)
                .add(R.id.fl_type,tagFragment)
                .commit();
        setfragment(listFragment);

        tl1.setOnTabSelectListener(new OnTabSelectListener() {
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

    private void setfragment(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .hide(listFragment)
                .hide(tagFragment)
                .show(fragment)
                .commit();
    }
}