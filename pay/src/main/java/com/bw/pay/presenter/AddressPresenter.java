package com.bw.pay.presenter;

import android.util.Log;

import com.bw.framework.CacheManager;
import com.bw.net.InventoryBean;
import com.bw.net.NetFunction;
import com.bw.net.OrderInfoBean;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.Basebean;
import com.bw.net.bean.Bean;
import com.bw.net.bean.ShopCarBean;
import com.bw.pay.contract.AddressContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddressPresenter extends AddressContract.IAddressPresenter {

    @Override
    public void updateNumber(String number) {
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",number);
        RetraficCreator.getiNetWorkApiService().updatePhone(map)
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
                        Log.i("---", "onNext: updatePhone："+s);
                        iView.onUpdateNumberOk(s);
                        iView.hidesLoading(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                        iView.hidesLoading(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void updateAddress(String address) {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);
        RetraficCreator.getiNetWorkApiService().updateAddress(map)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        iView.onUpdateAddressOk(s);
                        Log.i("---", "onNext: updateAddress："+s);
                        iView.hidesLoading(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                        iView.hidesLoading(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void checkInventory(List<ShopCarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for(ShopCarBean shopcarBean:products) {
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

        RetraficCreator.getiNetWorkApiService().checkInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<List<InventoryBean>>,List<InventoryBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                })
               .subscribe(new Observer<List<InventoryBean>>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(List<InventoryBean> inventoryBeans) {
                       iView.onInventory(inventoryBeans);
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
    public void getOrderInfo(List<ShopCarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for(ShopCarBean shopcarBean:products) {
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
            object.put("totalPrice", CacheManager.getInstance().getMoneyValues());
            object.put("body",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());

        RetraficCreator.getiNetWorkApiService().getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<OrderInfoBean>,OrderInfoBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderInfoBean orderInfoBean) {
                        iView.onOrderInfo(orderInfoBean);
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
    public void confirmServerPayResult(Bean bean) {
        /**
         * outTradeNo : 112810091743722
         * result : {"alipay_trade_app_pay_response":{"code":"10000","msg":"Success","app_id":"2016092200570833","auth_app_id":"2016092200570833","charset":"utf-8","timestamp":"2019-11-28 10:13:39","out_trade_no":"112810091743722","total_amount":"500.00","trade_no":"2019112822001427891000068428","seller_id":"2088102176778571"},"sign":"hDLn0JJ3M2DBu6i9aQCzw7W2SZ+gldu1Y2qDfOMXEdgj81vfVfXuCqOaX25aAi+StMuGfVb3Y5W495LaKPo5ItYo+HerhIBVFfAIyvNzC6XTLXGu7XEWXZumjMhT8EQbIRWFk76f4okzaqFNRqsE1\/VN8IwPVtEIjIpMBE\/YXBlQvk0k+uP9M6KNlli4UpaBdYu1GeNrd282X+IgDgMJje39yhS2blrvKrsT+pNuhyMsKX3RJlnoMYVjujEYgQZst5fs\/9zunNXC\/w0axcOIaePYU5eY7kQ7kuFcSy6JEeoycrA9o7hBWQZVKir8mDiMnwWKsE+rn9Ue4XNNSsCDdA==","sign_type":"RSA2"}
         * clientPayResult : true
         */
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("outTradeNo", bean.getOutTradeNo());
            jsonObject.put("result",bean.getResult());
            jsonObject.put("clientPayResult",bean.isClientPayResult());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetraficCreator.getiNetWorkApiService().confirmServerPayResultv(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("---", "onNext: confirmServerPayResultv："+s);
                        iView.onConfirmServerPayResult(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
