package com.shopmall.bawei.user.presenter;

import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.shopmall.bawei.user.contract.LoginContact;
import com.shopmall.bawei.user.contract.RegisterContact;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends RegisterContact.LoginPresenter {
    @Override
    public void getregister(String name, String password) {
        HashMap<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        Retrofitcreators.getiNetPresetenterWork().register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean stringBaseBean) {
                        if (stringBaseBean.getCode().equals("200")){
                            iView.onregister(stringBaseBean);
                        }else {
                            iView.onErroy(stringBaseBean.getMessage());
                        }
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
