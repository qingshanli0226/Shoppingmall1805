package com.example.elevenmonthshoppingproject.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.elevenmonthshoppingproject.R;
import com.example.net.BaseFragment;
import com.example.net.Contants;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class ShopTypeFragment extends BaseFragment implements View.OnClickListener {
    private CommonTabLayout commontab;
    private ImageView ivTypeSearch;

    private ArrayList<CustomTabEntity> tabEntitys=new ArrayList<>();
    private RecyclerView listClassification;
    private RecyclerView recyLabel;

    private String[] urls = new String[]{Contants.SKIRT_URL, Contants.JACKET_URL, Contants.PANTS_URL, Contants.OVERCOAT_URL,
            Contants.ACCESSORY_URL, Contants.BAG_URL, Contants.DRESS_UP_URL, Contants.HOME_PRODUCTS_URL, Contants.STATIONERY_URL,
            Contants.DIGIT_URL, Contants.GAME_URL};



    @Override
    protected int getlayoutid() {
        return R.layout.shopshowfragment;
    }

    @Override
    protected void iniView(View view) {
        listClassification =view. findViewById(R.id.recy_classification);
        recyLabel = view.findViewById(R.id.recy_label);
        commontab = view.findViewById(R.id.tl_1);
        ivTypeSearch = view.findViewById(R.id.iv_type_search);
        tabEntitys.add(new CustomTabEntity() {
            @Override
            public String getTabTitle() {
                return "分类";
            }

            @Override
            public int getTabSelectedIcon() {
                return 0;
            }

            @Override
            public int getTabUnselectedIcon() {
                return 0;
            }
        });
        tabEntitys.add(new CustomTabEntity() {
            @Override
            public String getTabTitle() {
                return "标签";
            }

            @Override
            public int getTabSelectedIcon() {
                return 0;
            }

            @Override
            public int getTabUnselectedIcon() {
                return 0;
            }
        });
        commontab.setTabData(tabEntitys);



    }

    @Override
    protected void iniData() {



    }

    @Override
    public void onClick(View view) {

    }
}
