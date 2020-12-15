package com.shopmall.bawei.user.telandadress;

import com.example.net.RetrofitCreater;
import com.example.net.bean.UpDateAddressBean;
import com.example.net.bean.UpdatePhoneBean;
import com.shoppmall.common.adapter.error.ExceptionUtil;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BindTelAndAddressPresenterImpl extends BindTelAndAddressContract.BindTelAndAddressPresenter {
    @Override
    public void bindTel(String tel) {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",tel);
        RetrofitCreater.getiNetWorkApi().updatePhone(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<UpdatePhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdatePhoneBean bean) {
                        iview.hideLoading(true,null);
                        iview.onBindTelOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.hideLoading(false, ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void bindAddress(String adress) {
        HashMap<String, String> map = new HashMap<>();
        map.put("address",adress);
        RetrofitCreater.getiNetWorkApi().updateAddress(map)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpDateAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        iview.showloading();
                    }

                    @Override
                    public void onNext(UpDateAddressBean upDateAddressBean) {
                        iview.hideLoading(true,null);
                        iview.onBindAddressOk(upDateAddressBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.hideLoading(false,ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
