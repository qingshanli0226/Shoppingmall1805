package com.shopmall.bawei.shopmall1805.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.bean.MainBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class HotAdapter extends BaseQuickAdapter<MainBean.ResultBean.HotInfoBean, BaseViewHolder> {
    private Context context;

    public HotAdapter(@Nullable List<MainBean.ResultBean.HotInfoBean> data, Context context) {
        super(R.layout.item_hot,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean.ResultBean.HotInfoBean item) {

    }
}
