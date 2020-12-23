package com.shopmall.bawei.order.payorder;

import android.widget.TextView;

import com.example.net.bean.FindForPayBean;
import com.shopmall.bawei.order.R;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;
import com.shoppmall.common.adapter.time.TimeUtil;

public class PayOrderAdapter extends BaseRvAdapter<FindForPayBean.ResultBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_find_order;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHoder, int viewType, FindForPayBean.ResultBean resultBean) {
        TextView time = baseViewHoder.getView(R.id.time_find_order);
        TextView totalprice = baseViewHoder.getView(R.id.totalprice_find_order);
        TextView tradeno = baseViewHoder.getView(R.id.tradeno_find_order);
        totalprice.setText("¥"+resultBean.getTotalPrice());
        tradeno.setText(resultBean.getTradeNo()+"");
        time.setText(TimeUtil.stampToDate(Long.parseLong(resultBean.getTime())));
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
