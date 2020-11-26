package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;


public class ClassfiyFragment extends Fragment {

    private Button btn1,btn2;
    private ViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();
    private int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_classfiy, container, false);

        btn1 = inflate.findViewById(R.id.btn1);
        btn2 = inflate.findViewById(R.id.btn2);
        viewPager = inflate.findViewById(R.id.vp);

        if (list.size()!=0||list!=null){
           list.clear();
        }

        list.add(new BlankFragment());
        list.add(new BlankFragment2());

        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });


        return inflate;
    }


}