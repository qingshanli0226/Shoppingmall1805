package com.shopmall.bawei.shopmall1805.ui.activity.presenter;

import android.media.MediaPlayer;

import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.google.gson.JsonObject;
import com.shopmall.bawei.shopmall1805.ui.activity.contract.GoodsInfoContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GoodInfoPresenter extends GoodsInfoContract.IGoodPresenter {
    @Override
    public void CheckOneproduct(String productId, String productNum) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("productId",productId);
        hashMap.put("productNum",productNum);
        Retrofitcreators.getiNetPresetenterWork().checkoneproductInventory(hashMap)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showsloading();
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<BaseBean<String>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(BaseBean<String> stringBaseBean) {
                    if (stringBaseBean.getCode().equals("200")){
                        iView.hideloading();
                        iView.onCheckOneproduct(stringBaseBean.getResult());
                    }else {
                        iView.hideloading();
                        iView.onErroy(stringBaseBean.getMessage());
                    }
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onComplete() {

                }
            });
    }

    @Override
    public void AddOneproduct(String productId, String productNum, String productName, String url, String productPrice) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",productId);
            jsonObject.put("productNum",productNum);
            jsonObject.put("productName",productName);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",productPrice);
            jsonObject.put("producrSelected",true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        Retrofitcreators.getiNetPresetenterWork().addOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsloading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        if (stringBaseBean.getCode().equals("200")){
                            iView.hideloading();
                            iView.onAddOneproduct(stringBaseBean.getResult());
                        }else {
                            iView.hideloading();
                            iView.onErroy(stringBaseBean.getMessage());
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void Productchanged(String productId, String productNum, String productName, String url, String productPrice) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",productId);
            jsonObject.put("productNum",productNum);
            jsonObject.put("productName",productName);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        Retrofitcreators.getiNetPresetenterWork().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsloading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        if (stringBaseBean.getCode().equals("200")){
                            iView.hideloading();
                            iView.onProductchanged(stringBaseBean.getResult());
                        }else {
                            iView.hideloading();
                            iView.onErroy(stringBaseBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
