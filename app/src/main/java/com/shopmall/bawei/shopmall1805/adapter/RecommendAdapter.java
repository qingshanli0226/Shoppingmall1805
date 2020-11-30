package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.common.Constants;
import com.shopmall.framework.adapter.BaseRVAdapter;
import com.shopmall.net.bean.HomeData;

public class RecommendAdapter extends BaseRVAdapter<HomeData.ResultBean.RecommendInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_recommend_grid_view;
    }

    @Override
    protected void convert(HomeData.ResultBean.RecommendInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_recommend);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + itemData.getFigure()).into(imageView);
        TextView textView = baseViewHolder.getView(R.id.tv_name);
        textView.setText(itemData.getName()+"");
        TextView textView2 = baseViewHolder.getView(R.id.tv_price);
        textView2.setText("￥"+itemData.getCover_price()+"");
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
