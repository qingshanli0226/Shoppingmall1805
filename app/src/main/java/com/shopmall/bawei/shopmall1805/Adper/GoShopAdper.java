package com.shopmall.bawei.shopmall1805.Adper;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import framework.greendao.usernv;

public
class GoShopAdper extends BaseQuickAdapter<usernv, BaseViewHolder> {
    public GoShopAdper(int layoutResId, @Nullable List<usernv> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, usernv item) {

    }
}
