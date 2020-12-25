package com.shopmall.bawei.order.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.framework.callback.Iorderpayitemlistener;
import com.shopmall.bawei.order.R;
import com.shopmall.bean.OrderPaybean;

public class OrderPayAdapter extends BaseRVAdapter<OrderPaybean.ResultBean> {
    private Iorderpayitemlistener iorderpayitemlistener;

    public void setIorderpayitemlistener(Iorderpayitemlistener iorderpayitemlistener1){
         this.iorderpayitemlistener=iorderpayitemlistener1;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_orderpay;
    }

    @Override
    protected void convert(final OrderPaybean.ResultBean itemData, final BaseViewHolder baseViewHolder, final int position) {
        TextView dan = baseViewHolder.itemView.findViewById(R.id.item_orderpay_dan);
        TextView money = baseViewHolder.itemView.findViewById(R.id.item_orderpay_money);
        TextView time = baseViewHolder.itemView.findViewById(R.id.item_orderpay_time);
        Button yes = baseViewHolder.itemView.findViewById(R.id.item_orderpay_yes);

        dan.setText(itemData.getTradeNo()+"");
        money.setText("￥"+itemData.getSubject()+"");
        time.setText(itemData.getTime()+"");

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 iorderpayitemlistener.orderpayitem(itemData,itemData.getSubject(),position);
            }
        });

    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
