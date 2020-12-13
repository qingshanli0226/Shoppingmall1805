package com.example.user.presenter;

import com.example.net.NetFunction;
import com.example.net.RetorfitCreate;
import com.example.net.ShopMallObserver;
import com.example.net.bean.BaseBean;
import com.example.net.bean.RegisterBean;
import com.example.user.contract.RegisterContract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenterImpl extends RegisterContract.RegisterPresenter {
    @Override
    public void getRegister(String name, String password) {
        HashMap<String,String> map = new HashMap<>();
        map.put("name", name);
        map.put("password", password);
        RetorfitCreate.getiNetworkserviceimpl().registerbean(map)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<RegisterBean>,RegisterBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<RegisterBean>() {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                            iview.onRegister(registerBean);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iview.onError(errorCode,errorMessage);
                    }
                });

    }
}
