package com.shopmall.bawei.shopmall1805.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.framework.BaseAdapter;
import com.bw.net.Contants;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.R;


public class MyActAdapter extends BaseAdapter<HomeFragmentBean.ResultBean.ActInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_act_data;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, HomeFragmentBean.ResultBean.ActInfoBean actInfoBean) {
        ImageView imageView = baseViewHoder.itemView.findViewById(R.id.act_image);

        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+actInfoBean.getIcon_url()).into(imageView);

    }



    @Override
    public int getViewType(int position) {
        return position;
    }
}
