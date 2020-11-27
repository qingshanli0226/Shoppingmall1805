package com.shopmall.bawei.shopmall1805.typefragment.presenter;

import com.example.net.Retrofitcreators;
import com.example.net.bean.Biaobean;
import com.shopmall.bawei.shopmall1805.typefragment.contract.LableContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LablePresenter extends LableContract.biaoPresenter {
    @Override
    public void getbiao() {
        Retrofitcreators.getiNetPresetenterWork().tag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsloading();
                    }
                })
                .subscribe(new Observer<Biaobean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Biaobean biaobean) {

                        if (iView!=null) {
                            iView.onbiao(biaobean.getResult());
                            iView.hideloading();
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
