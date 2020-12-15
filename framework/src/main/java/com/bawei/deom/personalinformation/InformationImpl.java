package com.bawei.deom.personalinformation;

import android.support.v4.app.INotificationSideChannel;

import com.bawei.deom.ClassInterface;

import java.util.HashMap;

import bean.PhoneBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InformationImpl extends InformationCountroller.InformatioShow {

    @Override
    public void UpdateAddressShow(String address) {
        HashMap<String,String> map=new HashMap<>();
        map.put("address",address);
        ClassInterface.getUserInterface().updateAddress(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhoneBean address) {
                           pView.onupdateAddressView(address);
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
    public void UpdatePhoneShow(String phone) {
        HashMap<String,String> map=new HashMap<>();
        map.put("phone",phone);
        ClassInterface.getUserInterface().updatePhone(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhoneBean phoneBean) {
                        pView.onupdatePhoneView(phoneBean);
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
