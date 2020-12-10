package com.shopmall.bawei.framework.mvptest.model;

import android.util.Log;

import com.shopmall.bawei.framework.callback.IShopcar;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.Registbean;
import com.shopmall.bean.ShopcarBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopcarModel implements Constart.ShopcarConstartModel {

    @Override
    public void addshopcarData(String url, JSONObject jsonObject, final IShopcar iShopcar) {

        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getaddOneProduct(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                        Log.e("add","添加失败："+registbean.getMessage());
                         iShopcar.onSucess(registbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("add","添加异常："+e.getMessage());
                        iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //检查库存情况
    @Override
    public void checkOneProductInventory(String url, HashMap<String, String> map, final IShopcar iShopcar) {

        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getcheckOneProductInventory(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                        Log.e("check",""+registbean.getResult());
                        iShopcar.onSucess(registbean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("check",""+e.getMessage());
                        iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    //更新服务端购物车产品的选择
    @Override
    public void updateProductSelected(String url, ShopcarBean.ResultBean shopcar, final IShopcar iShopcar) {
              JSONObject jsonObject=new JSONObject();
        boolean productSelected = shopcar.isProductSelected();
        try {
            jsonObject.put("productId",shopcar.getProductId());
            if (productSelected){
                jsonObject.put("productSelected",false);
            }else {
                jsonObject.put("productSelected",true);
            }
            jsonObject.put("productName",shopcar.getProductName());
            jsonObject.put("url",shopcar.getUrl());
            jsonObject.put("productPrice",shopcar.getProductPrice());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());

        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                   .getupdateProductSelected(url,requestBody)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Observer<Registbean>() {
                       @Override
                       public void onSubscribe(Disposable d) {

                       }

                       @Override
                       public void onNext(Registbean registbean) {
                               iShopcar.onSucess(registbean);
                       }

                       @Override
                       public void onError(Throwable e) {
                               iShopcar.onError(e.getMessage());
                       }

                       @Override
                       public void onComplete() {

                       }
                   });
    }
    // 全选服务端购物车产品或者全不选
    @Override
    public void selectAllProduct(String url, boolean allselect, final IShopcar iShopcar) {
           JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("selected",allselect);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getselectAllProduct(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                        iShopcar.onSucess(registbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
