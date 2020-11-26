package com.shopmall.bawei.shopmall1805.type.presenter;

import com.bw.net.RetraficCreator;
import com.bw.net.bean.TagBean;
import com.shopmall.bawei.shopmall1805.type.contract.TagContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TagPresenter extends TagContract.ITagPresenter {
    @Override
    public void getTag() {
        RetraficCreator.getiNetWorkApiService().getTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TagBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TagBean tagBean) {
                        iView.onOk(tagBean);
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
