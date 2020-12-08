package com.shopmall.framework.mvptest.model;

import com.shopmall.framework.callback.Tag;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.view.LoadingPage;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.TagBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TagModel implements Constart.TagConstartModel {

    @Override
    public void Tag(String url, final LoadingPage loadingPage, final Tag tag) {
        Https getinstance = RetrofitFactory.getInstance().create(Https.class);

        getinstance.getTag(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        loadingPage.showLoadingPage();
                    }
                })
                .subscribe(new Observer<TagBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TagBean tagBean) {
                        loadingPage.showSuccessView();
                        tag.Ok(tagBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingPage.showSuccessView();
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
