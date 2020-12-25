package com.shopmall.bawei.shopmall1805.Adper;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import framework.messagegreendao.ShopMessageGreenBean;

public
class PayMessageAdaper extends BaseQuickAdapter<ShopMessageGreenBean, BaseViewHolder> {
    public PayMessageAdaper(int layoutResId, @Nullable List<ShopMessageGreenBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopMessageGreenBean item) {
        helper.setText(R.id.payMessageTlite,item.getTlite());
        helper.setText(R.id.payMessageBody,item.getBody());
        helper.addOnClickListener(R.id.payMessageTlite)
                .addOnClickListener(R.id.payMessageBody);
    }
}
