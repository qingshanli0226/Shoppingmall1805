package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.common.Constants;
import com.shopmall.framework.adapter.BaseRVAdapter;
import com.shopmall.net.bean.HomeData;

public class SeckillAdapter extends BaseRVAdapter<HomeData.ResultBean.SeckillInfoBean.ListBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_seckill;
    }

    @Override
    protected void convert(HomeData.ResultBean.SeckillInfoBean.ListBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_figure);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + itemData.getFigure()).into(imageView);
        TextView textView = baseViewHolder.getView(R.id.tv_cover_price);
        textView.setText("￥"+itemData.getCover_price()+"");
        TextView textView2 = baseViewHolder.getView(R.id.tv_origin_price);
        textView2.setText("￥"+itemData.getOrigin_price()+"");
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
