package com.shopmall.framework.mvptest.model;

import com.shopmall.framework.callback.IShopCar;
import com.shopmall.framework.contract.Contract;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.RegisterBean;
import com.shopmall.net.bean.ShopcarBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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
    public void destroy() {

    }
}
