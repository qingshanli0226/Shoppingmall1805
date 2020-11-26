package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import baseurl.SkirstBean;
import baseurl.UrlHelp;

public class ClassificationSmallOftenAdapter extends BaseQuickAdapter<SkirstBean.ResultBean.ChildBean, BaseViewHolder> {
    public ClassificationSmallOftenAdapter(int layoutResId, @Nullable List<SkirstBean.ResultBean.ChildBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, SkirstBean.ResultBean.ChildBean item) {
        ImageView helperView = helper.getView(R.id.often_class_im);
        Glide.with(mContext).load(UrlHelp.BASE+UrlHelp.BASE_URl_IMAGE+item.getPic()).into(helperView);
        helper.setText(R.id.often_class_te, item.getName());

    }
}
