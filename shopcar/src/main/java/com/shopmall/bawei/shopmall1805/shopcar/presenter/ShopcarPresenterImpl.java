package com.shopmall.bawei.shopmall1805.shopcar.presenter;


import android.media.MediaRecorder;

import com.google.gson.JsonObject;
import com.shopmall.bawei.shopmall1805.common.ExceptionUtils;
import com.shopmall.bawei.shopmall1805.framework.service.CacheManager;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;
import com.shopmall.bawei.shopmall1805.net.entity.BaseBean;
import com.shopmall.bawei.shopmall1805.net.entity.OrderInfoBean;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;
import com.shopmall.bawei.shopmall1805.shopcar.contract.ShopcarContract;

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
    public void upDateSelect(String id, boolean select, String name, String url, String price, final int position) {//更改条目选中状态和数据内容
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",id);
            jsonObject.put("productSelected",select);
            jsonObject.put("productName",name);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitUtils.getiNetPresetenterWork().updateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<String>>(){
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iHttpView.onUpdateSelect(stringBaseBean.getResult(),position);
               }

                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {
                        iHttpView.hideLoading(false, ExceptionUtils.getErrorBean(errorCold,errorMsg));
                    }
                });
    }
    @Override
    public void upNumberChanger(String id, String numer, String name, String url, String price, final int position, final String newNum) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", id);
            jsonObject.put("productNum", numer);
            jsonObject.put("productName", name);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitUtils.getiNetPresetenterWork().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<String>>(){
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iHttpView.onNumberChanger(stringBaseBean.getResult(),position,newNum);
                }

                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {
                        iHttpView.hideLoading(false, ExceptionUtils.getErrorBean(errorCold,errorMsg));
                    }
                });
    }
    @Override
    public void upSelectAll(boolean isSelect) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", isSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitUtils.getiNetPresetenterWork().selectAllProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<String>>(){
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iHttpView.onAllSelect(stringBaseBean.getResult());
                    }

                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {
                        iHttpView.hideLoading(false, ExceptionUtils.getErrorBean(errorCold,errorMsg));
                 }
         });
    }
    @Override
    public void deleteProducts(List<ShopcarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for(ShopcarBean shopcarBean:products) {
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
        RetrofitUtils.getiNetPresetenterWork().removeManyProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<String>>(){
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iHttpView.onDeleteProducts(stringBaseBean.getResult());
                    }
                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {
                        iHttpView.hideLoading(false, ExceptionUtils.getErrorBean(errorCold,errorMsg));
                }
         });
    }
    @Override
    public void getOrderInfo(List<ShopcarBean> shopcarBeanList) {
        JSONArray jsonArray = new JSONArray();
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productName",shopcarBean.getProductName());
                jsonObject.put("productId",shopcarBean.getProductId());
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

        RequestBody requestBody =  RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());

        RetrofitUtils.getiNetPresetenterWork().getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<OrderInfoBean>>() {
                    @Override
                    public void onNext(BaseBean<OrderInfoBean> orderInfoBeanBaseBean) {
                        OrderInfoBean result = orderInfoBeanBaseBean.getResult();
                        iHttpView.onOrderInfoBean(result);
                    }
                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {

                    }
                });
    }
}
