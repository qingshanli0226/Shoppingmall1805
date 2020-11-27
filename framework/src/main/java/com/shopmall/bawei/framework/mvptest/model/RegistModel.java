package com.shopmall.bawei.framework.mvptest.model;

import com.shopmall.bawei.framework.callback.IRegist;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.Registbean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistModel implements Constart.RegistConstartModel {
    @Override
    public void regist(String url, HashMap<String, String> map, final IRegist iRegist) {
        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getregist(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                           iRegist.Ok(registbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                         iRegist.No(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
