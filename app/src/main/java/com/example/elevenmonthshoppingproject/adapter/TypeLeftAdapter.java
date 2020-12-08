package com.example.elevenmonthshoppingproject.adapter;


import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.bean.TypeBean;

import java.util.List;

public class TypeLeftAdapter extends BaseQuickAdapter<TypeBean.ResultBean, BaseViewHolder> {


    public TypeLeftAdapter(int layoutResId, @Nullable List<TypeBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeBean.ResultBean item) {

    }
}
