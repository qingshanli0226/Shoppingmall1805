package com.example.elevenmonthshoppingproject.product.presenter;



import com.example.elevenmonthshoppingproject.product.contract.ProductDetailContract;
import com.example.net.NetFunction;
import com.example.net.RetorfitCreate;
import com.example.net.ShopMallObserver;
import com.example.net.bean.BaseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ProductDetailPresenterImpl extends ProductDetailContract.ProductDetailPresenter {
    @Override
    public void checkOneProductNum(String productId, String productNum) {

        HashMap<String,String> params = new HashMap<>();
        params.put("productId", productId);
        params.put("productNum", productNum);
        RetorfitCreate.getiNetworkserviceimpl().checkOneProductInventory(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iview.onCheckOneProduct(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {

                    }
                });

    }

    @Override
    public void addOneProduct(String productId, String productNum, String productName, String url, String productPrice) {
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
        RetorfitCreate.getiNetworkserviceimpl().addOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iview.onAddProduct(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {

                    }
                });

    }

    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice) {
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
        RetorfitCreate.getiNetworkserviceimpl().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver <String>() {
                    @Override
                    public void onNext(String s) {
                        iview.onProductNumChange(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {

                    }
                });


    }


}
