package com.bawei.shopmall.type.contract;

import android.util.Log;

import com.bawei.net.OkHttpHelper;
import com.bawei.net.TagBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TagImpl extends TagContract.ITagPresenter {
    @Override
    public void tag() {
        OkHttpHelper.getApi()
                .getTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TagBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TagBean tagBean) {
                        iView.onTag(tagBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
