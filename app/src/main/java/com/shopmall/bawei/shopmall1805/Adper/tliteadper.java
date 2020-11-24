package com.shopmall.bawei.shopmall1805.Adper;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public
class tliteadper extends BaseQuickAdapter<String, BaseViewHolder> {
    public tliteadper(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tlite_text_one,item);
        helper.addOnClickListener(R.id.tlite_text_one);
    }
}
