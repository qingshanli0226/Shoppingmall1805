package com.shopmall.bawei.shopmall1805.product.presenter;

import com.example.net.BaseBean;
import com.example.net.ConfigUrl;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.shopmall.bawei.shopmall1805.product.contract.ProductDetailContract;

import org.json.JSONException;
import org.json.JSONObject;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ProductDetailPresenterImpl extends ProductDetailContract.ProductDetailPresenter {
    @Override
    public void checkOneProductNum(String productId, String productNum) {

            new HttpRetrofitManager()
                    .getRetrofit(ConfigUrl.BASE_URL)
                    .create(INetPresetenterWork.class)
                    .checkShop(productId,productNum)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            if (iView!=null){
                                iView.showsloading();
                            }
                        }
                    })
                    .subscribe(new Observer<BaseBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseBean baseBean) {
                            iView.onCheckOneProduct(baseBean.getResult().toString());
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (iView!=null){
                                iView.hideloading();
                            }
                        }

                        @Override
                        public void onComplete() {

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


        new HttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .addOneProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                            iView.showsloading();
                        }
                    }
                })
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        iView.onAddProduct(baseBean.getResult().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView!=null){
                            iView.hideloading();
                        }
                    }

                    @Override
                    public void onComplete() {

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


        new HttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                            iView.showsloading();
                        }
                    }
                })
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        iView.onProductNumChange(baseBean.getResult().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView!=null){
                            iView.hideloading();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
