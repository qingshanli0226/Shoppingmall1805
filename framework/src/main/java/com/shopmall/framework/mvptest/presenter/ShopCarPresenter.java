package com.shopmall.framework.mvptest.presenter;

import com.shopmall.framework.callback.IShopCar;
import com.shopmall.framework.callback.ITest;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.manager.CacheManager;
import com.shopmall.framework.manager.ShopUserManager;
import com.shopmall.framework.mvptest.repository.ShopCarRepository;
import com.shopmall.net.bean.CheckBean;
import com.shopmall.net.bean.RegisterBean;
import com.shopmall.net.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

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
                if (objects != null){
                    RegisterBean registerBean = (RegisterBean)objects[0];
                    if (registerBean.getCode().equals("200")){
                        CacheManager.getInstance().updateProductNum(position,num);
                    }
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }

    @Override
    public void removeManyProduct(String url) {
        mRepository.removeManyProduct(url, new IShopCar() {
            @Override
            public void onSuccess(Object... objects) {
                if (objects != null){
                    RegisterBean registerBean = (RegisterBean)objects[0];
                    if (registerBean.getCode().equals("200")){
                        CacheManager.getInstance().removeSelectedProducts();
                    }
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }

    @Override
    public void checkInventory(String url, final ITest iTest) {
        mRepository.checkInventory(url, new IShopCar() {
            @Override
            public void onSuccess(Object... objects) {
                if(objects != null){
                    CheckBean checkBean = (CheckBean)objects[0];
                    if (checkBean.getCode().equals("200")){
                        List<CheckBean.ResultBean> result = checkBean.getResult();
                        List<ShopcarBean.ResultBean> shopCarBeanList = CacheManager.getInstance().getShopCarBeanList();

                        for (int i=0 ; i<result.size() ; i++){
                            ShopcarBean.ResultBean resultBean = shopCarBeanList.get(i);
                            CheckBean.ResultBean resultBean1 = result.get(i);
                            if (Integer.parseInt(resultBean.getProductNum())>Integer.parseInt(resultBean1.getProductNum())){
                                iTest.onTest(resultBean.getProductName()+"产品数量不足"+resultBean1.getProductNum());
                            }
                        }
                        iTest.onTest("200");
                    }
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }

    @Override
    public void updatePhone(String url, String phone) {
        mRepository.updatePhone(url, phone, new IShopCar() {
            @Override
            public void onSuccess(Object... objects) {
                if (objects != null){
                    RegisterBean registerBean = (RegisterBean)objects[0];
                    if (registerBean.getCode().equals("200")){
                        mView.get().Success(registerBean.getMessage());
                        if (registerBean.getResult()!=null){
                            ShopUserManager.getInstance().setPhone(registerBean.getResult());
                        }
                    }
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }

    @Override
    public void updateAddress(String url, String address) {
        mRepository.updatePhone(url, address, new IShopCar() {
            @Override
            public void onSuccess(Object... objects) {
                if (objects != null){
                    RegisterBean registerBean = (RegisterBean)objects[0];
                    if (registerBean.getCode().equals("200")){
                        mView.get().Success(registerBean.getMessage());
                        if (registerBean.getResult()!=null){
                            ShopUserManager.getInstance().setAddress(registerBean.getResult());
                        }
                    }
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
