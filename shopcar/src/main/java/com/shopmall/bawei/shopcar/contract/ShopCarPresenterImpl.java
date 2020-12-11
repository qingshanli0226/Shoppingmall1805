package com.shopmall.bawei.shopcar.contract;

import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.ShopCarBean;

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

public class ShopCarPresenterImpl extends ShopCarContract.IShopCarPresenter {
    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, final int position, final String newNum) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productNum", newNum);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        OkHttpHelper.getApi().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        iView.onProductNumChanges(s,position,newNum);
                        iView.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.hideLoading(false, ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, final int position) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productSelected", productSelected);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        OkHttpHelper.getApi().updateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        iView.onProductSelected(s,position);
                        iView.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.hideLoading(false,ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void selectAllProduct(boolean isAllSelect) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", isAllSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        OkHttpHelper.getApi().selectAllProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        iView. onAllSelected(s);
                        iView.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.hideLoading(false,ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void deleteProducts(List<ShopCarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for(ShopCarBean shopcarBean:products) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId", shopcarBean.getProductId());
                jsonObject.put("productName", shopcarBean.getProductName());
                jsonObject.put("url", shopcarBean.getUrl());
                jsonObject.put("productNum", shopcarBean.getProductNum());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());
        OkHttpHelper.getApi().removeManyProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        iView.onDeleteProducts(s);
                        iView.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.hideLoading(false,ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
