package com.shopmall.bawei.shopmall1805.ui.activity.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.bean.FindForPayBean;

import java.util.List;

public class UserForPayAdapter extends BaseQuickAdapter<FindForPayBean,BaseViewHolder> {


    public UserForPayAdapter(int layoutResId, @Nullable List<FindForPayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindForPayBean item) {

    }
}
