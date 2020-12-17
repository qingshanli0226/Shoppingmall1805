package com.shopmall.bawei.order.model;

import android.util.Log;

import com.shopmall.bawei.order.IPassBack;

import net.FoodService;
import net.RxjavaRetortUlis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import framework.Contact;
import framework.ShopUserManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mode.BaseBean;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public
class OrderModel implements Contact.ICenterOrderIModel {

    @Override
    public void goBindingPhone(String Url) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phone", Url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);
        foodService.getUpdatePhone(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseBean<String> baseBean) {
                        Log.i("====","电话数据成功请求"+baseBean.getCode()+"返回"+baseBean.getResult());
                        IPassBack.getInstance().addData(baseBean);
                        IPassBack.getInstance().Updata();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("====","===="+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void goBindingPoint(String Url) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("address", Url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);
        foodService.getUpdateAddress(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> baseBean) {
                        Log.i("====","地址数据成功请求"+baseBean.getResult()+"返回"+baseBean.getResult());
                        IPassBack.getInstance().addData(baseBean);
                        IPassBack.getInstance().Updata();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("====","===="+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
