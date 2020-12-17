package com.shopmall.bawei.shopmall1805.product.presenter;

import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.RetroCreator;
import com.shopmall.bawei.net.ShopmallObserver;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.shopmall1805.product.contract.ProductDetailContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
        RetroCreator.getShopmallApiServie().checkOneProductInventory(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaing();
                    }
                })
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (iHttpView!=null) {
                            iHttpView.onCheckOneProduct(s);
                        }
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(errorCode, errorMessage));

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
        RetroCreator.getShopmallApiServie().addOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaing();
                    }
                })

                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iHttpView.onAddProduct(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(errorCode, errorMessage));
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
        RetroCreator.getShopmallApiServie().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaing();
                    }
                })
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iHttpView.onProductNumChange(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(errorCode, errorMessage));

                    }
                });


    }


}
