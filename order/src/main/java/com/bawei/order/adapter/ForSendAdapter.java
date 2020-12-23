package com.bawei.order.adapter;

import androidx.annotation.Nullable;

import com.bawei.net.mode.FindForSendBean;
import com.bawei.order.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ForSendAdapter extends BaseQuickAdapter<FindForSendBean.ResultBean, BaseViewHolder> {

    public ForSendAdapter(int layoutResId, @Nullable List<FindForSendBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FindForSendBean.ResultBean itemData) {
        baseViewHolder.setText(R.id.pay_name, itemData.getTradeNo());
        baseViewHolder.setText(R.id.pay_price, itemData.getTotalPrice());
        baseViewHolder.setText(R.id.pay_time, itemData.getTime());
    }
}
