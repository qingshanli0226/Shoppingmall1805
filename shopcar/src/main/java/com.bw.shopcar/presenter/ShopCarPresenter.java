package com.bw.shopcar.presenter;

import android.util.Log;

import com.bw.net.NetFunction;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.Basebean;
import com.bw.net.bean.ShopCarBean;
import com.bw.shopcar.contract.ShopCarContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopCarPresenter extends ShopCarContract.IShopCarPresenter {
    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, final int position, final String newNum) {

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
                .map(new NetFunction<Basebean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String string) {
                        Log.i("---", "onNext: updataNum："+string);
                        iView.onProductNumChange(string,position,newNum);
                        iView.hidesLoading(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.hidesLoading(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, final int position) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productSelected", productSelected);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetraficCreator.getiNetWorkApiService().updateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String string) {
                        iView.onProductSelected(string,position);
                        iView.hidesLoading(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.hidesLoading(false);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void selectAllProduct(final boolean isAllSelect) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", isAllSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetraficCreator.getiNetWorkApiService().selectAllProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                })
               .subscribe(new Observer<String>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(String s) {
                       Log.i("---", "onNext: allSelect:"+s);
                       iView.onAllSelected(s);
                       iView.hidesLoading(true);
                   }

                   @Override
                   public void onError(Throwable e) {
                       iView.hidesLoading(false);
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }

    @Override
    public void deleteProducts(List<ShopCarBean> shopCarBeans) {

        JSONArray jsonArray = new JSONArray();
        for (ShopCarBean shopCarBean : shopCarBeans) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId",shopCarBean.getProductId());
                jsonObject.put("productName",shopCarBean.getProductName());
                jsonObject.put("productNum",shopCarBean.getProductNum());
                jsonObject.put("productPrice",shopCarBean.getProductPrice());
                jsonObject.put("url",shopCarBean.getUrl());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());

        RetraficCreator.getiNetWorkApiService().removeManyProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("---", "onNext: deleteProduct："+s);
                        iView.onDeleteProducts(s);
                        iView.hidesLoading(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                        Log.i("---", "onError: deleteProduct："+e.getMessage());
                        iView.hidesLoading(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
