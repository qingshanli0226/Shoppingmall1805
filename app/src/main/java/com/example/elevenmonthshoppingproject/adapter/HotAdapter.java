package com.example.elevenmonthshoppingproject.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.elevenmonthshoppingproject.R;
import com.example.net.Contants;
import com.example.net.bean.Recommonde;

public class HotAdapter extends  BaseRVAdapter<Recommonde.HotInfoBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.hot_item_layout;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, Recommonde.HotInfoBean hotInfoBean) {
        Glide.with(holder.itemView.getContext()).load(Contants.Gui_Url+hotInfoBean.getFigure()).into((ImageView) holder.getView(R.id.img_hot));
    }
}
