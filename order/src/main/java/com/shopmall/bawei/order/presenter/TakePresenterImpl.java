package com.shopmall.bawei.order.presenter;

import com.example.framework.CacheManager;
import com.example.net.BaseBean;
import com.example.net.ConfigUrl;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.example.net.IntonVoryBean;
import com.example.net.OrderInfoBean;
import com.example.net.ShopcarBean;
import com.shopmall.bawei.order.contract.TakeContract;

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

public class TakePresenterImpl extends TakeContract.TakePresenter {
    @Override
    public void checkIntonvory(List<ShopcarBean> shopcarBeanList) {
        JSONArray jsonArray = new JSONArray();
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId", shopcarBean.getProductId());
                jsonObject.put("productNum", shopcarBean.getProductNum());
                jsonObject.put("productName", shopcarBean.getProductName());
                jsonObject.put("url", shopcarBean.getUrl());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());
        HttpRetrofitManager.getHttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .checjInventory(requestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean<List<IntonVoryBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<IntonVoryBean>> listBaseBean) {
                        iView.onCheckIntonvory(listBaseBean.getResult());
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
    public void orderinfo(List<ShopcarBean> shopcarBeanList) {
        JSONArray jsonArray = new JSONArray();
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productName", shopcarBean.getProductName());
                jsonObject.put("productId", shopcarBean.getProductId());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject object = new JSONObject();
        try {
            object.put("subject", "buy");
            object.put("totalPrice", CacheManager.getInstance().getMoneyValue());
            object.put("body",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        HttpRetrofitManager.getHttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .getOrderInfo(requestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean<OrderInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<OrderInfoBean> orderInfoBeanBaseBean) {
                        iView.getOrderInfo(orderInfoBeanBaseBean.getResult());
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
    public void ConfirmServerPayResult(OrderInfoBean orderInfoBean, boolean flag) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("outTradeNo",orderInfoBean.getOutTradeNo());
            jsonObject.put("result",orderInfoBean.getOrderInfo());
            jsonObject.put("clientPayResult",flag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        HttpRetrofitManager.getHttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .confirmServerPayResult(requestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iView.getConfirmServerPayResult(stringBaseBean.getResult());
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
