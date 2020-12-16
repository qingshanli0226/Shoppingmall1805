package com.example.elevenmonthshoppingproject.address.presenter;

import android.util.Log;

import com.example.elevenmonthshoppingproject.address.contract.AddressContract;
import com.example.net.NetFunction;
import com.example.net.RetorfitCreate;
import com.example.net.ShopMallObserver;
import com.example.net.bean.BaseBean;
import com.example.net.bean.MessageBean;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddressPresenterImpl extends AddressContract.AddressPresenter {
    @Override
    public void getAddress(String AddressName) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("address",AddressName);
        RetorfitCreate.getiNetworkserviceimpl().getMessage(hashMap)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<MessageBean>,MessageBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<MessageBean>() {
                    @Override
                    public void onNext(MessageBean messageBean) {
                        Log.i("---","123");
                            if (iview!=null) {
                                iview.onAddress(messageBean);
                            }

                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iview.onError(errorCode,errorMessage);
                    }
                });
    }
}
