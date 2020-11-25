package com.shopmall.framework.mvptest.model;

import com.shopmall.framework.callback.Tag;
import com.shopmall.framework.constart.Constart;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.TagBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TagModel implements Constart.TagConstartModel {

    @Override
    public void Tag(String url, final Tag tag) {
        Https getinstance = RetrofitFactory.getInstance().create(Https.class);

        getinstance.gettag(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TagBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TagBean tagBean) {
                        tag.Ok(tagBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        tag.No(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void destroy() {

    }
}
