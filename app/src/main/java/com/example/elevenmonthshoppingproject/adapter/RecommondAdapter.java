package com.example.elevenmonthshoppingproject.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.elevenmonthshoppingproject.R;
import com.example.net.Contants;
import com.example.net.bean.Recommonde;

public class RecommondAdapter extends  BaseRVAdapter<Recommonde.RecommendInfoBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.recommonview;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, Recommonde.RecommendInfoBean recommendInfoBean) {
        Glide.with(holder.itemView.getContext()).load(Contants.Gui_Url+recommendInfoBean.getFigure()).into((ImageView) holder.getView(R.id.img_pic2));
    }
}
