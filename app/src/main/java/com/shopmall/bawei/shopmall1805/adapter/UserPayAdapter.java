package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.FindPayBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class UserPayAdapter extends BaseQuickAdapter<FindPayBean, BaseViewHolder> {

    public UserPayAdapter(int layoutResId, @Nullable List<FindPayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindPayBean item) {
        helper.setText(R.id.tv_pay_time,item.getTime());
        helper.setText(R.id.tv_pay_price,item.getTotalPrice());
        helper.setText(R.id.tv_pay_id,item.getTradeNo());
    }
}
