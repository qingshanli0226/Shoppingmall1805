package com.example.elevenmonthshoppingproject.home.view;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.common.ShopMallContants;
import com.example.elevenmonthshoppingproject.R;
import com.example.framwork.BaseRVAdapter;
import com.example.net.bean.HomeBean;

public class RecommondAdapter extends BaseRVAdapter<HomeBean.RecommendInfoBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.recommonview;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, HomeBean.RecommendInfoBean recommendInfoBean) {
        Glide.with(holder.itemView.getContext()).load(ShopMallContants.Gui_Url+recommendInfoBean.getFigure()).into((ImageView) holder.getView(R.id.img_pic2));
    }
}
