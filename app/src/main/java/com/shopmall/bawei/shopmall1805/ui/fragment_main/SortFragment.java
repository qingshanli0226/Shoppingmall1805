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
import com.shopmall.bawei.shopmall1805.ui.fragment_sort.ClassFragment_Sort;
import com.shopmall.bawei.shopmall1805.ui.fragment_sort.TagFragment_Sort;

import java.util.ArrayList;
import java.util.List;

public class SortFragment extends Fragment {

    private SegmentTabLayout segmentF2;
    private FrameLayout frameF2;

   private ClassFragment_Sort classFragment_Sort;
   private TagFragment_Sort tagFragment_Sort;
    private List<Fragment> fragments=new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.sort_fragment, null);


        segmentF2 =inflate.findViewById(R.id.segment_f2);
        frameF2 = inflate.findViewById(R.id.frame_f2);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    private void setfragment(Fragment fragment){
         getChildFragmentManager().beginTransaction()
                 .hide(classFragment_Sort)
                 .hide(tagFragment_Sort)
                 .show(fragment)
                 .commit();
    }
}
