package com.shopmall.bawei.shopcar.presenter;

import com.example.net.BaseBean;
import com.example.net.ConfigUrl;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.example.net.ShopcarBean;
import com.google.gson.JsonObject;
import com.shopmall.bawei.shopcar.contract.ShopcarContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopcarPresenterImpl extends ShopcarContract.ShopcarPresenter {

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
        new HttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .updateProductNum(requestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iView.onProductNumChange(stringBaseBean.getResult(),position,newNum);
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

        new HttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .updateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iView.onProductSelected(stringBaseBean.getResult(),position);
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
    public void selectAllProduct(boolean isAllSelect) {
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("selected",isAllSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        new HttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .upDataSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iView.onAllSelected(stringBaseBean.getResult());
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
    public void deleteProducts(List<ShopcarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for(ShopcarBean shopcarBean:products) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId", shopcarBean.getProductId());
                jsonObject.put("productName", shopcarBean.getProductName());
                jsonObject.put("url", shopcarBean.getUrl());
                jsonObject.put("productNum", shopcarBean.getProductNum());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());
        new HttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .removeManyProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iView.onDeleteProducts(stringBaseBean.getResult());
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
    public void checkInventory(List<ShopcarBean> products) {

    }

    @Override
    public void getOrderInfo(List<ShopcarBean> products) {

    }
}
