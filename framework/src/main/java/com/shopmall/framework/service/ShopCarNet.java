package com.shopmall.framework.service;

import com.shopmall.common.Constants;
import com.shopmall.framework.base.BaseUrl;
import com.shopmall.framework.callback.ITest;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.mvptest.presenter.ShopCarPresenter;
import com.shopmall.net.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;

public class ShopCarNet extends BaseUrl<ShopCarPresenter> implements Contract.ShopCarContractView {
    private volatile static ShopCarNet shopCarNet;

    public static ShopCarNet getShopCarNet(){
        if (null == shopCarNet){
            synchronized (ShopCarNet.class){
                if (shopCarNet==null){
                    shopCarNet = new ShopCarNet();
                }
            }
        }
        return shopCarNet;
    }

    public void addShopCarData(String url, JSONObject jsonObject) {
        mPresenter.addShopCarData(url,jsonObject);
    }

    public void checkOneProductInventory(String url, HashMap<String, String> map, final ITest iTest) {
        mPresenter.checkOneProductInventory(url,map,iTest);
    }

    public void updateProductSelected(ShopcarBean.ResultBean shopCar, final int position) {
        mPresenter.updateProductSelected(Constants.UPDATE_PRODUCTSELECTED,shopCar,position);
    }

    public void selectAllProduct(boolean allSelect) {
        mPresenter.selectAllProduct(Constants.SELECTALL_PRODUCT,allSelect);
    }

    public void updateProductNum(final String num, ShopcarBean.ResultBean shopCar, final int position){
        mPresenter.updateProductNum(Constants.UPDATE_PRODUCTNUM,num,shopCar,position);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new ShopCarPresenter(this);
    }

    @Override
    public void Success(Object... objects) {

    }

    @Override
    public void Error(String s) {

    }
}
