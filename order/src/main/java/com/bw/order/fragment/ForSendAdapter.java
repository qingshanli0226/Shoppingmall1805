package com.bw.order.fragment;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.framework.BaseAdapter;
import com.bw.net.ForSendBean;
import com.shopmall.bawei.order.R;

public class ForSendAdapter extends BaseAdapter<ForSendBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_forpay;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, ForSendBean forSendBean) {
        TextView orderPrice =  baseViewHoder.getView(R.id.orderPrice);
        ImageView orderImage =  baseViewHoder.getView(R.id.orderImage);
        TextView orderTitle =  baseViewHoder.getView(R.id.orderTitle);


        orderTitle.setText(forSendBean.getBody());
        orderPrice.setText(forSendBean.getTotalPrice());

        Button btnPay = baseViewHoder.getView(R.id.btnPay);
        btnPay.setText("查看订单");
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
