package com.shopmall.bawei.shopmall1805.message;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
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
        TextView tv_read = baseViewHolder.getView(R.id.tv_read);
        tv_message.setText(""+itemData.getTitle()+":");
        tv_pay.setText(""+itemData.getBody());
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//        Date curDate =  new Date(itemData.getTime());
//        //获取消息时间
//        String str  =  formatter.format(curDate);

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒") ;
        String time = format.format(new Date(itemData.getTime()));

        tv_time.setText(time);
        if (itemData.getIsRead()){
            tv_read.setText("已读");
            tv_read.setTextColor(Color.BLACK);
        }else {
            tv_read.setText("未读");
            tv_read.setTextColor(Color.RED);
        }
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
