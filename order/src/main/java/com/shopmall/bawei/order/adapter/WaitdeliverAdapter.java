package com.shopmall.bawei.order.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.order.R;
import com.shopmall.bean.OrderPaybean;

public class WaitdeliverAdapter extends BaseRVAdapter<OrderPaybean.ResultBean> {



    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_waitdeliver;
    }

    @Override
    protected void convert(final OrderPaybean.ResultBean itemData, final BaseViewHolder baseViewHolder, final int position) {
        TextView dan = baseViewHolder.itemView.findViewById(R.id.item_waitdeliver_dan);
        TextView money = baseViewHolder.itemView.findViewById(R.id.item_waitdeliver_money);
        TextView time = baseViewHolder.itemView.findViewById(R.id.item_waitdeliver_time);
        Button yes = baseViewHolder.itemView.findViewById(R.id.item_waitdeliver_yes);

        dan.setText(itemData.getTradeNo()+"");
        money.setText("￥"+itemData.getSubject()+"");
        time.setText(itemData.getTime()+"");

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(baseViewHolder.itemView.getContext(), "已催促商家发货！", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
