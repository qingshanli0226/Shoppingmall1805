package com.bw.user.presenter;



import com.bw.net.RetraficCreator;
import com.bw.net.bean.RegisterBean;
import com.bw.user.contract.RegisterContract;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenterImpl extends RegisterContract.ReigsterPresenter {

    @Override
    public void register(String name, String password) {
        HashMap<String,String> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);

        RetraficCreator.getiNetWorkApiService().register(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (registerBean.getCode() .equals("200") ){
                            iView.onRegister(registerBean.getMessage());

                        }else {
                            iView.onError(registerBean.getMessage());

                        }
                        iView.hidesLoading(true);

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
