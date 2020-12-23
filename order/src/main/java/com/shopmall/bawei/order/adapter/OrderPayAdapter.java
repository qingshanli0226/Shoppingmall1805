package com.shopmall.bawei.order.adapter;

import android.widget.Button;
import android.widget.TextView;

import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.order.R;
import com.shopmall.bean.OrderPaybean;

public class OrderPayAdapter extends BaseRVAdapter<OrderPaybean.ResultBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_orderpay;
    }

    @Override
    protected void convert(OrderPaybean.ResultBean itemData, BaseViewHolder baseViewHolder, int position) {
        TextView dan = baseViewHolder.itemView.findViewById(R.id.item_orderpay_dan);
        TextView money = baseViewHolder.itemView.findViewById(R.id.item_orderpay_money);
        TextView time = baseViewHolder.itemView.findViewById(R.id.item_orderpay_time);
        Button yes = baseViewHolder.itemView.findViewById(R.id.item_orderpay_yes);

        dan.setText(itemData.getTradeNo()+"");
        money.setText("￥"+itemData.getSubject()+"");
        time.setText(itemData.getTime()+"");

    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
