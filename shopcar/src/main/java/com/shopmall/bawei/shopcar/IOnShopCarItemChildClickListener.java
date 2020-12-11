package com.shopmall.bawei.shopcar;

public interface IOnShopCarItemChildClickListener {
    void onProductNumChange(String id, int num, String name, String url, String price);
    void onProductSelectChange(int position,boolean isSelect);

}
