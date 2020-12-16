package com.shopmall.bawei.shopmall1805.framework.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.shopmall.bawei.framework.R;
import com.shopmall.bawei.shopmall1805.framework.ShopUserManager;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;
import com.shopmall.bawei.shopmall1805.net.entity.BaseBean;
import com.shopmall.bawei.shopmall1805.net.entity.LoginBean;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {

    private List<ShopcarBean> shopcarBeanList = new ArrayList<>();//缓存第一步:,定义单例，并且在单例中使用列表来存储缓存数据
    private List<ShopcarBean> deleteShopcarBeanList = new ArrayList<>();//
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList = new ArrayList<>();
    private Context context;
    public CacheManager() {}
    private static CacheManager instance;
    public static CacheManager getInstance() {
        if(instance == null){
            instance = new CacheManager();
        }
        return instance;
    }
    public void init(Context context) {
        this.context = context;
        initService();
    }
    private void initService() {
        Intent intent = new Intent(context,ShopmallService.class);
        context.startService(intent);//通过start启动service
        ShopUserManager.getInstance().registerUserLoginChangeListener(new ShopUserManager.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean.ResultBean loginBean) {//监听自动登录,成功直接拿数据
                getShopcarDataFromServer();//请求服务器函数
            }
            @Override
            public void oUserLogout() {

            }
        });
    }

    public void processDeleteProducts() {
        //首先将删除列表中的数据在购物车缓存张删除
        shopcarBeanList.removeAll(deleteShopcarBeanList);
        //把删除列表清空
        deleteShopcarBeanList.clear();

        //通知UI刷新页面
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }

    }
    private void getShopcarDataFromServer(){
        RetrofitUtils.getiNetPresetenterWork().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<List<ShopcarBean>>>(){
                    @Override
                    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {
                        Log.i("HC", "onNext: "+listBaseBean);
                        List<ShopcarBean> result = listBaseBean.getResult();
                        shopcarBeanList.addAll(result);
                        notifyShopcarDataChanged();//去通知
                    }
                });
    }

    //更新缓存中商品的数量
    public void updateProductNum(int position, String newNum) {
        ShopcarBean shopcarBean = shopcarBeanList.get(position);
        shopcarBean.setProductNum(newNum);

        //通知UI，数据发生了改变，需要更新UI
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
        }
    }

    public boolean isAllSelectInEditMode() {
        return deleteShopcarBeanList.size() == shopcarBeanList.size();
    }
    public void selectCompileAll(boolean isSelect){
        if(isSelect){
            deleteShopcarBeanList.clear();
            deleteShopcarBeanList.addAll(shopcarBeanList);
        }else {
            deleteShopcarBeanList.clear();
        }

        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onAllSelected(isSelect);
            listener.onDataChanged(shopcarBeanList);
        }
    }
    public void updateProductSelected(int position) {
        ShopcarBean shopcarBean = shopcarBeanList.get(position);
        boolean newProductselected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductselected);

        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
            if (isAccomplish()) {
                listener.onAllSelected(true);
            } else {
                listener.onAllSelected(false);
            }
        }
    }

    public List<ShopcarBean> getDeleteShopcarBeanList() {
        return deleteShopcarBeanList;
    }

    public void selectAllProduct(boolean isAllSelect) {
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            shopcarBean.setProductSelected(isAllSelect);
        }

        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(isAllSelect);
        }

    }


    public boolean isAccomplish(){
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if(!shopcarBean.isProductSelected()){
                return false;
            }
        }
        return true;
    }
    public void add(ShopcarBean shopcarBean){
        shopcarBeanList.add(shopcarBean);
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }
    public void setShopcarDataChanger(IShopcarDataChangeListener listener){
        if(!iShopcarDataChangeListenerList.contains(listener)){
            iShopcarDataChangeListenerList.add(listener);
        }
    }
    public void notifyShopcarDataChanged() {//遍历listen通知所有界面数据变化
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }
    public ShopcarBean geShopcarBean(String id){
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if(id.equals(shopcarBean.getProductId())){
                return shopcarBean;
            }
        }
        return null;
    }
    public void deleteShopcarQueue(ShopcarBean shopcarBean,int position){
        deleteShopcarBeanList.remove(shopcarBean);
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onAllSelected(false);
        }
    }
    public void addDeleteQueue(ShopcarBean shopcarBean,int position){
        deleteShopcarBeanList.add(shopcarBean);
        boolean isAllSelect =  deleteShopcarBeanList.size() == shopcarBeanList.size();
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position,shopcarBean);
            if(isAllSelect){
                listener.onAllSelected(isAllSelect);
            }
        }
    }
    public String getMoneyValue() {
        float totalPrice = 0;
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                float productPrice = Float.parseFloat(shopcarBean.getProductPrice());
                int productNum = Integer.parseInt(shopcarBean.getProductNum());
                totalPrice = totalPrice + productPrice*productNum;
            }
        }
        return String.valueOf(totalPrice);
    }

    public boolean checkIfDataInDeleteShopcarBeanList(ShopcarBean shopcarBean){
        return shopcarBeanList.contains(shopcarBean);
    }
    public List<ShopcarBean> getShopcarBeanList(){
        return shopcarBeanList;
    }
    public interface IShopcarDataChangeListener{
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position, ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }
}

