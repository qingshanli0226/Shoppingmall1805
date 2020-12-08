package com.example.framework.user;

import android.content.Context;
import android.content.Intent;

import com.example.framework.service.ShopMallService;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ShopCarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheManager {
    private List<ShopCarBean> shopCarBeanList=new ArrayList<>();
    private List<ShopCarBean> deleteShopCarBeanList=new ArrayList<>();
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
            public void OnUserLogin(LoginBean loginBean) {
                getShopcarDataFromServer();
            }

            @Override
            public void onUserLogout() {

            }
        });
    }

    private void getShopcarDataFromServer() {

    }

    public interface  IShopcarDataChangeListener{
        void onDataChanger(List<ShopCarBean> shopCarBeanList);
        void onOneDataChanged(int position,ShopCarBean shopCarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }
}
