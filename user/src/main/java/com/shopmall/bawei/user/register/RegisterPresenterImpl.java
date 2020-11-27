package com.shopmall.bawei.user.register;

import com.example.net.RetrofitCreater;
import com.example.net.bean.RegisterBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenterImpl extends RegisterContract.RegisterPresenter {
    @Override
    public void register(String name, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        RetrofitCreater.getiNetWorkApi().register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean bean) {
                        iview.onOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
