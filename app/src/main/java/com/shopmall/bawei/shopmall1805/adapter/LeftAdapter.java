package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.entity.NameEntitiy;

import java.util.List;

public class LeftAdapter extends BaseQuickAdapter<NameEntitiy, BaseViewHolder> {
    public LeftAdapter(int layoutResId, @Nullable List<NameEntitiy> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NameEntitiy item) {
        helper.setText(R.id.tv_itemleft,item.getName());
    }
}
