package com.shopmall.bawei.shopmall1805.type.presenter;

import com.bw.net.RetraficCreator;

import com.bw.net.bean.SkirtBean;
import com.shopmall.bawei.shopmall1805.type.contract.TypeContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TypePresenter extends TypeContract.ITypePresenter {
    @Override
    public void getSkirt() {
        RetraficCreator.getiNetWorkApiService().getSkirt()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean skirtBean) {
                            iView.onGetSkirtOk(skirtBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getJacket() {
        RetraficCreator.getiNetWorkApiService().getJacket()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean jacketBean) {
                        if (jacketBean.getCode() == 200){
                            iView.onGetSkirtOk(jacketBean);
                        }else {
                            iView.onError(jacketBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getPints() {
        RetraficCreator.getiNetWorkApiService().getPants()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean pantsBean) {
                        iView.onGetSkirtOk(pantsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getOvercoad() {
        RetraficCreator.getiNetWorkApiService().getOverCoat()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean overCoatBean) {
                        iView.onGetSkirtOk(overCoatBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getAccessory() {
        RetraficCreator.getiNetWorkApiService().getAccessory()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean accessoryBean) {
                        iView.onGetSkirtOk(accessoryBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getBag() {
        RetraficCreator.getiNetWorkApiService().getBag()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean bagBean) {

                        iView.onGetSkirtOk(bagBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getDress() {

        RetraficCreator.getiNetWorkApiService().getDress()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean dressBean) {
                        iView.onGetSkirtOk(dressBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHomeproduct() {

        RetraficCreator.getiNetWorkApiService().getHomeProducts()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean homeProductsBean) {

                        iView.onGetSkirtOk(homeProductsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getStation() {

        RetraficCreator.getiNetWorkApiService().getStationery()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean stationeryBean) {
                        iView.onGetSkirtOk(stationeryBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getDigit() {

        RetraficCreator.getiNetWorkApiService().getDigit()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean digitBean) {
                        iView.onGetSkirtOk(digitBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getGame() {
        RetraficCreator.getiNetWorkApiService().getGame()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkirtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SkirtBean gameBean) {
                        iView.onGetSkirtOk(gameBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
