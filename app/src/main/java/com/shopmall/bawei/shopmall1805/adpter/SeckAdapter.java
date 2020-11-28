package com.shopmall.bawei.shopmall1805.adpter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.net.Confing;
import com.example.framework.BaseRVAdapter;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.shopmall1805.R;


public class SeckAdapter extends BaseRVAdapter<HomeBean.SeckillInfoBean.ListBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_seck_layout;
    }

    @Override
    protected void convert(HomeBean.SeckillInfoBean.ListBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.hotImg);
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE + itemData.getFigure()).into(imageView);
        TextView texthot1 = baseViewHolder.getView(R.id.tv_hot1);
        TextView texthot2 = baseViewHolder.getView(R.id.tv_hot2);
        texthot1.setText(""+itemData.getCover_price());
        texthot2.setText(""+itemData.getCover_price());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
