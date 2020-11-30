package com.bawei.user.contact;

import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.ExceptionUtil;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.mode.LoginBean;
import com.bawei.net.mode.RegisterBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserContractImpl extends UserContract.IUserPresenter {

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
                            iView.hideLoading(false, null);
                            iView.login(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorBean errorBean = ExceptionUtil.getErrorBean(e);
                        iView.hideLoading(true, errorBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void registerUser(String username, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", username);
        map.put("password", password);
        RetrofitCreate.getApi().registerUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (registerBean == null) {
                            iView.showEmpty();
                        } else {
                            iView.hideLoading(false, null);
                            iView.register(registerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorBean errorBean = ExceptionUtil.getErrorBean(e);
                        iView.hideLoading(true, errorBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
