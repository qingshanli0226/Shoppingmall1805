package com.shopmall.bawei.shopmall1805.app.adapter.fenlei;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.common.ClassifyTagEntity;

import java.util.List;

public class ClassifyTagAdapter extends BaseQuickAdapter<ClassifyTagEntity.ResultBean, BaseViewHolder> {

    public ClassifyTagAdapter(int layoutResId, @Nullable List<ClassifyTagEntity.ResultBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, ClassifyTagEntity.ResultBean item) {
        helper.setText(R.id.fenlei_two_tv_item,item.getName());
    }
}
