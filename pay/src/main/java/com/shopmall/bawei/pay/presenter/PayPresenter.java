package com.shopmall.bawei.pay.presenter;

import com.example.framework.CacheManager;
import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.IntonVoryBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.pay.contract.PayContract;

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

public class PayPresenter extends PayContract.IOrderPresenter {
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
        Retrofitcreators.getiNetPresetenterWork().checjInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsloading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<IntonVoryBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<IntonVoryBean>> listBaseBean) {
                        if (listBaseBean.getCode().equals("200")){
                            iView.hideloading();
                            iView.onCheckIntonvory(listBaseBean.getResult());
                        }else {
                            iView.hideloading();
                            iView.onErroy(listBaseBean.getMessage());
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
            object.put("totalPrice", CacheManager.getInstance().getMoney());
            object.put("body",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        Retrofitcreators.getiNetPresetenterWork().getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsloading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<OrderInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<OrderInfoBean> orderInfoBeanBaseBean) {
                        if (orderInfoBeanBaseBean.getCode().equals("200")){
                            iView.hideloading();
                            iView.getOrderInfo(orderInfoBeanBaseBean.getResult());
                        }else {
                            iView.hideloading();
                            iView.onErroy(orderInfoBeanBaseBean.getMessage());
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
