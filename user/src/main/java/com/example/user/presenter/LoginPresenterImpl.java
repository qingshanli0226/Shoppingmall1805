package com.example.user.presenter;

import com.example.net.NetFunction;
import com.example.net.RetorfitCreate;
import com.example.net.ShopMallObserver;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.example.user.contract.LoginContract;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl extends LoginContract.LoginPresenter{
    @Override
    public void login(String name, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        RetorfitCreate.getiNetworkserviceimpl().loginbean(map)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<LoginBean>,LoginBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        iview.onLogin(loginBean);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                            iview.onError(errorCode,errorMessage);
                    }
                });


    }
}
