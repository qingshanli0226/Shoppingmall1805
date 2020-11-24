package com.shopmall.bawei.shopmall1805.Fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.framework.BaseFragment;
import com.shopmall.bawei.shopmall1805.Fragment.fragment_fen.Fragment_biao;
import com.shopmall.bawei.shopmall1805.Fragment.fragment_fen.Fragment_fen;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.Fragment_fen_Adpter;
import com.shopmall.bawei.shopmall1805.bean.MyViewPager;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Classfid extends BaseFragment {
    private Button btFen;
    private Button btBiao;
    private MyViewPager vrFen;
    private List<Fragment> list = new ArrayList<>();
    private Fragment_fen fragment_fen = new Fragment_fen();
    private Fragment_biao fragment_biao = new Fragment_biao();
    private Fragment_fen_Adpter fragment_fen_adpter;

    @Override
    protected void initPreseter() {
    }

    @Override
    protected void initView(View inflate) {
        btFen = inflate.findViewById(R.id.bt_fen);
        btBiao = inflate.findViewById(R.id.bt_biao);
        vrFen = inflate.findViewById(R.id.vr_fen);
        list.add(fragment_fen);
        list.add(fragment_biao);
    }

    @Override
    protected void initdate() {

        fragment_fen_adpter = new Fragment_fen_Adpter(getActivity().getSupportFragmentManager(),list);
        vrFen.setAdapter(fragment_fen_adpter);
        btFen.setBackgroundResource(R.drawable.biao_fe_fen);
        //点击切换对应的fragment
        btFen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrFen.setCurrentItem(0);
                btFen.setBackgroundResource(R.drawable.biao_fe_fen);
                btBiao.setBackgroundResource(R.drawable.biao_bi);
            }
        });
        btBiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrFen.setCurrentItem(1);
                btBiao.setBackgroundResource(R.drawable.biao_bi_bi);
                btFen.setBackgroundResource(R.drawable.biao_fe);
            }
        });
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_fragment__classfid;
    }

}