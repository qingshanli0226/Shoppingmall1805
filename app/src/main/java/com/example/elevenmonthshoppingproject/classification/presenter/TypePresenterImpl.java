package com.example.elevenmonthshoppingproject.classification.presenter;

import com.example.elevenmonthshoppingproject.classification.contract.TypeContract;
import com.example.net.RetorfitCreate;
import com.example.net.ShopMallObserver;
import com.example.net.bean.TypeBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TypePresenterImpl extends TypeContract.TypeIPresenter {
    @Override
    public void getType() {
        RetorfitCreate.getiNetworkserviceimpl().typebean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<TypeBean>() {
                    @Override
                    public void onNext(TypeBean typeBean) {
                        List<TypeBean.ResultBean> result = typeBean.getResult();
                        iview.onType(result);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iview.onError(errorCode,errorMessage);
                    }
                });

    }
}
