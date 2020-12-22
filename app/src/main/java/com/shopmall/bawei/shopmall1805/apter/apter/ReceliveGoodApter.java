package com.shopmall.bawei.shopmall1805.apter.apter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

import java.util.List;

import bean.FindForPayBean;


public class ReceliveGoodApter extends BaseQuickAdapter<FindForPayBean.ResultBean, BaseViewHolder> {

    public ReceliveGoodApter(int layoutResId, @Nullable List<FindForPayBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindForPayBean.ResultBean item) {
          helper.setText(R.id.recelivegoods_id,item.getTradeNo());
          helper.setText(R.id.recelivegoods_num,"2");
          helper.setText(R.id.recelivegoods_price,item.getTotalPrice());
          helper.addOnClickListener(R.id.recelivegoods_but);
    }
}
