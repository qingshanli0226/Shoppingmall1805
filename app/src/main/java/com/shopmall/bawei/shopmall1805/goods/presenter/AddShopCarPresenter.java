package com.shopmall.bawei.shopmall1805.goods.presenter;

import com.shopmall.bawei.shopmall1805.goods.contract.AddShopCarContract;

import org.json.JSONException;
import org.json.JSONObject;

import http.BaseBean;
import http.MyHttp;
import http.NetFunction;
import http.ShopmallObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mvp.view.IView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddShopCarPresenter extends AddShopCarContract.AddShopCarPresenter {

    @Override
    public void addShopCar(String productId, String productNum, String productName, String url, String productPrice) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productNum", productNum);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
            jsonObject.put("productSelected",true);
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
                    iHttpView.showError(errorCode,errorMessage);
                    }
                });
    }
}