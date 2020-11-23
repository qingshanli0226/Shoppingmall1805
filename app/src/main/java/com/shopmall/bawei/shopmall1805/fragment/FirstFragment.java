package com.shopmall.bawei.shopmall1805.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.MainAdapter;
import com.shopmall.bawei.shopmall1805.bean.TestLayoutBean;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    private List<TestLayoutBean> beanList = new ArrayList<>();
    private RecyclerView mainRv;
    private View view;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment_first, container, false);
       //多布局
        beanList.add(new TestLayoutBean(TestLayoutBean.EDIT));
     /*   beanList.add(new TestLayoutBean(TestLayoutBean.SELECT));
        beanList.add(new TestLayoutBean(TestLayoutBean.YOUHUI));
        beanList.add(new TestLayoutBean(TestLayoutBean.TUIJIAN));
        beanList.add(new TestLayoutBean(TestLayoutBean.XINPIN));
        beanList.add(new TestLayoutBean(TestLayoutBean.CHECK));*/
        mainRv = (RecyclerView)view.findViewById(R.id.main_rv);

        MainAdapter mainAdapter = new MainAdapter(beanList);
        mainRv.setLayoutManager(new LinearLayoutManager(getContext()));

        mainRv.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();

        return view;
    }
}