package com.shopmall.bawei.shopmall1805.type.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.net.Contants;
import com.bw.net.bean.SkirtBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.base.BaseAdapter;

import java.util.List;


public class MyReferAdapter extends BaseAdapter<SkirtBean.ResultBean.HotProductListBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_refer_data;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, SkirtBean.ResultBean.HotProductListBean hotProductListBean) {


        ImageView imageView = baseViewHoder.itemView.findViewById(R.id.refer_image);
        TextView textView = baseViewHoder.itemView.findViewById(R.id.refer_title);

        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+hotProductListBean.getFigure()).into(imageView);
        textView.setText(hotProductListBean.getName());




    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
