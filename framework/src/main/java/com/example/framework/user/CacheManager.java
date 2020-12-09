package com.example.framework.user;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.framework.service.ShopMallService;
import com.example.net.RetrofitCreater;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ShopCarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {
    private List<ShopCarBean.ResultBean> shopCarList=new ArrayList<>();
    private List<ShopCarBean.ResultBean> deleteShopCarList=new ArrayList<>();
    private static CacheManager instance;
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList=new ArrayList<>();
    private Context context;
    private ExecutorService executionException= Executors.newCachedThreadPool();

    private CacheManager() {
    }
    public static CacheManager getInstance(){
        if(instance==null){
            instance=new CacheManager();
        }
        return instance;
    }
    public void init(Context context){
        this.context=context;
        initService();
    }

    private void initService() {
        Intent intent = new Intent(context, ShopMallService.class);
        context.startService(intent);
        UserManager.getInstance().registerUserLoginChangeListener(new UserManager.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean.ResultBean loginBean) {
                getShopcarDataFromServer();
            }

            @Override
            public void onUserLogout() {

            }
        });
    }

    private void getShopcarDataFromServer() {
        RetrofitCreater.getiNetWorkApi().getShopCar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopCarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopCarBean shopCarBean) {
                        List<ShopCarBean.ResultBean> result = shopCarBean.getResult();
                        shopCarList.addAll(result);
                        Log.i("Yoyo", "ok: "+result.size());
                        notifyShopCarDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "购物车请求错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void notifyShopCarDataChanged() {
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopCarList);
        }
    }
    public void add(ShopCarBean.ResultBean resultBean){
        shopCarList.add(resultBean);
        Log.i("Yoyo", "add: "+shopCarList.size());
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopCarList);
        }

    }
    public List<ShopCarBean.ResultBean> getShopCarList(){
        return  shopCarList;
    }
    public void setShopcarDataChangeListener(IShopcarDataChangeListener listener) {
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }
    public interface  IShopcarDataChangeListener{
        void onDataChanged(List<ShopCarBean.ResultBean> shopCarBeanList);
        void onOneDataChanged(int position,ShopCarBean shopCarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }
}
