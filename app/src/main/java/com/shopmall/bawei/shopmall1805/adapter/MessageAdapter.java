package com.shopmall.bawei.shopmall1805.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.framework.greendaobean.MessageBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.glide.Myglide;

public class MessageAdapter extends BaseRVAdapter<MessageBean> {

    private TextView messageTitle;
    private TextView messageDate;
    private TextView messageCircle;
    private ImageView messageImage;
    private TextView messageTishi;
    private TextView messageTime;
    private TextView messageRead;
    private TextView messageTop;
    private TextView messageOrder;


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_message;
    }

    @Override
    protected void convert(MessageBean itemData, BaseViewHolder baseViewHolder, int position) {


        messageTitle = baseViewHolder.itemView.findViewById(R.id.message_title);
        messageDate = baseViewHolder.itemView.findViewById(R.id.message_date);
        messageCircle = baseViewHolder.itemView.findViewById(R.id.message_circle);
        messageImage = baseViewHolder.itemView.findViewById(R.id.message_image);
        messageTishi = baseViewHolder.itemView.findViewById(R.id.message_tishi);
        messageTime = baseViewHolder.itemView.findViewById(R.id.message_time);
        messageRead = baseViewHolder.itemView.findViewById(R.id.message_read);
        messageTop = baseViewHolder.itemView.findViewById(R.id.message_top);
        messageOrder = baseViewHolder.itemView.findViewById(R.id.message_order);

        messageTitle.setText(itemData.getTitle());
        messageDate.setText(itemData.getDate());
        if (itemData.getRead()){
            messageCircle.setVisibility(View.GONE);
            messageRead.setText(R.string.message_yes_read);
            messageRead.setTextColor(Color.parseColor("#999999"));
        }else {
            messageCircle.setVisibility(View.VISIBLE);
            messageRead.setText(R.string.message_no_read);
            messageRead.setTextColor(Color.parseColor("#000000"));
        }

        Myglide.getMyglide().centercenglide(baseViewHolder.itemView.getContext(),messageImage,itemData.getUrl());
        Log.e("msg",itemData.getMsg());
        if (itemData.getMsg().equals("您有一比已支付信息的账单，请查看！")){
            messageTop.setBackgroundColor(Color.parseColor("#FF8400"));
            messageOrder.setText(R.string.message_order_yes);
            messageTishi.setText(R.string.message_date2);
        }else {
            messageTop.setBackgroundColor(Color.parseColor("#1EC400"));
            messageOrder.setText(R.string.message_order_no);
            messageTishi.setText(R.string.message_date);
        }

        messageTime.setText(itemData.getDate());

       }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
