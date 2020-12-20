package com.shopmall.bawei.shopmall1805.goods.presenter;

import com.shopmall.bawei.shopmall1805.goods.contract.AddShopCarContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.logging.Handler;

import http.BaseBean;
import http.MyHttp;
import http.NetFunction;
import http.ShopmallObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddShopCarPresenter extends AddShopCarContract.AddShopCarPresenter {

    @Override
    public void addShopCar(String productId, String productNum, String productName, String url, String productPrice) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productNum", productNum);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
            jsonObject.put("productSelected", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        MyHttp.getShopmallApiService().addOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iHttpView.onAddShopCar(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.showError(errorCode, errorMessage);
                    }
                });
    }

    @Override
    public void checkOnewProductNum(String productId, String productNum) {
        HashMap<String, String> params = new HashMap<>();
        params.put("productId", productId);
        params.put("productNum", productNum);
        MyHttp.getShopmallApiService().checkOneProductInventory(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (iHttpView != null) {
                            iHttpView.onCheckOneProduct(s);
                        }
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {

                    }
                });


    }

    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productNum", productNum);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        MyHttp.getShopmallApiService().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iHttpView.onProductNumChange(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {

                    }
                });

    }
}