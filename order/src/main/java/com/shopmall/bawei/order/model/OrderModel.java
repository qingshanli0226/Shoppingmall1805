package com.shopmall.bawei.order.model;

import android.util.Log;

import com.shopmall.bawei.order.IPassBack;

import net.FoodService;
import net.RxjavaRetortUlis;

import framework.Contact;
import framework.IOrderData;
import framework.ShopUserManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mode.BaseBean;
import view.loadinPage.UnpaidBean;

public
class OrderModel implements Contact.ICenterOrderIModel {

    @Override
    public void goBindingPhone(String Url) {

        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);
        foodService.getUpdatePhone(Url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseBean<String> baseBean) {
                        Log.i("====","电话数据成功请求"+baseBean.getCode()+"返回"+baseBean.getMessage());
                        IPassBack.getInstance().addData(baseBean);
                        IPassBack.getInstance().Updata();
                        ShopUserManager.getInstance().setPhone(baseBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("====","===="+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void goBindingPoint(String Url) {

        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);
        foodService.getUpdateAddress(Url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> baseBean) {
                        Log.i("====","地址数据成功请求"+baseBean.getCode()+"返回"+baseBean.getMessage());
                        IPassBack.getInstance().addData(baseBean);
                        IPassBack.getInstance().Updata();
                        ShopUserManager.getInstance().setAddress(baseBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("====","===="+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void goUnpaidOrder(final IOrderData iOrderData) {
        RxjavaRetortUlis.getInstance()
                .create(FoodService.class)
                .getFindForPay()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UnpaidBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UnpaidBean unpaidBean) {
                        if (unpaidBean!=null){
                            iOrderData.UnpaidBean(unpaidBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iOrderData.Error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
