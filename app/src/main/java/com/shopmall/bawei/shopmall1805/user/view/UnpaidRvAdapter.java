package com.shopmall.bawei.shopmall1805.user.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.PayBean;
import com.shopmall.bawei.shopmall1805.R;

public class UnpaidRvAdapter extends BaseRvAdapter<PayBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_unpaid;
    }

    @Override
    protected void convert(PayBean itemData, BaseViewHolder baseViewHolder, int position) {
        TextView body = baseViewHolder.getView(R.id.body);
        TextView totalPrice = baseViewHolder.getView(R.id.total_price);
        TextView tradeNo = baseViewHolder.getView(R.id.tradeNo);

        body.setText(itemData.getBody());
        totalPrice.setText("￥"+itemData.getTotalPrice());
        tradeNo.setText(itemData.getTradeNo());


    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
