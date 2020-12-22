package com.shopmall.bawei.shopmall1805.app.presenter;

import com.shopmall.bawei.shopmall1805.app.contract.ShopCarContract;
import com.shopmall.bawei.shopmall1805.common.ExceptionUtils;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;
import com.shopmall.bawei.shopmall1805.net.entity.BaseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopCarPresenterImpl extends ShopCarContract.ProductDetailPresenter {

    @Override
    public void checkOneProductNum(String productId, String productNum) {
        HashMap<String,String> params = new HashMap<>();
        params.put("productId", productId);
        params.put("productNum", productNum);
        RetrofitUtils.getiNetPresetenterWork().checkOneProductInventory(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<String>>(){
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iHttpView.onCheckOneProduct(stringBaseBean.getResult());
                 }
                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {
                        iHttpView.hideLoading(false, ExceptionUtils.getErrorBean(errorCold,errorMsg));
                    }
                });
    }
    @Override
    public void addOneProduct(String productId, String productNum, String productName, String url, String productPrice) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",productId);
            jsonObject.put("productNum",productNum);
            jsonObject.put("productName",productName);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",productPrice);
            jsonObject.put("productSelected",true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        RetrofitUtils.getiNetPresetenterWork().addOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<String>>(){
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        String result = stringBaseBean.getResult();
                        iHttpView.onAddProduct(result);
                    }

                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {

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
        RetrofitUtils.getiNetPresetenterWork().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<String>>(){
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iHttpView.onProductNumChange(stringBaseBean.getResult());
                    }

                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {

                    }
                });

    }
}
