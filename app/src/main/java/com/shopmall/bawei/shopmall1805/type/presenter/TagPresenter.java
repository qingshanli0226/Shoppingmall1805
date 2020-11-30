package com.shopmall.bawei.shopmall1805.type.presenter;

import com.bw.net.RetraficCreator;
import com.bw.net.bean.TagBean;
import com.shopmall.bawei.shopmall1805.type.contract.TagContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TagPresenter extends TagContract.ITagPresenter {
    @Override
    public void getTag() {
        RetraficCreator.getiNetWorkApiService().getTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                })
                .subscribe(new Observer<TagBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TagBean tagBean) {
                        iView.onOk(tagBean);
                        iView.hidesLoading(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                        iView.hidesLoading(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
