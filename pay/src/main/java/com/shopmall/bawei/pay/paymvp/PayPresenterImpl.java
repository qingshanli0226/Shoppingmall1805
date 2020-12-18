package com.shopmall.bawei.pay.paymvp;

import com.example.net.NetFunction;
import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.framework.example.framework.manager.CacheManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PayPresenterImpl extends PayContract.orderPresenter {
    @Override
    public void updatePhoneData(String numPhone) {
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",numPhone);
        Retrofitcreators.getiNetPresetenterWork().updatePhone(map)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction< BaseBean<String>,String >())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        iView.updatePhone(s);
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
    public void updateAddressData(String address) {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);

        Retrofitcreators.getiNetPresetenterWork().updateAddress(map)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        iView.updateAddress(s);
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
        JSONArray jsonArray = new JSONArray();
        for(ShopcarBean shopcarBean:products) {
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

        Retrofitcreators.getiNetPresetenterWork().checkInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<InventoryBean>>,List<InventoryBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<InventoryBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<InventoryBean> inventoryBeans) {
                        iView.onInventory(inventoryBeans);
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
    public void getOrderInfo(List<ShopcarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for(ShopcarBean shopcarBean:products) {
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

        Retrofitcreators.getiNetPresetenterWork().getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<OrderInfoBean>,OrderInfoBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderInfoBean orderInfoBean) {
                        iView.onOrderInfo(orderInfoBean);
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
