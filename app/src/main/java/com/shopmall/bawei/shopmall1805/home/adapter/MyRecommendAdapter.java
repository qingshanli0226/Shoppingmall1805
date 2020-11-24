package com.shopmall.bawei.shopmall1805.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.net.Contants;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.base.BaseAdapter;

public class MyRecommendAdapter extends BaseAdapter<HomeFragmentBean.ResultBean.RecommendInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_recommend_data;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, HomeFragmentBean.ResultBean.RecommendInfoBean recommendInfoBean) {
        ImageView imageView = baseViewHoder.itemView.findViewById(R.id.recommend_image);
        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+recommendInfoBean.getFigure()).into(imageView);
        TextView textView = baseViewHoder.itemView.findViewById(R.id.recommend_title);
        textView.setText(recommendInfoBean.getCover_price()+"");
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
