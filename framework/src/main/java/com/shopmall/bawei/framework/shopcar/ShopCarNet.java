package com.shopmall.bawei.framework.shopcar;

import android.util.Log;

import com.shopmall.bawei.framework.callback.Itest;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.Registbean;
import com.shopmall.manager.ShopCarmanager;

import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopCarNet {
     private volatile static ShopCarNet shopCarNet;

     public static ShopCarNet getShopCarNet(){
           if (null==shopCarNet){
               synchronized (ShopCarNet.class){
                   if (shopCarNet==null){
                       shopCarNet=new ShopCarNet();
                   }
               }
           }
           return shopCarNet;
     }

    /**
     * 添加数据
     * @param url
     * @param jsonObject
     */
     public void addshopcarData(String url, JSONObject jsonObject){
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
                         ShopCarmanager.getShopCarmanager().ShopcarData();
                     }

                     @Override
                     public void onError(Throwable e) {
                         Log.e("add","添加异常："+e.getMessage());
                     }

                     @Override
                     public void onComplete() {

                     }
                 });


     }

    /**
     * 检验库存情况
     */
    public void checkOneProductInventory(String url, HashMap<String,String> map, final Itest itest){
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
                                 itest.ontest(registbean.getResult());

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("check",""+e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }

}
