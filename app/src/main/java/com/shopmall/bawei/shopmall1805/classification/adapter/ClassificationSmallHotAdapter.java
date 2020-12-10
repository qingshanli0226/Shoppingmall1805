package com.shopmall.bawei.shopmall1805.classification.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.common2.SkirstBean;
import com.example.common2.UrlHelp;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;


public class ClassificationSmallHotAdapter extends BaseQuickAdapter<SkirstBean.HotProductListBean, BaseViewHolder> {
    public ClassificationSmallHotAdapter(int layoutResId, @Nullable List<SkirstBean.HotProductListBean> data) {
        super(layoutResId, data);
    }




    @Override
    protected void convert(BaseViewHolder helper, SkirstBean.HotProductListBean item) {
        ImageView helperView = helper.getView(R.id.hot_class_im);
        Glide.with(mContext).load(UrlHelp.BASE_URl_IMAGE+item.getFigure()).into(helperView);
        helper.setText(R.id.hot_class_te,"￥"+item.getCover_price());

    }
}
