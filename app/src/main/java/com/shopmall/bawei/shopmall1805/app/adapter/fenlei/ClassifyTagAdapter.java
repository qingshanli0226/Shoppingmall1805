package com.shopmall.bawei.shopmall1805.app.adapter.fenlei;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.common.FenLeiVpTwoEntity;

import java.util.List;

public class ClassifyTagAdapter extends BaseQuickAdapter<FenLeiVpTwoEntity.ResultBean, BaseViewHolder> {

    public ClassifyTagAdapter(int layoutResId, @Nullable List<FenLeiVpTwoEntity.ResultBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, FenLeiVpTwoEntity.ResultBean item) {
        helper.setText(R.id.fenlei_two_tv_item,item.getName());
    }
}
