package com.bw.order.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.BaseAdapter;
import com.bw.net.ForPayBean;

import com.shopmall.bawei.order.R;

public class ForPayAdapter extends BaseAdapter<ForPayBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_forpay;
    }

    @Override
    protected void convert(final BaseViewHoder baseViewHoder, int viewType, final ForPayBean forPayBean) {



        TextView orderPrice =  baseViewHoder.getView(R.id.orderPrice);
        ImageView orderImage =  baseViewHoder.getView(R.id.orderImage);
        TextView orderTitle =  baseViewHoder.getView(R.id.orderTitle);


        orderTitle.setText(forPayBean.getBody());
        orderPrice.setText(forPayBean.getTotalPrice());

        Button btnForPay = baseViewHoder.getView(R.id.btnPay);

        btnForPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/pay/payActivity").withSerializable("forPayBean",forPayBean).withBoolean("flag",true).navigation();
            }
        });


    }



    @Override
    public int getViewType(int position) {
        return position;
    }
}
