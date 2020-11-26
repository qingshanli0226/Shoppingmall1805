package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.JavaBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class NameAdapter extends BaseQuickAdapter<JavaBean.ResultBean, BaseViewHolder> {


    public NameAdapter(int layoutResId, @Nullable List<JavaBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JavaBean.ResultBean item) {
        helper.setText(R.id.tv_itemname,item.getName());
    }
}
