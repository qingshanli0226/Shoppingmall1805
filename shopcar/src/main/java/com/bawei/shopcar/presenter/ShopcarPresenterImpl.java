package com.bawei.shopcar.presenter;

import com.bawei.common.view.ExceptionUtil;
import com.bawei.net.NetFunction;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.ShopmallObserver;
import com.bawei.net.mode.BaseBean;
import com.bawei.net.mode.InventoryBean;
import com.bawei.net.mode.ShopcarBean;
import com.bawei.shopcar.contract.ShopcarContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class ShopcarPresenterImpl extends ShopcarContract.ShopcarPresenter {
    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice,
                                 final int position, final String newNum) {
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
        RetrofitCreate.getApi().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })

                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iView.onProductNumChange(s, position, newNum);
                        iView.hideLoading(true, null);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iView.hideLoading(false, ExceptionUtil.getErrorBean(errorCode, errorMessage));
                    }
                });


    }

    @Override
    public void updateProductSelected(final String productId, boolean productSelected, String productName, String url, String productPrice, final int position) {
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
        RetrofitCreate.getApi().updateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })

                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iView.onProductSelected(s, position);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iView.hideLoading(false, ExceptionUtil.getErrorBean(errorCode, errorMessage));
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

        RetrofitCreate.getApi().selectAllProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })

                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iView.onAllSelected(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iView.hideLoading(false, ExceptionUtil.getErrorBean(errorCode, errorMessage));
                    }
                });
    }

    @Override
    public void deleteProducts(List<ShopcarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for (ShopcarBean shopcarBean : products) {
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

        RetrofitCreate.getApi().removeManyProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iView.onDeleteProducts(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iView.hideLoading(false, ExceptionUtil.getErrorBean(errorCode, errorMessage));
                    }
                });
    }

    @Override
    public void checkInventory(List<ShopcarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for (ShopcarBean shopcarBean : products) {
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

        RetrofitCreate.getApi().checkInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<InventoryBean>>, List<InventoryBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new ShopmallObserver<List<InventoryBean>>() {
                    @Override
                    public void onNext(List<InventoryBean> inventoryBeans) {
                        iView.onInventory(inventoryBeans);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iView.hideLoading(false, ExceptionUtil.getErrorBean(errorCode, errorMessage));

                    }
                });


    }
}
