package com.bawei.shopmall.message;

import android.widget.TextView;

import com.bawei.framework.BaseRvAdapter;
import com.bawei.framework.greendao.MessageBean;
import com.shopmall.bawei.shopmall1805.R;

public class MessageAdapter extends BaseRvAdapter<MessageBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_message;
    }

    @Override
    protected void convert(MessageBean itemData, BaseViewHolder baseViewHolder, int position) {
        TextView id = baseViewHolder.getView(R.id.tv_number);
        TextView title = baseViewHolder.getView(R.id.message_title);
        TextView body = baseViewHolder.getView(R.id.message_body);
        id.setText(itemData.getId() + "");
        title.setText(itemData.getTitle());
        body.setText(itemData.getBody());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
