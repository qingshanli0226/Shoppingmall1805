package com.shopmall.bawei.pay;

import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRVAdapter;
import com.example.net.bean.FindPayBean;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindPayAdpter extends BaseRVAdapter<FindPayBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_pay;
    }

    @Override
    protected void convert(FindPayBean itemData, BaseViewHolder baseViewHolder, int position) {
        TextView id = baseViewHolder.getView(R.id.tv_pay_id);
        TextView price = baseViewHolder.getView(R.id.tv_pay_price);
        TextView time = baseViewHolder.getView(R.id.tv_pay_time);
        id.setText(""+itemData.getTradeNo());
        price.setText(""+itemData.getTotalPrice());
        time.setText(""+itemData.getTime());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
