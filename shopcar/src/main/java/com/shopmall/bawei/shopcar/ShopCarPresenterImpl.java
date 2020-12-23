package com.shopmall.bawei.shopcar;

import android.util.Log;

import com.example.framework.manager.CacheManager;
import com.example.net.RetrofitCreater;
import com.example.net.bean.CheckInventoryBean;
import com.example.net.bean.GetOrderInfoBean;
import com.example.net.bean.OrderBean;
import com.example.net.bean.RemoveManyProductBean;
import com.example.net.bean.SelectAllBean;
import com.example.net.bean.ShopCarBean;
import com.example.net.bean.UpdateProductNumBean;
import com.example.net.bean.UpdateProductSelectedBean;
import com.google.gson.JsonObject;
import com.shoppmall.common.adapter.error.ExceptionUtil;

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

public class ShopCarPresenterImpl extends ShopCarContract.ShopCarPresenter {

    @Override
    public void removeManyProduct(List<ShopCarBean.ResultBean> beans) {
        JSONArray jsonArray = new JSONArray();
        for (ShopCarBean.ResultBean bean : beans) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId",bean.getProductId());
                jsonObject.put("productNum",bean.getProductNum());
                jsonObject.put("productName",bean.getProductName());
                jsonObject.put("url",bean.getUrl());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonArray.toString());
        RetrofitCreater.getiNetWorkApi().removeManyProduct(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<RemoveManyProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RemoveManyProductBean bean) {
                        iview.hideLoading(true,null);
                        iview.onRemoveManyOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Yoyo", "onError: "+e.getMessage());
                       iview.onError(ExceptionUtil.getErrorBean(e));

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void selectAllProduct(boolean selected) {
        JsonObject object = new JsonObject();
        object.addProperty("selected",selected);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());

        RetrofitCreater.getiNetWorkApi().selectAllProduct(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<SelectAllBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SelectAllBean bean) {
                        iview.onSelectAllOk(bean);
                        iview.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(ExceptionUtil.getErrorBean(e));

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void productNumChange(String id, final int num, String name, String url, String price) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",id);
            jsonObject.put("productNum",num);
            jsonObject.put("productName",name);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        RetrofitCreater.getiNetWorkApi().updateProductNum(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<UpdateProductNumBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateProductNumBean bean) {
                        iview.onProductNumChangeOk(bean);
                        iview.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void productSelectChange(String id, boolean productSelected, String name, String url, String price) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",id);
            jsonObject.put("productSelected",productSelected);
            jsonObject.put("productName",name);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        RetrofitCreater.getiNetWorkApi().updateProductSelected(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<UpdateProductSelectedBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateProductSelectedBean bean) {
                        iview.onProductSelectChangeOk(bean);
                        iview.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void checkInventory(List<OrderBean> list) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (OrderBean orderBean : list) {
                JSONObject object = new JSONObject();
                object.put("productId",orderBean.getProductId());
                object.put("productNum",orderBean.getProductNum());
                object.put("productName",orderBean.getProductName());
                object.put("url",orderBean.getUrl());
                jsonArray.put(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());
        RetrofitCreater.getiNetWorkApi().checkInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<CheckInventoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CheckInventoryBean bean) {
                        iview.hideLoading(true,null);
                        iview.onCheckOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getOrderInfo(List<ShopCarBean.ResultBean> list) {
        JSONObject object = new JSONObject();
        JSONArray body = new JSONArray();
        try {
            object.put("subject","buy");
            object.put("totalPrice", CacheManager.getInstance().getMoneyValue()+"");
            for (ShopCarBean.ResultBean resultBean : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("productName",resultBean.getProductName());
                jsonObject.put("productId",resultBean.getProductId());
                body.put(jsonObject);
            }
            object.put("body",body);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        RetrofitCreater.getiNetWorkApi().getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<GetOrderInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetOrderInfoBean bean) {
                        iview.hideLoading(true,null);
                        iview.onGetOrderInfoOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
