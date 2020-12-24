package com.shopmall.bawei.shopmall1805.myfragment.presenter;

import com.example.common2.UpdaptePhoneBean;
import com.example.common2.UpdataAddressBean;
import com.shopmall.bawei.shopmall1805.myfragment.contract.AddressContract;

import http.MyHttp;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddressPresenter extends AddressContract.AddressPresenter {
    @Override
    public void addAddress(String address) {
        MyHttp.getShopmallApiService().updateAddress(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdataAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdataAddressBean updataAddressBean) {
                            iHttpView.onAddess(updataAddressBean);
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
    public void addPhone(String phone) {
        MyHttp.getShopmallApiService().updatePhone(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdaptePhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdaptePhoneBean updaptePhoneBean) {
                        iHttpView.onPhone(updaptePhoneBean);
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
