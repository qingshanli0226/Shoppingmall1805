package com.shopmall.bawei.shopmall1805.ui.fragment_main;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.shopmall.bawei.shopmall1805.ui.fragmentf2.Fragment1_f2;
import com.shopmall.bawei.shopmall1805.ui.fragmentf2.Fragment2_f2;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    private SegmentTabLayout segmentF2;
    private FrameLayout frameF2;

   private Fragment1_f2 fragment1_f2;
   private Fragment2_f2 fragment2_f2;
    private List<Fragment> fragments=new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment2, null);


        segmentF2 =inflate.findViewById(R.id.segment_f2);
        frameF2 = inflate.findViewById(R.id.frame_f2);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] strings={"分类","标签"};
        segmentF2.setTabData(strings);
         fragment1_f2=new Fragment1_f2();
         fragment2_f2=new Fragment2_f2();
         fragments.add(fragment1_f2);
         fragments.add(fragment2_f2);
         getChildFragmentManager().beginTransaction()
                 .add(R.id.frame_f2,fragment1_f2)
                 .add(R.id.frame_f2,fragment2_f2)
                 .commit();
         setfragment(fragment1_f2);

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

    private void setfragment(Fragment fragment){
         getChildFragmentManager().beginTransaction()
                 .hide(fragment1_f2)
                 .hide(fragment2_f2)
                 .show(fragment)
                 .commit();
    }
}
