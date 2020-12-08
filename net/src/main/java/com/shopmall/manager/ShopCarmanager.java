package com.shopmall.manager;

import android.util.Log;

import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopCarmanager{

     private volatile static ShopCarmanager shopCarmanager;
     //添加数据的集合
     private List<ShopcarBean.ResultBean> shopcarBeanList=new ArrayList<>();

     //添加监听的集合，用来通知数据的变化
     private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList=new ArrayList<>();

     public static ShopCarmanager getShopCarmanager(){
          if (null==shopCarmanager){
              synchronized (ShopCarmanager.class){
                  if (shopCarmanager==null){
                      shopCarmanager=new ShopCarmanager();
                  }
              }
          }
          return shopCarmanager;
     }


    /**
     * 监听数据变化的接口
     */
    public interface IShopcarDataChangeListener{
          void shopcarData(List<ShopcarBean.ResultBean> shopcarBeans);
          void undateshopcar(int positon, ShopcarBean.ResultBean shopcar);
          void getMoney(String money);

    }

    /**
     * 需要监听改变数据
     */
    public void registiShopcarDataChangeListener(IShopcarDataChangeListener iShopcarDataChangeListener){
              if (!iShopcarDataChangeListenerList.contains(iShopcarDataChangeListener)){
                   iShopcarDataChangeListenerList.add(iShopcarDataChangeListener);
              }
    }


    /**
     * 通知刷新数据数据
     */
    public void notifyShopcarDataChanged(){
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
               iShopcarDataChangeListener.shopcarData(shopcarBeanList);
        }
    }


    /**
     * 解除监听
     */
    public void uniShopcarDataChangeListener(IShopcarDataChangeListener iShopcarDataChangeListener){
        if (iShopcarDataChangeListenerList.contains(iShopcarDataChangeListener)){
            iShopcarDataChangeListenerList.remove(iShopcarDataChangeListener);
        }
    }


    /**
     * 结算总钱数
     */
    private String getMoney(){
         float money = 0;
        for (ShopcarBean.ResultBean resultBean : shopcarBeanList) {
                if (resultBean.isProductSelected()){
                   money=money+ Float.parseFloat((String) resultBean.getProductPrice())* Integer.parseInt(resultBean.getProductNum());
                }
        }

        return String.valueOf(money);
    }


    /**
     * 获取购物车信息
     */
    public void ShopcarData(){
        if (shopcarBeanList!=null){
            shopcarBeanList.clear();
        }
        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getShopcar("getShortcartProducts")
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<ShopcarBean>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(ShopcarBean shopcarBean) {
                         Log.e("shopcar",""+shopcarBean);
                         shopcarBeanList.addAll(shopcarBean.getResult());
                         //刷新通知页面
                         notifyShopcarDataChanged();
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
