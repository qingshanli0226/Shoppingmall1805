package com.shopmall.bawei.shopmall1805.mvp.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.ClassificationSmallPagerAdapter;
import com.shopmall.bawei.shopmall1805.adapter.BaseVpAdapter;
import com.shopmall.bawei.shopmall1805.mvp.view.classificationfragment.ClassificationSmallFragment;
import com.shopmall.bawei.shopmall1805.mvp.view.classificationfragment.LabelFragment;

import java.util.ArrayList;
import java.util.List;


public class ClassificationFragment extends Fragment {

    private List<Fragment> list = new ArrayList<>();
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (null != view) {

            ViewGroup parent = (ViewGroup) view.getParent();

            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.fragment_classification, container, false);
            initView(view); // 控件初始化

        }

        return view;


    }

    private void initView(View view) {
        list.add(new ClassificationSmallFragment());
        list.add(new LabelFragment());
        ClassificationSmallPagerAdapter classAdapter = new ClassificationSmallPagerAdapter(getFragmentManager(), list);
        final BaseVpAdapter viewById = view.findViewById(R.id.class_vp);
        RadioGroup rg = view.findViewById(R.id.rg);
        viewById.setAdapter(classAdapter);
        viewById.setscrollable(false);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_left:
                        viewById.setCurrentItem(0);
                        break;
                    case R.id.radio_regit:
                        viewById.setCurrentItem(1);
                        break;
                }
            }
        });
    }
}