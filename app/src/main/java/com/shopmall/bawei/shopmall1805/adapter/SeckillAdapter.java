package com.shopmall.bawei.shopmall1805.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.Constants;
import com.example.net.bean.MainBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class SeckillAdapter extends BaseQuickAdapter<MainBean.ResultBean.SeckillInfoBean.ListBean, BaseViewHolder> {
    private Context context;

    public SeckillAdapter(@Nullable List<MainBean.ResultBean.SeckillInfoBean.ListBean> data, Context context) {
        super(R.layout.item_seckill,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean.ResultBean.SeckillInfoBean.ListBean item) {
        helper.setText(R.id.tv_cover_price,item.getCover_price());
        helper.setText(R.id.tv_origin_price,item.getOrigin_price());
        Glide.with(context).load(Constants.BASE_URl_IMAGE+item.getFigure()).into((ImageView) helper.getView(R.id.iv_figure));

    }

}
