package com.bawei.order.adapter;

import androidx.annotation.Nullable;

import com.bawei.net.mode.FindForPayBean;
import com.bawei.order.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ForPayAdapter extends BaseQuickAdapter<FindForPayBean.ResultBean, BaseViewHolder> {

    public ForPayAdapter(int layoutResId, @Nullable List<FindForPayBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FindForPayBean.ResultBean itemData) {

        baseViewHolder.setText(R.id.pay_name, itemData.getTradeNo());
        baseViewHolder.setText(R.id.pay_price, itemData.getTotalPrice());
        baseViewHolder.setText(R.id.pay_time, itemData.getTime());
    }
}
