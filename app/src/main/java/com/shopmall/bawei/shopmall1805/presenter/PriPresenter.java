package com.shopmall.bawei.shopmall1805.presenter;

import android.util.Log;

import com.example.net.BaseBean;
import com.example.net.ConfigUrl;
import com.example.net.HomeBean;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.shopmall.bawei.shopmall1805.contract.PrimereContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PriPresenter extends PrimereContract.PrimerePresenter {
    @Override
    public void getData() {
        Log.i("wft", "initPresenter: "+"11111");

        new HttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<HomeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<HomeBean> homeBeanBaseBean) {
                        Log.i("wft","123");
                        if (iView!=null){
                            iView.getViewData(homeBeanBaseBean);
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
