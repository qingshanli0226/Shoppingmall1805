package com.shopmall.bawei.shopmall1805.home.view.adapter;



import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class ZhongleiApter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ZhongleiApter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
           helper.setText(R.id.lei,item);
    }
}
