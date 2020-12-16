package com.bawei.order.contact;

import com.bawei.common.view.ExceptionUtil;
import com.bawei.net.NetFunction;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.ShopmallObserver;
import com.bawei.net.mode.BaseBean;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BanDingImpl extends BanDingUserContact.IBanDingPresenter {
    @Override
    public void BanDingPhone(String phone) {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        RetrofitCreate.getApi().updatePhone(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new NetFunction<BaseBean<String>, String>())
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iView.onBanDingPhone(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iView.hideLoading(true, ExceptionUtil.getErrorBean(errorCode, errorMessage));
                    }
                });
    }

    @Override
    public void BanDingAddress(String address) {
        HashMap<String, String> map = new HashMap<>();
        map.put("address", address);
        RetrofitCreate.getApi().updateAddress(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new NetFunction<BaseBean<String>, String>())
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iView.onBanDingAddress(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iView.hideLoading(true, ExceptionUtil.getErrorBean(errorCode, errorMessage));
                    }
                });
    }
}
