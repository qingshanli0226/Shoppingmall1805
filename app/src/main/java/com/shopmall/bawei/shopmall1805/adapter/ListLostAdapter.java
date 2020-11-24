package com.shopmall.bawei.shopmall1805.adapter;

import com.example.net.bean.GoodsBean;
import com.shoppmall.common.adapter.BaseRvAdapter;

public class ListLostAdapter extends BaseRvAdapter<GoodsBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return 0;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHoder, int viewType, GoodsBean bean) {

    }

    @Override
    public int getViewType(int position) {
        return 0;
    }
}
