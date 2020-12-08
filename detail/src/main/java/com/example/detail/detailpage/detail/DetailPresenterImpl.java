package com.example.detail.detailpage.detail;

import com.example.net.RetrofitCreater;
import com.example.net.bean.AddProductBean;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DetailPresenterImpl extends DetailContract.DetailPresenter {
    @Override
    public void addProduct(String id,int num,String name,String url,String price) {
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
        RetrofitCreater.getiNetWorkApi().addOneProduct(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddProductBean bean) {
                        iview.onOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
