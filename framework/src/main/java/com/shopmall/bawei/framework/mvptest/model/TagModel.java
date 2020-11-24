package com.shopmall.bawei.framework.mvptest.model;

import com.shopmall.bawei.framework.callback.Tag;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.TagData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TagModel implements Constart.TagConstartModel {
    @Override
    public void Tagdata(String url, final Tag sort) {
        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getTag(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TagData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TagData tagData) {
                          sort.Ok(tagData);
                    }

                    @Override
                    public void onError(Throwable e) {
                         sort.No(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
