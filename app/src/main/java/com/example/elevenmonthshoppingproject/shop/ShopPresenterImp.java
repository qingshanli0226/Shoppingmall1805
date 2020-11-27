package com.example.elevenmonthshoppingproject.shop;

import com.example.net.NetFunction;
import com.example.net.RetorfitCreate;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.Recommonde;
import com.example.net.bean.RegisterBean;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopPresenterImp extends  ShopIView.IShopPresenter {

    private ShopIView.IShopView shopView;

    public void  attatch(ShopIView.IShopView shopView) {
        this.shopView = shopView;
    }

    @Override
    public void getshop() {
        RetorfitCreate.getiNetworkserviceimpl().recommondebean()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<Recommonde>,Recommonde>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recommonde>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Recommonde recommonde) {
                            shopView.onShopview(recommonde);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onregister(String username, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        RetorfitCreate.getiNetworkserviceimpl().registerbean(map)
                .delay(5,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (shopView!=null){
                            if (registerBean.getCode()=="200"){
                                shopView.onregister(registerBean);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onlogin(String username, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        RetorfitCreate.getiNetworkserviceimpl().loginbean(map)
                .delay(5,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                                shopView.onlogin(loginBean);
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
