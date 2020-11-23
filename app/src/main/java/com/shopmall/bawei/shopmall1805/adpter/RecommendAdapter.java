package com.shopmall.bawei.shopmall1805.adpter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.net.Confing;
import com.example.framework.BaseRVAdapter;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.shopmall1805.R;


public class RecommendAdapter extends BaseRVAdapter<HomeBean.RecommendInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_common_layout;
    }

    @Override
    protected void convert(HomeBean.RecommendInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_recommend);
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE + itemData.getFigure()).into(imageView);
        TextView texthot1 = baseViewHolder.getView(R.id.tv_name);
        TextView texthot2 = baseViewHolder.getView(R.id.tv_price);
        texthot1.setText(""+itemData.getName());
        texthot2.setText("￥"+itemData.getCover_price());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
