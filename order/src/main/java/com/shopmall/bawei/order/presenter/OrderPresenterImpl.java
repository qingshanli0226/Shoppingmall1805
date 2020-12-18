package com.shopmall.bawei.order.presenter;

import android.util.Log;

import com.example.net.BaseBean;
import com.example.net.ConfigUrl;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.example.net.LoginBean;
import com.shopmall.bawei.order.contract.OrderContract;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenterImpl extends OrderContract.OrderPresenter {


    @Override
    public void upDataPhone(String phone) {
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",phone);
         HttpRetrofitManager.getHttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .upDataUserPhone(map)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseBean<String> loginBeanBaseBean) {
                        Log.i("TAG", "onNext: "+loginBeanBaseBean.getMessage());
                        iView.onUpDataPhone(loginBeanBaseBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void upDataAddress(String address) {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);
         HttpRetrofitManager.getHttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .upDataUserAddress(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> loginBeanBaseBean) {
                        iView.onUpDataAddress(loginBeanBaseBean.getResult()+"");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
