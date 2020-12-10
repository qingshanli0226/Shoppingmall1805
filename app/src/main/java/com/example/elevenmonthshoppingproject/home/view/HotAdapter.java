package com.example.elevenmonthshoppingproject.home.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.ShopMallContants;
import com.example.elevenmonthshoppingproject.R;
import com.example.framwork.BaseRVAdapter;
import com.example.net.bean.HomeBean;


public class HotAdapter extends BaseRVAdapter<HomeBean.HotInfoBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.hot_item_layout;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, HomeBean.HotInfoBean hotInfoBean) {
        Glide.with(holder.itemView.getContext()).load(ShopMallContants.Gui_Url+hotInfoBean.getFigure()).into((ImageView) holder.getView(R.id.img_hot));
        TextView txthot = holder.getView(R.id.txt_hot);
        txthot.setText(hotInfoBean.getName()+"");
    }
}
