package com.shopmall.bawei.shopmall1805.message;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRVAdapter;
import com.example.framework.dao.ShopcarMessage;
import com.shopmall.bawei.shopmall1805.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageAdpter extends BaseRVAdapter<ShopcarMessage> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_message;
    }

    @Override
    protected void convert(ShopcarMessage itemData, BaseViewHolder baseViewHolder, int position) {
        TextView tv_message = baseViewHolder.getView(R.id.tv_message);
        TextView tv_pay = baseViewHolder.getView(R.id.tv_pay);
        TextView tv_time = baseViewHolder.getView(R.id.tv_time);
        tv_message.setText(""+itemData.getTitle());
        tv_pay.setText(""+itemData.getBody());
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate =  new Date(itemData.getTime());
        //获取消息时间
        String str  =  formatter.format(curDate);
        tv_time.setText(str);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
