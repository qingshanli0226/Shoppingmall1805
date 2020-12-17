package com.shopmall.framework.mvptest.model;

import com.shopmall.framework.callback.IShopCar;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.manager.CacheManager;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.CheckBean;
import com.shopmall.net.bean.RegisterBean;
import com.shopmall.net.bean.ShopcarBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopCarModel implements Contract.ShopCarContractModel {
    @Override
    public void addShopCarData(String url, JSONObject jsonObject, final IShopCar iShopCar) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitFactory.getInstance().create(Https.class)
                .getAddOneProduct(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        iShopCar.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopCar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void checkOneProductInventory(String url, HashMap<String, String> map, final IShopCar iShopCar) {
        RetrofitFactory.getInstance().create(Https.class)
                .getCheckOneProductInventory(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        iShopCar.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopCar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateProductSelected(String url, ShopcarBean.ResultBean shopCar, final IShopCar iShopCar) {
        JSONObject jsonObject = new JSONObject();
        boolean productSelected = shopCar.isProductSelected();
        try {
            jsonObject.put("productId",shopCar.getProductId());
            if (productSelected){
                jsonObject.put("productSelected",false);
            }else {
                jsonObject.put("productSelected",true);
            }
            jsonObject.put("productName",shopCar.getProductName());
            jsonObject.put("url",shopCar.getUrl());
            jsonObject.put("productPrice",shopCar.getProductPrice());
        }catch (JSONException e){
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitFactory.getInstance().create(Https.class)
                .getUpdateProductSelected(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        iShopCar.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopCar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void selectAllProduct(String url, boolean allSelect, final IShopCar iShopCar) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected",allSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitFactory.getInstance().create(Https.class)
                .getSelectAllProduct(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        iShopCar.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopCar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateProductNum(String url, String num, ShopcarBean.ResultBean shopCar, final IShopCar iShopCar) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",shopCar.getProductId());
            jsonObject.put("productName",shopCar.getProductName());
            jsonObject.put("url",shopCar.getUrl());
            jsonObject.put("productNum",num);
            jsonObject.put("productPrice",shopCar.getProductPrice());
        }catch (JSONException e){
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitFactory.getInstance().create(Https.class)
                .getUpdateProductNum(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        iShopCar.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopCar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void removeManyProduct(String url, final IShopCar iShopCar) {
        List<ShopcarBean.ResultBean> deleteshopcarBeanList = CacheManager.getInstance().getDeleteShopCarBeanList();
        JSONArray jsonArray=new JSONArray();
        for (ShopcarBean.ResultBean resultBean : deleteshopcarBeanList) {
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("productId",resultBean.getProductId());
                jsonObject.put("productNum",resultBean.getProductNum());
                jsonObject.put("productName",resultBean.getProductName());
                jsonObject.put("url",resultBean.getUrl());
                jsonObject.put("productPrice",resultBean.getProductPrice());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());
        RetrofitFactory.getInstance().create(Https.class)
                .getRemoveManyProduct(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        iShopCar.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopCar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void checkInventory(String url, final IShopCar iShopCar) {
        List<ShopcarBean.ResultBean> shopCarBeanList = CacheManager.getInstance().getShopCarBeanList();
        JSONArray jsonArray=new JSONArray();
        for (ShopcarBean.ResultBean resultBean : shopCarBeanList) {
            if (resultBean.isProductSelected()){
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("productId",resultBean.getProductId());
                    jsonObject.put("productNum",resultBean.getProductNum());
                    jsonObject.put("productName",resultBean.getProductName());
                    jsonObject.put("url",resultBean.getUrl());
                    jsonObject.put("productPrice",resultBean.getProductPrice());
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());
        RetrofitFactory.getInstance().create(Https.class)
                .getCheckInventory(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CheckBean checkBean) {
                        iShopCar.onSuccess(checkBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopCar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updatePhone(String url, String phone, final IShopCar iShopCar) {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",phone);
        RetrofitFactory.getInstance().create(Https.class)
                .getUpdatePhone(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        iShopCar.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopCar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateAddress(String url, String address, final IShopCar iShopCar) {
        HashMap<String, String> map = new HashMap<>();
        map.put("address",address);
        RetrofitFactory.getInstance().create(Https.class)
                .getUpdateAddress(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        iShopCar.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopCar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void destroy() {

    }
}
