package com.shopmall.bawei.shopmall1805.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.MyFragmentPager;
import com.shopmall.bawei.shopmall1805.fragment2.BiaoFragment;
import com.shopmall.bawei.shopmall1805.fragment2.FenFragment;

import java.util.ArrayList;

public class TypeFragment extends Fragment {
    private RadioButton fenlei;
    private RadioButton biaoqian;
    private ViewPager pager;

    MyFragmentPager myFragmentPager;
    ArrayList<Fragment> arrayList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.typefragment,null);
        fenlei = (RadioButton) view.findViewById(R.id.fenlei);
        biaoqian = (RadioButton) view.findViewById(R.id.biaoqian);
        pager = (ViewPager) view.findViewById(R.id.pager);
        arrayList.add(new FenFragment());
        arrayList.add(new BiaoFragment());
        myFragmentPager=new MyFragmentPager(getChildFragmentManager(),arrayList);
        pager.setAdapter(myFragmentPager);
        fenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0);
                fenlei.setTextColor(Color.RED);
                biaoqian.setTextColor(Color.WHITE);
            }
        });
        biaoqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
                fenlei.setTextColor(Color.WHITE);
                biaoqian.setTextColor(Color.RED);
            }
        });
        return view;
    }
}
