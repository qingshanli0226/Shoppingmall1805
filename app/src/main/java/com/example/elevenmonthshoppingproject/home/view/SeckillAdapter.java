package com.example.elevenmonthshoppingproject.home.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.ShopMallContants;
import com.example.elevenmonthshoppingproject.R;
import com.example.framwork.BaseRVAdapter;
import com.example.net.bean.HomeBean;


public class SeckillAdapter extends BaseRVAdapter<HomeBean.SeckillInfoBean.ListBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.seckill_layout;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, HomeBean.SeckillInfoBean.ListBean listBean) {
        Glide.with(holder.itemView.getContext()).load(ShopMallContants.Gui_Url+listBean.getFigure()).into((ImageView) holder.getView(R.id.scekill_img));
        TextView txtseckill = holder.getView(R.id.txt_seckill);
        txtseckill.setText(listBean.getName()+"");
    }


}
