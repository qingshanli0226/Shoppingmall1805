package com.shopmall.bawei.shopcar.adapter;

import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.ShopCarBean;
import com.shopmall.bawei.shopcar.R;

public class ShopCarAdapter extends BaseRvAdapter<ShopCarBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shop_cart;
    }

    @Override
    protected void convert(ShopCarBean itemData, BaseViewHolder baseViewHolder, int position) {

    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
