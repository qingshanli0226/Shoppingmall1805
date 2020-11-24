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

public class RecommendAdapter extends BaseQuickAdapter<MainBean.ResultBean.RecommendInfoBean, BaseViewHolder> {
    private Context context;

    public RecommendAdapter(@Nullable List<MainBean.ResultBean.RecommendInfoBean> data, Context context) {
        super(R.layout.item_recommend,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean.ResultBean.RecommendInfoBean item) {
        Glide.with(context).load(Constants.BASE_URl_IMAGE+item.getFigure()).into((ImageView) helper.getView(R.id.iv_recommend));
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_price,"¥"+item.getCover_price());
    }
}
