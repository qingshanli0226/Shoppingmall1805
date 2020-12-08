package com.bw.framework;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bw.framework.service.AutoLoginService;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.Basebean;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.ShopCarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheManager{

    private List<ShopCarBean> shopCarBeans = new ArrayList<>();
    private List<ShopCarBean> deleteShopCarBeans = new ArrayList<>();

    private static CacheManager instance;

    private List<IShopcarDataChangeListener> iShopcarDataChangeListeners = new ArrayList<>();

    private Context context;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private CacheManager() {
    }

    public static CacheManager getInstance(){
        if (instance == null){
            instance = new CacheManager();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
        initService();
    }

    private void initService() {
        Intent intent = new Intent(context, AutoLoginService.class);
        context.startService(intent);

        ShopUserManager.getInstance().registerUserLoginChangedListener(new ShopUserManager.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                getShopDataCarService();
            }

            @Override
            public void onUserLoginOut() {

            }
        });
    }

    private void getShopDataCarService() {
        RetraficCreator.getiNetWorkApiService().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ShopCarBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ShopCarBean> shopCarBeanList) {
                        shopCarBeans.addAll(shopCarBeanList);

                        notifyShopcarDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "请求购物车数据错误"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //提供获取缓存数据的方法
    public List<ShopCarBean> getShopCarBeans(){
        return shopCarBeans;
    }

    public void add(ShopCarBean shopCarBean){
        shopCarBeans.add(shopCarBean);
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListeners) {
            iShopcarDataChangeListener.onDataChanged(shopCarBeans);
        }
    }

    //获取已经选择的商品集合
    public List<ShopCarBean> getSelectedProductBeanList(){

    }

    private void notifyShopcarDataChanged() {
        //遍历这listener列表，去逐个通知页面购物车产品数据变化了
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListeners) {
            listener.onDataChanged(shopCarBeans);
        }
    }



    public interface IShopcarDataChangeListener {
        void onDataChanged(List<ShopCarBean> shopCarBeanList);
        void onOneDataChanged(int position,ShopCarBean shopCarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }
}