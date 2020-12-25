package com.shopmall.bawei.order.adaper;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.order.R;

import java.util.List;

import view.loadinPage.UnpaidBean;

public
class UnpaoidAdaper extends BaseQuickAdapter<UnpaidBean.ResultBean, BaseViewHolder> {
    public UnpaoidAdaper(int layoutResId, @Nullable List<UnpaidBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnpaidBean.ResultBean item) {
            helper.setText(R.id.pay_textone,item.getTradeNo());
            helper.setText(R.id.pay_texttow,item.getOrderInfo().toString());
    }
}
