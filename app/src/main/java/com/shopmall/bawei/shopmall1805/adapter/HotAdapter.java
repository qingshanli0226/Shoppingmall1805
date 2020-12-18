package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.net.Confing;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.framework.example.framework.BaseRVAdapter;
import com.shopmall.bawei.shopmall1805.R;


public class HotAdapter extends BaseRVAdapter<HomeBean.HotInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_hot;
    }

    @Override
    protected void convert(HomeBean.HotInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_hot);
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE + itemData.getFigure()).into(imageView);
        TextView texthot1 = baseViewHolder.getView(R.id.tv_hot_name);
        TextView texthot2 = baseViewHolder.getView(R.id.tv_hot_price);
        texthot1.setText(""+itemData.getName());
        texthot2.setText(""+itemData.getCover_price());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
