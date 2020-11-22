package com.shopmall.bawei.shopmall1805.home.view;

import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.HomeBean;

public class HotAdapter extends BaseRvAdapter<HomeBean.ResultBean.HotInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return 0;
    }

    @Override
    protected void convert(HomeBean.ResultBean.HotInfoBean itemData, BaseViewHolder baseViewHolder, int position) {

    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
