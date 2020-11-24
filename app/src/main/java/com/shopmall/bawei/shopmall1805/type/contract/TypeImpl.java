package com.shopmall.bawei.shopmall1805.type.contract;

import android.util.Log;

import com.shopmall.bawei.net.MyNetApi;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.TypeBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TypeImpl extends TypeContract.ITypePresenter {

    @Override
    public MyNetApi type() {
        return OkHttpHelper.getApi();
    }

    public void getSkirt() {
        type().getSkirt()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TypeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TypeBean typeBean) {
                        iView.onType(typeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getJacket(){
        type().getJacket()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}
    public void getPants(){type().getPants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}
    public void getOvercoat(){type().getOvercoat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}
    public void getAccessory(){type().getAccessory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}
    public void getBag(){type().getBag()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}
    public void getDress(){type().getDress()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}
    public void getProduct(){type().getProduct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}
    public void getStationery(){type().getStationery()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}
    public void getDigit(){type().getDigit()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}
    public void getGame(){type().getGame()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TypeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(TypeBean typeBean) {
                    iView.onType(typeBean);
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });}

}
