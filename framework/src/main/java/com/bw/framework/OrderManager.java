package com.bw.framework;

import android.content.Context;
import android.util.Log;

import com.bw.net.ForPayBean;
import com.bw.net.ForSendBean;
import com.bw.net.NetFunction;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.Basebean;
import com.bw.net.bean.LoginBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OrderManager {

    private static OrderManager instance;

    private List<ForPayBean> forPayBeanList = new ArrayList<>();
    private List<ForSendBean> forSendBeanList = new ArrayList<>();
    private List<IOrderChangeListener> listeners = new ArrayList<>();
    private Context context;

    private OrderManager() {
    }

    public static OrderManager getInstance() {
        if (instance == null){
            instance = new OrderManager();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;

        ShopUserManager.getInstance().registerUserLoginChangedListener(new ShopUserManager.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                getForPayData();
            }

            @Override
            public void onUserLoginOut() {

            }
        });



    }

    private void getForPayData() {
        RetraficCreator.getiNetWorkApiService().findForPay()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<List<ForPayBean>>,List<ForPayBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ForPayBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ForPayBean> forPayBeans) {
                        Log.i("---", "onNext: forPayListSize："+forPayBeans.size());
                        forPayBeanList.clear();
                        forPayBeanList.addAll(forPayBeans);
                        getForSendData();
                        notifyOrderDataChange();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getForSendData() {
        RetraficCreator.getiNetWorkApiService().findForSend()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<List<ForSendBean>>,List<ForSendBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ForSendBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ForSendBean> forSendBeans) {
                        Log.i("---", "onNext: forsendListSize："+forSendBeans.size());
                        forSendBeanList.clear();
                        forSendBeanList.addAll(forSendBeans);
                        notifyOrderDataChange();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addForPayBean(ForPayBean forPayBean){
        forPayBeanList.add(forPayBean);
        for (IOrderChangeListener listener : listeners) {
            listener.onForPayChange(forPayBeanList);
        }
    }

    public void addForSendBean(ForSendBean forSendBean){
        forSendBeanList.add(forSendBean);
        for (IOrderChangeListener listener : listeners) {
            listener.onForSendChange(forSendBeanList);
        }
    }


    public List<ForSendBean> getForSendBeanList() {
       return forSendBeanList;
    }

    public List<ForPayBean> getForPayBeanList() {
        return forPayBeanList;
    }

    public void setOrderChangeListeners(IOrderChangeListener iOrderChangeListener) {
        if (!listeners.contains(iOrderChangeListener)){
            listeners.add(iOrderChangeListener);
        }
    }


    public void unSetOrderChangeListeners(IOrderChangeListener iOrderChangeListener) {
        if (listeners.contains(iOrderChangeListener)){
            listeners.remove(iOrderChangeListener);
        }
    }

    public void notifyOrderDataChange(){
        for (IOrderChangeListener listener : listeners) {
            listener.onForPayChange(forPayBeanList);
            listener.onForSendChange(forSendBeanList);
        }
    }


    public interface IOrderChangeListener{
        void onForPayChange(List<ForPayBean> forPayBeanList);
        void onForSendChange(List<ForSendBean> forSendBeanList);
    }

}
