package com.shopmall.bawei.shopmall1805.Adper.classify;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public
class TliteHeadlineAdper extends BaseQuickAdapter<String, BaseViewHolder> {
    public TliteHeadlineAdper(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tlite_text_Headlin,item);
        helper.addOnClickListener(R.id.tlite_text_Headlin);
    }
}
