package com.shopmall.bawei.shopmall1805.apter.zhuyeapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import bean.BaseBean;
import bean.ClothesBean;

public class MyBaseApter extends BaseQuickAdapter<BaseBean<ClothesBean>, BaseViewHolder> {
    public MyBaseApter(int layoutResId, @Nullable List<BaseBean<ClothesBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean<ClothesBean> item) {

    }
}
