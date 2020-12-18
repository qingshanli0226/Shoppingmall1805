package com.shopmall.bawei.shopmall1805.typefragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.shopmall.bawei.framework.example.framework.BaseFragment;
import com.shopmall.bawei.framework.example.framework.MyViewPager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.Fragment_fen_Adpter;
import com.shopmall.bawei.shopmall1805.typefragment.view.Fragment_Lable;
import com.shopmall.bawei.shopmall1805.typefragment.view.Fragment_fen;

import java.util.ArrayList;
import java.util.List;


public class TypeFragment extends BaseFragment {
    private Button btFen;
    private Button btBiao;
    private MyViewPager vrFen;
    private List<Fragment> list = new ArrayList<>();
    private Fragment_fen fragment_fen = new Fragment_fen();
    private Fragment_Lable fragment_biao = new Fragment_Lable();
    private Fragment_fen_Adpter fragment_fen_adpter;

    @Override
    protected void initPreseter() {
    }


    @Override
    protected void initView(View inflate) {
        btFen = inflate.findViewById(R.id.bt_fen);
        btBiao = inflate.findViewById(R.id.bt_biao);
        vrFen = inflate.findViewById(R.id.vr_fen);
        //添加fragment
        if (list !=null||list.size()!=0){
           list.clear();
        }
        list.add(fragment_fen);
        list.add(fragment_biao);

    }

    @Override
    protected void initdate() {

        fragment_fen_adpter = new Fragment_fen_Adpter(getChildFragmentManager(),list);
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
        return R.layout.fragment_type;
    }

}