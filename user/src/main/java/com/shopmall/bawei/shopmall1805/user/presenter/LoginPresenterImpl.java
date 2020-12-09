package com.shopmall.bawei.shopmall1805.user.presenter;

import com.shopmall.bawei.shopmall1805.net.entity.LoginBean;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;
import com.shopmall.bawei.shopmall1805.user.contract.LoginContract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl extends LoginContract.LoginPresenter {

    @Override
    public void getLoginData(HashMap<String, String> map) {
        RetrofitUtils.getiNetPresetenterWork()
                .login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginBean>(){
                    @Override
                    public void onNext(LoginBean loginBean) {
                        if(loginBean!=null){
                            iHttpView.onLoginDate(loginBean);
                     }
                }

         });
    }
}
