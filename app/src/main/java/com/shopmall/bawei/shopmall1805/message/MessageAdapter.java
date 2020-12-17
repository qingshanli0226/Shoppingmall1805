package com.shopmall.bawei.shopmall1805.message;

import android.widget.BaseAdapter;
import android.widget.TextView;
import com.shopmall.bawei.framework.BaseRVAdapter;
import com.shopmall.bawei.framework.dao.ShopcarMessage;
import com.shopmall.bawei.shopmall1805.R;

public class MessageAdapter extends BaseRVAdapter<ShopcarMessage> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcar_message;
    }

    @Override
    protected void convert(ShopcarMessage itemData, BaseViewHolder baseViewHolder, int position) {
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
