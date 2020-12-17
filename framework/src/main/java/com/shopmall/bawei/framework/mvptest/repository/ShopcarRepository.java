package com.shopmall.bawei.framework.mvptest.repository;

import com.shopmall.bawei.framework.callback.IShopcar;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.mvptest.model.ShopcarModel;
import com.shopmall.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;

public class ShopcarRepository extends Constant.ShopcarConstartRepository {
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
    // 从服务端购物车删除多个产品
    @Override
    public void removeManyProduct(String url, IShopcar iShopcar) {
        mModel.removeManyProduct(url,iShopcar);
    }
    //更新服务端购物车产品的数量
    @Override
    public void updateProductNum(String url,int newnum, ShopcarBean.ResultBean shopcar, IShopcar iShopcar) {
       mModel.updateProductNum(url,newnum,shopcar,iShopcar);
    }
    // 检查服务端多个产品是否库存充足
    @Override
    public void checkInventory(String url, IShopcar iShopcar) {
        mModel.checkInventory(url,iShopcar);
    }

    @Override
    public void updatePhone(String url, String phone, IShopcar iShopcar) {
        mModel.updatePhone(url,phone,iShopcar);
    }

    @Override
    public void updateAddress(String url, String address, IShopcar iShopcar) {
        mModel.updateAddress(url,address,iShopcar);
    }
}
