package com.shopmall.bawei.shopmall1805.message;

import android.view.View;
import android.widget.TextView;

import com.example.framework.greendao.MessageBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;
import com.shoppmall.common.adapter.time.TimeUtil;

public class MessageAdapter extends BaseRvAdapter<MessageBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.message_item;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHoder, int viewType, MessageBean messageBean) {
        TextView titleItemMessage = baseViewHoder.getView(R.id.title_item_message);
        TextView isnewItemMessage = baseViewHoder.getView(R.id.isnew_item_message);
        TextView msgItemMessage = baseViewHoder.getView(R.id.msg_item_message);
        TextView timeItemMessage = baseViewHoder.getView(R.id.time_item_message);
        titleItemMessage.setText(messageBean.getTitle());
        if(messageBean.getIsNew()){
            isnewItemMessage.setVisibility(View.VISIBLE);
        }else {
            isnewItemMessage.setVisibility(View.GONE);
        }
        msgItemMessage.setText(messageBean.getMsg());
        timeItemMessage.setText(TimeUtil.stampToDate(messageBean.getTime()));
    }

    @Override
    public int getViewType(int position) {
        return position;
    }

}
