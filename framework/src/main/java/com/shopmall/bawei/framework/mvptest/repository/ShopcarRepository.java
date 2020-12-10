package com.shopmall.bawei.framework.mvptest.repository;

import com.shopmall.bawei.framework.callback.IShopcar;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.model.ShopcarModel;
import com.shopmall.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;

public class ShopcarRepository extends Constart.ShopcarConstartRepository {
    @Override
    protected void createModel() {
            mModel=new ShopcarModel();
    }
   //添加商品
    @Override
    public void addshopcarData(String url, JSONObject jsonObject, IShopcar iShopcar) {
          mModel.addshopcarData(url,jsonObject,iShopcar);
    }
    //检查库存情况
    @Override
    public void checkOneProductInventory(String url, HashMap<String, String> map, IShopcar iShopcar) {
          mModel.checkOneProductInventory(url,map,iShopcar);
    }
    //更该产品选择情况
    @Override
    public void updateProductSelected(String url, ShopcarBean.ResultBean shopcar, IShopcar iShopcar) {
          mModel.updateProductSelected(url,shopcar,iShopcar);
    }
    // 全选服务端购物车产品或者全不选
    @Override
    public void selectAllProduct(String url, boolean allselect, IShopcar iShopcar) {
          mModel.selectAllProduct(url,allselect,iShopcar);
    }
}
