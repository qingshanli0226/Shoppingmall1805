package com.bawei.user.contact;

import com.bawei.common.view.ErrorBean;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.mode.LoginBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserContractImpl extends UserContract.UserPresenter {

    @Override
    public void loginUser(String username, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", username);
        map.put("password", password);
        RetrofitCreate.getApi().loginUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean == null) {
                            iView.showEmpty();
                        } else {
                            iView.hideLoading(true, null);
                            iView.login(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorBean err = new ErrorBean();
                        err.setErrorCode("100");
                        err.setErrorMessage(e.getMessage());
                        iView.hideLoading(false, err);
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void registerUser(String username, String password) {

    }
}
