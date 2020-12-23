package com.shopmall.bawei.order.sendorder;

import android.widget.TextView;

import com.example.net.bean.FindForSendBean;
import com.shopmall.bawei.order.R;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;
import com.shoppmall.common.adapter.time.TimeUtil;

public class SendOrderAdapter extends BaseRvAdapter<FindForSendBean.ResultBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_find_order;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHoder, int viewType, FindForSendBean.ResultBean findForSendBean) {
        TextView time = baseViewHoder.getView(R.id.time_find_order);
        TextView totalprice = baseViewHoder.getView(R.id.totalprice_find_order);
        TextView tradeno = baseViewHoder.getView(R.id.tradeno_find_order);
        totalprice.setText("¥"+findForSendBean.getTotalPrice());
        tradeno.setText(findForSendBean.getTradeNo()+"");
        time.setText(TimeUtil.stampToDate(Long.parseLong(findForSendBean.getTime())));
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
