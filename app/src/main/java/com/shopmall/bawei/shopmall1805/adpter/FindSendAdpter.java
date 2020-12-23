package com.shopmall.bawei.shopmall1805.adpter;

import android.widget.TextView;

import com.example.framework.BaseRVAdapter;
import com.example.net.FindSendBean;
import com.shopmall.bawei.shopmall1805.R;

public class FindSendAdpter extends BaseRVAdapter<FindSendBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_send;
    }

    @Override
    protected void convert(FindSendBean itemData, BaseViewHolder baseViewHolder, int position) {
        TextView id = baseViewHolder.getView(R.id.tv_send_id);
        TextView price = baseViewHolder.getView(R.id.tv_send_price);
        TextView time = baseViewHolder.getView(R.id.tv_send_time);
        id.setText(""+itemData.getTradeNo());
        price.setText(""+itemData.getTotalPrice());
        time.setText(""+itemData.getTime());
    }


    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
