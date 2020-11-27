package com.shopmall.bawei.shopmall1805.homeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.HomeData;


public class SeckAdapter extends BaseRVAdapter<HomeData.ResultBean.SeckillInfoBean.ListBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_seck_layout;
    }

    @Override
    protected void convert(HomeData.ResultBean.SeckillInfoBean.ListBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.hotImg);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + itemData.getFigure()).into(imageView);
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
