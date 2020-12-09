package com.shopmall.bawei.shopmall1805.detail;

import android.util.Log;

import com.bw.net.NetFunction;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.Basebean;
import com.bw.net.bean.ShopCarBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DetailPresenter extends DetailContract.DetailPresenter {
    @Override
    public void addProduct(String productId, String productNum, String productName, String url, String productPrice) {
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

        RetraficCreator.getiNetWorkApiService().addProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i("---", "onNext  addProduct : "+s);
                iView.onAddProductOk("加入购物车成功"+s);
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
    public void checkOneProductNum(String productId, String productNum) {
        HashMap<String,String> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productNum", productNum);

        RetraficCreator.getiNetWorkApiService().checkOneProductInventory(map)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String string) {
                        iView.onCheckOneProduct(string);
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
        RetraficCreator.getiNetWorkApiService().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String string) {
                        Log.i("---", "onNext: updateProductNum："+string);
                        iView.onProductNumChange(string);
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
