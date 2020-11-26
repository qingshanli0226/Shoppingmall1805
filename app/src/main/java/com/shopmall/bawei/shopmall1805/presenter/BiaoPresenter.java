package com.shopmall.bawei.shopmall1805.presenter;

import com.example.net.Confing;
import com.example.net.Retrofitcreators;
import com.example.net.bean.Biaobean;
import com.shopmall.bawei.shopmall1805.contract.BiaoContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BiaoPresenter extends BiaoContract.biaoPresenter {
    @Override
    public void getbiao() {
        Retrofitcreators.getiNetPresetenterWork().tag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Biaobean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Biaobean biaobean) {
                        if (iView!=null) {
                            iView.onbiao(biaobean.getResult());
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
