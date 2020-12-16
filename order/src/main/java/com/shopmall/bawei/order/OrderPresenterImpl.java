package com.shopmall.bawei.order;

import com.example.net.RetrofitCreater;
import com.example.net.bean.CheckInventoryBean;
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

public class OrderPresenterImpl extends OrderContract.OrderPresenter {
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
                        iview.hideLoading(false, ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
