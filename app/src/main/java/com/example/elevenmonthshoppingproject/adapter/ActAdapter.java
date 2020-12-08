package com.example.elevenmonthshoppingproject.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.elevenmonthshoppingproject.R;
import com.example.net.Contants;
import com.example.net.bean.Recommonde;

public class ActAdapter extends  BaseRVAdapter<Recommonde.ActInfoBean> {

    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.act_layout;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, Recommonde.ActInfoBean actInfoBean) {
        Glide.with(holder.itemView.getContext()).load(Contants.Gui_Url+actInfoBean.getIcon_url()).into((ImageView) holder.getView(R.id.img_actpic4));
    }
}
