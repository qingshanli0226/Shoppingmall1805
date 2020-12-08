package com.shopmall.bawei.shopmall1805.detail;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.framework.BaseAdapter;
import com.bw.net.Contants;
import com.shopmall.bawei.shopmall1805.GreenDaoBean;
import com.shopmall.bawei.shopmall1805.R;

public class MyDetailsAdapter extends BaseAdapter<GreenDaoBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_details;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, GreenDaoBean shopCarBean) {
        ImageView imageView = baseViewHoder.itemView.findViewById(R.id.detailsImage);
        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+shopCarBean.getUrl()).into(imageView);
        TextView textView = baseViewHoder.itemView.findViewById(R.id.detailsTitle);
        textView.setText(shopCarBean.getProductName());
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
