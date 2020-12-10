package com.example.elevenmonthshoppingproject.classification.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.elevenmonthshoppingproject.R;
import com.example.framwork.BaseFragment;

public class ShopTypeFragment extends BaseFragment {

    private ListView listType;
    private ArrayAdapter<String> skrittypeAdapter;
    private RecyclerView rvShopmall;

    private String[] skirt={"小裙子","上衣","下装","外套","配件","包包","装","居家展评","办公文具","数码周边","游戏专区"};

    @Override
    protected int getlayoutid() {
        return R.layout.shoptypefragment;
    }

    @Override
    protected void iniView(View view) {


        listType = view.findViewById(R.id.list_type);
        rvShopmall = view.findViewById(R.id.rv_shopmall);

        skrittypeAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,skirt);
        listType.setAdapter(skrittypeAdapter);

        //判断对应的
        listType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){

                }
            }
        });
    }

    @Override
    protected void iniData() {

    }
}
