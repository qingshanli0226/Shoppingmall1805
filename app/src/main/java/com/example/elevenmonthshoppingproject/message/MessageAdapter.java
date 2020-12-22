package com.example.elevenmonthshoppingproject.message;


import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.elevenmonthshoppingproject.R;
import com.example.framwork.BaseRVAdapter;
import com.example.framwork.ShopCarGreen;
import com.example.net.bean.MoneyBean;

import java.util.ArrayList;
import java.util.List;


public class MessageAdapter extends BaseRVAdapter<MoneyBean> {





    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.item_shopcar_message;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, MoneyBean shopCarGreen) {

        TextView titleTv = holder.getView(R.id.titleTv);
        TextView bodyTv = holder.getView(R.id.bodyTv);
        titleTv.setText(shopCarGreen.money);
        bodyTv.setText(shopCarGreen.money);
    }
}
