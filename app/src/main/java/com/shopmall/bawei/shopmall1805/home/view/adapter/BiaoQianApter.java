package com.shopmall.bawei.shopmall1805.home.view.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.net.mode.TAGBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;


public class BiaoQianApter extends BaseQuickAdapter<TAGBean.ResultBean, BaseViewHolder> {
    public BiaoQianApter(int layoutResId, @Nullable List<TAGBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TAGBean.ResultBean item) {
        helper.setText(R.id.textes,item.getName());
    }
}
