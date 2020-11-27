package com.example.elevenmonthshoppingproject.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.elevenmonthshoppingproject.R;
import com.example.net.BaseFragment;

public class ShopShowFragment extends BaseFragment implements View.OnClickListener {
    private Button btnBiaoqian;
    private Button btnFenlei;
    private ListView listView;
    private RecyclerView recyShopshow;
    private boolean flag=false;
//    private
    @Override
    protected int getlayoutid() {
        return R.layout.shopshowfragment;
    }

    @Override
    protected void iniView(View view) {


        btnBiaoqian =view.findViewById(R.id.btn_biaoqian);
        btnFenlei =view. findViewById(R.id.btn_fenlei);
        listView = view.findViewById(R.id.list_shopclothes);
        recyShopshow = view.findViewById(R.id.recy_shopshow);


        btnBiaoqian.setOnClickListener(this);
        btnFenlei.setOnClickListener(this);



    }

    @Override
    protected void iniData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_biaoqian:
                if (!flag){
                    btnFenlei.setTextColor(Color.RED);
                }else {
                    btnBiaoqian.setTextColor(Color.BLUE);
                }

                break;
            case R.id.btn_fenlei:

                btnBiaoqian.setTextColor(Color.YELLOW);
                break;
        }
    }
}
