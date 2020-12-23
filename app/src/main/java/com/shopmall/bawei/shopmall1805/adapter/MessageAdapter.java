package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.framework.ShopcarMessage;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class MessageAdapter extends BaseQuickAdapter<ShopcarMessage, BaseViewHolder> {
    public MessageAdapter(int layoutResId, @Nullable List<ShopcarMessage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopcarMessage item) {
        helper.setText(R.id.tv_message_item,item.getId()+"");
        helper.setText(R.id.tv_message_item_boolean,item.getIsRead()+"");
        helper.setText(R.id.tv_message_item_title,item.getTitle()+"");
    }
}
