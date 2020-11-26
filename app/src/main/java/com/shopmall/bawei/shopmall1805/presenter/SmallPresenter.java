package com.shopmall.bawei.shopmall1805.presenter;

import android.util.Log;

import com.example.net.ClothesBean;
import com.example.net.ConfigUrl;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.shopmall.bawei.shopmall1805.contract.SmallskirtContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SmallPresenter extends SmallskirtContract.SmallskirtPresenter {
    @Override
    public void getData2() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .skirt()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        if (iView!=null){
                            iView.getViewData2(clothesBean.getResult());
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

    @Override
    public void getData1() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .accessory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData3() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .bag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData4() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .digit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData5() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .dressup()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        if (iView!=null){
                            iView.getViewData2(clothesBean.getResult());
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

    @Override
    public void getData6() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .game()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData7() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .home_products()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData8() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .hot_post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData9() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .jacket()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData10() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .new_post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData11() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .overcoat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData12() {
        new HttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .tag1()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        iView.getViewData2(clothesBean.getResult());
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
