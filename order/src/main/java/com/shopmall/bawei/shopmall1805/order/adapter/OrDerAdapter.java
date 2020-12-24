package com.shopmall.bawei.shopmall1805.order.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.shopmall1805.net.entity.FindForBean;
import com.shopmall.bawei.shopmall1805.net.entity.OrderInfoBean;

import java.util.List;

public class OrDerAdapter extends BaseQuickAdapter<FindForBean.ResultBean, BaseViewHolder> {

    public OrDerAdapter(int layoutResId, @Nullable List<FindForBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindForBean.ResultBean item) {
        helper.setText(R.id.item_price_tv,item.getTotalPrice()+"");
    }
}
