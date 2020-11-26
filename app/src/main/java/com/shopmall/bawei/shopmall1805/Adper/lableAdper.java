package com.shopmall.bawei.shopmall1805.Adper;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import mode.javabean;


public
class lableAdper extends BaseQuickAdapter<javabean.ResultBean, BaseViewHolder> {


    public lableAdper(int layoutResId, @Nullable List<javabean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, javabean.ResultBean item) {
        helper.setText(R.id.Biao_text,item.getName());
    }
}
