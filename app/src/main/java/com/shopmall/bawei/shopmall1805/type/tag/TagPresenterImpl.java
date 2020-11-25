package com.shopmall.bawei.shopmall1805.type.tag;

import com.example.net.RetrofitCreater;
import com.example.net.bean.TagBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TagPresenterImpl extends TagContract.TagPresenter {
    @Override
    void showTag() {
        RetrofitCreater.getiNetWorkApi()
                .showTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TagBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TagBean bean) {
                        iview.onOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
