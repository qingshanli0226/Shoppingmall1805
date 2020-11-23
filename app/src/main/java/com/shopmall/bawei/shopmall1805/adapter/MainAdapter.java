package com.shopmall.bawei.shopmall1805.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.bean.TestLayoutBean;

import java.util.List;

public class MainAdapter extends BaseMultiItemQuickAdapter<TestLayoutBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MainAdapter(List<TestLayoutBean> data) {
        super(data);
        addItemType(TestLayoutBean.EDIT,R.layout.banner_layout);

    }

    @Override
    protected void convert(BaseViewHolder helper, TestLayoutBean item) {
        switch (helper.getItemViewType()){
            case TestLayoutBean.EDIT:

                break;
            case TestLayoutBean.CHECK:

                break;
            case TestLayoutBean.SELECT:

                break;
            case TestLayoutBean.YOUHUI:

                break;
            case TestLayoutBean.XINPIN:

                break;
            case TestLayoutBean.TUIJIAN:

                break;

        }
    }
}
