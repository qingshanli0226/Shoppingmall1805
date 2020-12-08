package com.shopmall.bawei.shopmall1805.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.common2.TagBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;



public class ClassificationSmallLabelAdapter extends BaseQuickAdapter<TagBean.ResultBean, BaseViewHolder> {
    public ClassificationSmallLabelAdapter(int layoutResId, @Nullable List<TagBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagBean.ResultBean item) {
        helper.setText(R.id.tag_te,item.getName());
    }
}
