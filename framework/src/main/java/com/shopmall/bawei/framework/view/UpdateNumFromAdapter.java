package com.shopmall.bawei.framework.view;

import com.shopmall.bawei.net.mode.ShopCarBean;

public interface UpdateNumFromAdapter {
    void onAddNum(ShopCarBean itemData, int num, int position);
    void onSubNum(ShopCarBean itemData, int num, int position);
}
