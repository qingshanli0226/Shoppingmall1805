package com.shopmall.bawei.shopmall1805.apter;

import android.widget.TextView;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ShangTitle;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

public class MessageApter extends BaseRVAdapter<ShangTitle> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcar_message;
    }

    @Override
    protected void convert(ShangTitle itemData, BaseViewHolder baseViewHolder, int position) {
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
