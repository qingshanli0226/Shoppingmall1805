package com.shopmall.bawei.shopmall1805.apter.apter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

import bean.HomeBean;


public class ActAdapter extends BaseRVAdapter<HomeBean.ActInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_act_layout;
    }

    @Override
    protected void convert(HomeBean.ActInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.vr_act);

        Glide.with(baseViewHolder.itemView.getContext()).load("http://49.233.0.68:8080/atguigu/img/"+ itemData.getIcon_url()).into(imageView);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
