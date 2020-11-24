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

public class HotAdapter extends BaseQuickAdapter<MainBean.ResultBean.HotInfoBean, BaseViewHolder> {
    private Context context;

    public HotAdapter(@Nullable List<MainBean.ResultBean.HotInfoBean> data, Context context) {
        super(R.layout.item_hot,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean.ResultBean.HotInfoBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_price,"¥"+item.getCover_price());
        Glide.with(context).load(Constants.BASE_URl_IMAGE+item.getFigure()).into((ImageView) helper.getView(R.id.iv_hot));

    }
}
