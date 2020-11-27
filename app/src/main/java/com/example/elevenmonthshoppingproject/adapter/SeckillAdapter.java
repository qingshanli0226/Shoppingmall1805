package com.example.elevenmonthshoppingproject.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.elevenmonthshoppingproject.R;
import com.example.net.Contants;
import com.example.net.bean.Recommonde;

import java.util.ArrayList;
import java.util.List;

public class SeckillAdapter extends  BaseRVAdapter<Recommonde.SeckillInfoBean.ListBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.seckill_layout;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, Recommonde.SeckillInfoBean.ListBean listBean) {
        Glide.with(holder.itemView.getContext()).load(Contants.Gui_Url+listBean.getFigure()).into((ImageView) holder.getView(R.id.scekill_img));
    }


}
