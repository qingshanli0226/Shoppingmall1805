package com.bw.user.presenter;




import android.util.Log;

import com.bw.net.NetFunction;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.Basebean;
import com.bw.net.bean.LoginBean;
import com.bw.user.contract.LoginContract;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenterImpl extends LoginContract.LoginPresenter {


    @Override
    public void login(String userName, String password) {
        HashMap<String,String> params = new HashMap<>();
        params.put("name", userName);
        params.put("password", password);

        RetraficCreator.getiNetWorkApiService().login(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<LoginBean>,LoginBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        iView.onLogin(loginBean);
                        iView.hidesLoading(true);
                        Log.e("---","p_token"+loginBean.getToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                        iView.hidesLoading(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
