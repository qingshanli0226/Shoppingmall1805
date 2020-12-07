package com.shopmall.bawei.shopmall1805.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.framework.BaseAdapter;
import com.bw.net.Contants;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.R;

public class MyHotAdapter extends BaseAdapter<HomeFragmentBean.ResultBean.HotInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_hot_data;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, HomeFragmentBean.ResultBean.HotInfoBean hotInfoBean) {
        ImageView imageView = baseViewHoder.itemView.findViewById(R.id.hot_image);
        TextView textView = baseViewHoder.itemView.findViewById(R.id.hot_title);
        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+hotInfoBean.getFigure()).into(imageView);
        textView.setText(hotInfoBean.getName());
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
