package com.bawei.deom.selectedordelete;

import com.bawei.deom.ClassInterface;

import bean.FindForSendBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FindForSendImpl extends FindForContract.FindForShow {
    @Override
    public void findforshow() {
        ClassInterface.getUserInterface().findForSend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindForSendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindForSendBean findForSendBean) {
                          pView.onFindForView(findForSendBean.getResult());
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
