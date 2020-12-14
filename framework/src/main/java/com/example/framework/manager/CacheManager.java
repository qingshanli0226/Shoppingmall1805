package com.example.framework.manager;

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
    private List<ShopCarBean.ResultBean> shopCarEditList=new ArrayList<>();
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
                shopCarEditList.clear();
                shopCarList.clear();
                UserManager.getInstance().UnRegisterUserLoginChangeListener(this);
            }
        });
    }
    public ShopCarBean.ResultBean getShopcarBean(String productId){
        for (ShopCarBean.ResultBean resultBean : shopCarList) {
            if(productId.equals(resultBean.getProductId())){
                return resultBean;
            }
        }
        return null;
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
                      if(shopCarBean.getCode().equals("200")){
                          List<ShopCarBean.ResultBean> result = shopCarBean.getResult();
                          shopCarList.addAll(result);
                          setEditList(result);
                          Log.i("Yoyo", "ok: "+result.size());
                          notifyShopCarDataChanged();
                      }else {
                          Toast.makeText(context, ""+shopCarBean.getMessage(), Toast.LENGTH_SHORT).show();
                      }
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

    public void setEditList(List<ShopCarBean.ResultBean> result) {
        shopCarEditList.clear();
        for (ShopCarBean.ResultBean resultBean : result) {
            ShopCarBean.ResultBean bean = new ShopCarBean.ResultBean();
            bean.setProductSelected(false);
            bean.setProductNum(resultBean.getProductNum());
            bean.setUrl(resultBean.getUrl());
            bean.setProductPrice(resultBean.getProductPrice());
            bean.setProductId(resultBean.getProductId());
            bean.setProductName(resultBean.getProductName());
            shopCarEditList.add(bean);
        }
    }

    private void notifyShopCarDataChanged() {
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopCarList);
        }
    }
    public void add(ShopCarBean.ResultBean resultBean){
        shopCarList.add(resultBean);
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopCarList);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
            if(isAllSelected()){
                iShopcarDataChangeListener.onAllSelected(true);
            }else {
                iShopcarDataChangeListener.onAllSelected(false);
            }
        }

    }
    public void addEdit(ShopCarBean.ResultBean resultBean){
        ShopCarBean.ResultBean bean = new ShopCarBean.ResultBean();
        bean.setProductSelected(false);
        bean.setProductNum(resultBean.getProductNum());
        bean.setUrl(resultBean.getUrl());
        bean.setProductPrice(resultBean.getProductPrice());
        bean.setProductId(resultBean.getProductId());
        bean.setProductName(resultBean.getProductName());
        shopCarEditList.add(bean);
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopCarList);
        }
    }
    public void upDataProductNum(int position,String num){
        ShopCarBean.ResultBean resultBean = shopCarList.get(position);
        resultBean.setProductNum(num);
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onOneDataChanged(position,resultBean);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
        }
    }
    public void upDataEditProductNum(int position,String num){
        ShopCarBean.ResultBean resultBean = shopCarEditList.get(position);
        resultBean.setProductNum(num);
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onOneDataChanged(position,resultBean);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
        }
    }
    public void selectAllProduct(boolean isAllSelect){
        for (ShopCarBean.ResultBean resultBean : shopCarList) {
            resultBean.setProductSelected(isAllSelect);
        }

        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopCarList);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
            iShopcarDataChangeListener.onAllSelected(isAllSelect);
        }


    }
    public void selectAllEditProduct(boolean isAllSelect){
        for (ShopCarBean.ResultBean resultBean : shopCarEditList) {
            resultBean.setProductSelected(isAllSelect);
        }
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopCarEditList);
        }
    }
    public void deleteManyProduct(List<ShopCarBean.ResultBean> list){
        List<ShopCarBean.ResultBean> deleteList=new ArrayList<>();
        for (ShopCarBean.ResultBean deleteBean : list) {
            for (ShopCarBean.ResultBean resultBean : shopCarList) {
                if(resultBean.getProductId().equals(deleteBean.getProductId())){
                    deleteList.add(resultBean);
                }
            }
        }
        shopCarList.removeAll(deleteList);
        shopCarEditList.removeAll(list);
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopCarList);
            iShopcarDataChangeListener.onAllSelected(isAllEditSelected());
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
        }
    }
    public void updateProductSelected(int position){
        ShopCarBean.ResultBean resultBean = shopCarList.get(position);
        resultBean.setProductSelected(!resultBean.isProductSelected());
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onOneDataChanged(position,resultBean);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
            if(isAllSelected()){
                iShopcarDataChangeListener.onAllSelected(true);
            }else {
                iShopcarDataChangeListener.onAllSelected(false);
            }
        }
    }
    public void updateProductEditSelected(int position){
        ShopCarBean.ResultBean resultBean = shopCarEditList.get(position);
        resultBean.setProductSelected(!resultBean.isProductSelected());
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onOneDataChanged(position,resultBean);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
            if(isAllSelected()){
                iShopcarDataChangeListener.onAllSelected(true);
            }else {
                iShopcarDataChangeListener.onAllSelected(false);
            }
        }
    }

    private boolean isAllSelected() {
        for (ShopCarBean.ResultBean resultBean : shopCarList) {
            if(!resultBean.isProductSelected()){
                return false;
            }
        }
        return true;
    }
    private boolean isAllEditSelected() {
        for (ShopCarBean.ResultBean resultBean : shopCarList) {
            if(!resultBean.isProductSelected()){
                return false;
            }
        }
        return true;
    }

    public String getMoneyValue() {
        float sumPrice=0;
        for (ShopCarBean.ResultBean resultBean : shopCarList) {
            if(resultBean.isProductSelected()){
                float productPrice=Float.parseFloat(resultBean.getProductPrice()+"");
                int productNum=Integer.parseInt(resultBean.getProductNum());
                sumPrice=sumPrice+productPrice*productNum;
            }
        }
        return String.valueOf(sumPrice);
    }
    public List<ShopCarBean.ResultBean> getShopCarList(){
        return  shopCarList;
    }
    public List<ShopCarBean.ResultBean> getShopCarEditList(){
        return  shopCarEditList;
    }
    public void setShopcarDataChangeListener(IShopcarDataChangeListener listener) {
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }
    public void unSetShopcarDataChangeListener(IShopcarDataChangeListener listener) {
        if (iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.remove(listener);
        }
    }
    public interface  IShopcarDataChangeListener{
        void onDataChanged(List<ShopCarBean.ResultBean> shopCarBeanList);
        void onOneDataChanged(int position,ShopCarBean.ResultBean shopCarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }
}
