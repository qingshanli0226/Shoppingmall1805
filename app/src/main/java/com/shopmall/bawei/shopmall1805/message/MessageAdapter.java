package com.shopmall.bawei.shopmall1805.message;

import android.widget.TextView;

import com.shopmall.bawei.framework.example.framework.BaseRVAdapter;
import com.shopmall.bawei.framework.example.framework.dao.PayMessage;
import com.shopmall.bawei.shopmall1805.R;

public class MessageAdapter extends BaseRVAdapter<PayMessage> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_message;
    }

    @Override
    protected void convert(PayMessage itemData, BaseViewHolder baseViewHolder, int position) {
        TextView titleTv = baseViewHolder.getView(R.id.titleTv);
        TextView bodyTv = baseViewHolder.getView(R.id.bodyTv);
        titleTv.setText(itemData.getTitle());
        bodyTv.setText(itemData.getBody());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
