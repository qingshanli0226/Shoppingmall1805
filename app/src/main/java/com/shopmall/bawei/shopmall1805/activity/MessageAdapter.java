package com.shopmall.bawei.shopmall1805.activity;

import android.widget.TextView;

import com.bw.framework.BaseAdapter;
import com.bw.framework.MessageManager;
import com.bw.framework.dao.ShopcarMessage;
import com.shopmall.bawei.shopmall1805.R;

import java.text.SimpleDateFormat;

public class MessageAdapter extends BaseAdapter<ShopcarMessage> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_message;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, ShopcarMessage shopcarMessage) {
       TextView messageType = baseViewHoder.getView(R.id.message_type);
       if (shopcarMessage.getType() == MessageManager.PAY_TYPE){
           messageType.setText("支付消息");
       }else {

       }
        TextView messageTime = baseViewHoder.getView(R.id.message_time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
        String time = simpleDateFormat.format(shopcarMessage.getTime());
        messageTime.setText(time);

        TextView messageBody = baseViewHoder.getView(R.id.message_body);
        messageBody.setText(shopcarMessage.getBody());


    }

    @Override
    public int getViewType(int position) {
        return 0;
    }
}
