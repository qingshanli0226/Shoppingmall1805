package com.shopmall.bawei.shopmall1805.apter.apter2;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import bean.TAGBean;

public class ThelabelApter extends BaseQuickAdapter<TAGBean.ResultBean, BaseViewHolder> {
    public ThelabelApter(int layoutResId, @Nullable List<TAGBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TAGBean.ResultBean item) {
        helper.setText(R.id.textes,item.getName());
    }
}
