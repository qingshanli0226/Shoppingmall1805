package com.shopmall.bawei.shopmall1805.app.presenter;

import com.shopmall.bawei.shopmall1805.app.contract.ClassifyLeftContract;
import com.shopmall.bawei.shopmall1805.net.entity.ClothesBean;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ClassifyLeftPresenterImpl extends ClassifyLeftContract.FenleiPresenter {
    @Override
    public void getFenLeiView(String url) {
        RetrofitUtils.getiNetPresetenterWork()
                .skirt(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                    }
                })
                .subscribe(new BaseObserver<ClothesBean>() {
                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        List<ClothesBean.ResultBean> result = clothesBean.getResult();
                        if(result!=null){
                            iHttpView.onFenleiData(result);
                        }
                    }
                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {

                    }
                });
    }
}
