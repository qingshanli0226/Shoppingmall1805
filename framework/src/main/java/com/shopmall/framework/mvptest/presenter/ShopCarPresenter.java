package com.shopmall.framework.mvptest.presenter;

import com.shopmall.framework.callback.IShopCar;
import com.shopmall.framework.callback.ITest;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.manager.CacheManager;
import com.shopmall.framework.mvptest.repository.ShopCarRepository;
import com.shopmall.net.bean.RegisterBean;
import com.shopmall.net.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;

public class ShopCarPresenter extends Contract.ShopCarContractPresenter {

    public ShopCarPresenter(Contract.ShopCarContractView shopCarContractView) {
        super(shopCarContractView);
    }

    @Override
    public void addShopCarData(String url, JSONObject jsonObject) {
        mRepository.addShopCarData(url, jsonObject, new IShopCar() {
            @Override
            public void onSuccess(Object... objects) {
                if (objects!=null){
                    RegisterBean registerBeans = (RegisterBean) objects[0];
                    if (registerBeans.getCode().equals("200")){
                        CacheManager.getInstance().getShopCarDataFromServer();
                    }
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }

    @Override
    public void checkOneProductInventory(String url, HashMap<String, String> map, final ITest iTest) {
        mRepository.checkOneProductInventory(url, map, new IShopCar() {
            @Override
            public void onSuccess(Object... objects) {
                if (objects!=null) {
                    RegisterBean registerBeans = (RegisterBean) objects[0];
                    iTest.onTest(registerBeans.getMessage());
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }

    @Override
    public void updateProductSelected(String url, final ShopcarBean.ResultBean shopCar, final int position) {
        mRepository.updateProductSelected(url, shopCar, new IShopCar() {
            @Override
            public void onSuccess(Object... objects) {
                if (objects!=null) {
                    RegisterBean registerBeans = (RegisterBean) objects[0];
                    if (registerBeans.getCode().equals("200")){
                        CacheManager.getInstance().updateshopcarselect(shopCar,position);
                    }
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }

    @Override
    public void selectAllProduct(String url, boolean allSelect) {
        mRepository.selectAllProduct(url, allSelect, new IShopCar() {
            @Override
            public void onSuccess(Object... objects) {
                if (objects!=null) {
                    RegisterBean registerBeans = (RegisterBean) objects[0];
                    if (registerBeans.getCode().equals("200")){
                        CacheManager.getInstance().getShopCarDataFromServer();
                    }
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }

    @Override
    public void updateProductNum(String url, final String num, final ShopcarBean.ResultBean shopCar, final int position) {
        mRepository.updateProductNum(url,num,shopCar, new IShopCar() {
            @Override
            public void onSuccess(Object... objects) {
                RegisterBean registerBean = (RegisterBean)objects[0];
                if (registerBean.getCode().equals("200")){
                    CacheManager.getInstance().updateProductNum(position,num);
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }

    @Override
    protected void createRepository() {
        mRepository = new ShopCarRepository();
    }
}
