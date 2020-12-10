package com.bawei.deom.selectedordelete;

import com.bawei.deom.ClassInterface;
import com.bawei.deom.NetFunction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import bean.BaseBean;
import bean.Shoppingcartproducts;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopcarPresenterImpl extends ShopcarContract.SelectedandDeletedCountrollerShow {

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
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        ClassInterface.getUserInterface().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new NetFunction<BaseBean<String>,String>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        pView.onProductNumChange(s,position,newNum);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, final int position) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("productId",productId);
            jsonObject.put("productSelected",productSelected);
            jsonObject.put("productName",productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice",productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        ClassInterface.getUserInterface().updateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new NetFunction<BaseBean<String>,String>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        pView.ononProductSelected(s,position);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void selectAllProduct(boolean isAllSelect) {
      JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("selected",isAllSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        ClassInterface.getUserInterface().selectAllProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new NetFunction<BaseBean<String>,String>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                      pView.onAllSelected(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void deleteProducts(List<Shoppingcartproducts.ResultBean> products) {

    }

    @Override
    public void checkInventory(List<Shoppingcartproducts.ResultBean> products) {

    }

    @Override
    public void getOrderInfo(List<Shoppingcartproducts.ResultBean> products) {

    }
}
