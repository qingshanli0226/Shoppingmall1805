package com.shopmall.bawei.shopmall1805.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.common.BaseAdapter;
import com.bw.net.Contants;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.R;

public class MySeckillAdapter extends BaseAdapter<HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_seckill_data;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean listBean) {
        ImageView imageView = baseViewHoder.itemView.findViewById(R.id.seckill_image);
        TextView textView = baseViewHoder.itemView.findViewById(R.id.seckill_title);
        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+listBean.getFigure()).into(imageView);
        textView.setText(listBean.getCover_price());
    }



    @Override
    public int getViewType(int position) {
        return 0;
    }
}
