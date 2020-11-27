package com.shopmall.bawei.shopmall1805.Adper.classify;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import mode.LableBean;

public
class LableAdper extends BaseQuickAdapter<LableBean.ResultBean, BaseViewHolder> {


    public LableAdper(int layoutResId, @Nullable List<LableBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LableBean.ResultBean item) {
        helper.setText(R.id.Biao_text,item.getName());
    }
}
