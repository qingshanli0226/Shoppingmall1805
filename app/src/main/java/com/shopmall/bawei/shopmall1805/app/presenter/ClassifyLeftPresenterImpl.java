package com.shopmall.bawei.shopmall1805.app.presenter;

import com.shopmall.bawei.shopmall1805.app.contract.ClassifyLeftContract;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.framework.ErrorBean;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
                .subscribe(new BaseObserver<ClothesBean>() {
                    @Override
                    public void onNext(ClothesBean clothesBean) {
                        List<ClothesBean.ResultBean> result = clothesBean.getResult();
                        if(result!=null){
                            iHttpView.onFenleiData(result);
                            iHttpView.hideLoading();
                        }
                    }
                });
    }
}
