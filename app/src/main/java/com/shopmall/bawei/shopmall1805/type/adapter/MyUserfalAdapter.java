package com.shopmall.bawei.shopmall1805.type.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.framework.BaseAdapter;
import com.bw.net.Contants;
import com.bw.net.bean.SkirtBean;
import com.shopmall.bawei.shopmall1805.R;

public class MyUserfalAdapter extends BaseAdapter<SkirtBean.ResultBean.ChildBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_userfal_data;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, SkirtBean.ResultBean.ChildBean childBean) {
        ImageView imageView = baseViewHoder.itemView.findViewById(R.id.userfal_image);
        TextView textView = baseViewHoder.itemView.findViewById(R.id.userfal_title);

        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+childBean.getPic()).into(imageView);
        textView.setText(childBean.getName());
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
