package com.example.shopercar.presenter;

import com.example.framwork.CacheManager;
import com.example.net.InventoryBean;
import com.example.net.NetFunction;
import com.example.net.OrderInfoBean;
import com.example.net.RetorfitCreate;
import com.example.net.ShopMallObserver;
import com.example.net.bean.BaseBean;
import com.example.net.bean.ShopcarBean;
import com.example.shopercar.contract.ShopCarContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopCarPresenterImpl extends ShopCarContract.ShopCarPresenter {
    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, final int position, final String newNum) {
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
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (iview!=null){
                            iview.onProductNumChange(s,position,newNum);
                        }




                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iview.onError(errorCode,errorMessage);
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
        RetorfitCreate.getiNetworkserviceimpl().updateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (iview!=null){
                            iview.onProductSelect(s, position);
                        }else {

                        }

                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iview.onError(errorCode,errorMessage);
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

        RetorfitCreate.getiNetworkserviceimpl().selectAllProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (iview!=null){
                            iview.onAllSelect(s);
                        }else {

                        }

                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
//                        iview.onError(errorCode, errorMessage);
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

        RetorfitCreate.getiNetworkserviceimpl().removeManyProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (iview!=null){
                            iview.onDeleteProduct(s);
                        }

                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iview.onError(errorCode, errorMessage);
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

        RetorfitCreate.getiNetworkserviceimpl().checkInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<InventoryBean>>, List<InventoryBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<List<InventoryBean>>() {
                    @Override
                    public void onNext(List<InventoryBean> inventoryBeans) {
                        if (iview!=null){
                            iview.onInventory(inventoryBeans);
                        }

                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iview.onError(errorCode, errorMessage);

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

        RetorfitCreate.getiNetworkserviceimpl().getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<OrderInfoBean>,OrderInfoBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<OrderInfoBean>() {
                    @Override
                    public void onNext(OrderInfoBean orderInfoBean) {
                        if (iview!=null){
                            iview.onOnderInfo(orderInfoBean);
                        }else {

                        }

                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iview.onError(errorCode, errorMessage);
                    }
                });
    }

    @Override
    public void getConfirmServerPay() {

    }

}
