package com.shopmall.framework.mvptest.repository;

import com.shopmall.framework.callback.IShopCar;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.mvptest.model.ShopCarModel;
import com.shopmall.net.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;

public class ShopCarRepository extends Contract.ShopCarContractRepository {
    @Override
    public void addShopCarData(String url, JSONObject jsonObject, IShopCar iShopCar) {
        mModel.addShopCarData(url,jsonObject,iShopCar);
    }

    @Override
    public void checkOneProductInventory(String url, HashMap<String, String> map, IShopCar iShopCar) {
        mModel.checkOneProductInventory(url,map,iShopCar);
    }

    @Override
    public void updateProductSelected(String url, ShopcarBean.ResultBean shopCar, IShopCar iShopCar) {
        mModel.updateProductSelected(url,shopCar,iShopCar);
    }

    @Override
    public void selectAllProduct(String url, boolean allSelect, IShopCar iShopCar) {
        mModel.selectAllProduct(url,allSelect,iShopCar);
    }

    @Override
    public void updateProductNum(String url, String num, ShopcarBean.ResultBean shopCar, IShopCar iShopCar) {
        mModel.updateProductNum(url,num,shopCar,iShopCar);
    }

    @Override
    public void removeManyProduct(String url, IShopCar iShopCar) {
        mModel.removeManyProduct(url,iShopCar);
    }

    @Override
    public void checkInventory(String url, IShopCar iShopCar) {
        mModel.checkInventory(url,iShopCar);
    }

    @Override
    public void updatePhone(String url, String phone, IShopCar iShopCar) {
        mModel.updatePhone(url,phone,iShopCar);
    }

    @Override
    public void updateAddress(String url, String address, IShopCar iShopCar) {
        mModel.updateAddress(url,address,iShopCar);
    }

    @Override
    protected void createModel() {
        mModel = new ShopCarModel();
    }
}
