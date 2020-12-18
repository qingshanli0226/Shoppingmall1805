package com.shopmall.bawei.shopmall1805.ui.activity.checkmvp;

import android.util.Log;

import com.example.net.NetFunction;
import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ProductDetailPresenterImpl extends ProductDetailContract.ProductDetailPresenter {
    HashMap<String,String> map;

    @Override
    public void checkOneProductNum(String productId, String productNum) {
        Log.i("---", "检查库存"+productNum);
        HashMap<String,String> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productNum",productNum);
        Retrofitcreators.getiNetPresetenterWork().checkOneProductInventoryBean(map)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("---", "onNext: checkProductNum："+s);
                        iView.onCheckOneProduct(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("---", "onError: checkProductNumError："+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addOneProduct(String productId, String productNum, String productName, String url, String productPrice) {
        Log.e("---","addOneProduct");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productNum", productNum);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
            jsonObject.put("productSelected",true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        Retrofitcreators.getiNetPresetenterWork().addOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("---", "onNext: addProduct"+s);
                        iView.onAddProduct(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("---", "onError: addProductError"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice) {
        Log.e("---","updateProductNum");
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

        Retrofitcreators.getiNetPresetenterWork().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        iView.onProductNumChange(s);
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
