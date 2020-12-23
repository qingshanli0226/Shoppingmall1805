package com.shopmall.bawei.shopmall1805.apter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import bean.FindForSendBean;

public class DeliveryApter extends BaseQuickAdapter<FindForSendBean.ResultBean, BaseViewHolder> {
    public DeliveryApter(int layoutResId, @Nullable List<FindForSendBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindForSendBean.ResultBean item) {
        helper.setText(R.id.delivery_id,item.getTradeNo());
        helper.setText(R.id.delivery_num,"2");
        helper.setText(R.id.delivery_price,item.getTotalPrice());
        helper.setText(R.id.delivery_time,item.getTime());

    }
}
