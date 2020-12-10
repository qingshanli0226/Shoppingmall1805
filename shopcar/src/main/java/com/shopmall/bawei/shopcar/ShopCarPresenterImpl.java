package com.shopmall.bawei.shopcar;

import com.example.net.RetrofitCreater;
import com.example.net.bean.RemoveManyProductBean;
import com.example.net.bean.SelectAllBean;
import com.example.net.bean.ShopCarBean;
import com.example.net.bean.UpdateProductNumBean;
import com.google.gson.JsonObject;
import com.shoppmall.common.adapter.error.ExceptionUtil;

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

public class ShopCarPresenterImpl extends ShopCarContract.ShopCarPresenter {

    @Override
    public void removeManyProduct(List<ShopCarBean.ResultBean> beans) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), beans.toString());

        RetrofitCreater.getiNetWorkApi().removeManyProduct(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<RemoveManyProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RemoveManyProductBean bean) {
                        iview.onRemoveManyOk(bean);
                        iview.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                       iview.onRemoveManyError(ExceptionUtil.getErrorBean(e));

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void selectAllProduct(boolean selected) {
        JsonObject object = new JsonObject();
        object.addProperty("selected",selected);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());

        RetrofitCreater.getiNetWorkApi().selectAllProduct(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<SelectAllBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SelectAllBean bean) {
                        iview.onSelectAllOk(bean);
                        iview.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onSelectAllError(ExceptionUtil.getErrorBean(e));

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void productNumChange(String id, final int num, String name, String url, String price) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId",id);
            jsonObject.put("productNum",num);
            jsonObject.put("productName",name);
            jsonObject.put("url",url);
            jsonObject.put("productPrice",price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        RetrofitCreater.getiNetWorkApi().updateProductNum(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<UpdateProductNumBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateProductNumBean bean) {
                        iview.onProductNumChangeOk(bean);
                        iview.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onProductNumChangeError(ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
