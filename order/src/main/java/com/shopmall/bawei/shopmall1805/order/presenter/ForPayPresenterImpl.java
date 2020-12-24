package com.shopmall.bawei.shopmall1805.order.presenter;

import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;
import com.shopmall.bawei.shopmall1805.net.entity.FindForBean;
import com.shopmall.bawei.shopmall1805.order.contract.ForPayContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForPayPresenterImpl extends ForPayContract.ForPayPresenter {

    @Override
    public void getFindFor() {
        RetrofitUtils.getiNetPresetenterWork().getFindForPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<FindForBean>() {
                    @Override
                    public void onNext(FindForBean findForBean) {
                        List<FindForBean.ResultBean> result = findForBean.getResult();
                        if(result!=null){
                            iHttpView.onFindFor(result);
                        }
                    }
                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {

                    }
                });
    }
}
