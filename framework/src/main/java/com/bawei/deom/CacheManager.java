package com.bawei.deom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bean.Shoppingcartproducts;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.POST;

public class CacheManager {
    //当用户登录成功以后CacheManger会调用服务端接口请求购物车数据拿到购物车数据后给shopcarBeanlist
    private List<Shoppingcartproducts.ResultBean> shopcarBeanlist=new ArrayList<>();
    private  List<Shoppingcartproducts.ResultBean> deleteshocarBeanlist=new ArrayList<>();
    private  static  CacheManager instance;
    //监听多个页面数据的变化所以维护一个监听listener的列表
  private  List<IShopcarDataChangeListener> iShopcarDataChangeListeners=new ArrayList<>();
  private Context context;
//   private ExecutorService executorService= Executors.new

    public CacheManager() {
    }
    public static CacheManager getInstance(){
        if ( instance==null){
            instance=new CacheManager();
        }
        return  instance;
    }
    public  void init(Context context){
        this.context=context;
        initReceiver();
    }
   //注册广播监听当前用户的登录状态
    private void initReceiver() {
        IntentFilter intentFilter=new IntentFilter("/usr/LoginRegisterActivity");
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                 if (intent.getAction().equals("/usr/LoginRegisterActivity")){
                     getShopcarDataFromServer();
                 }
            }
        },intentFilter);
    }

    private void getShopcarDataFromServer() {
        ClassInterface.getUserInterface().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Shoppingcartproducts>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Shoppingcartproducts shoppingcartproducts) {
                        shopcarBeanlist.addAll(shoppingcartproducts.getResult());
                        //通知页面刷一下UI
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
  public  void add(Shoppingcartproducts.ResultBean shopcarBean){//添加
        shopcarBeanlist.add(shopcarBean);//提供接口添加一个商品到购物车的公共数据里面
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListeners){
            listener.onDataChanged(shopcarBeanlist);
        }
  }
    private void notifyShopcarDataChanged() {//刷新
        for (IShopcarDataChangeListener listener: iShopcarDataChangeListeners){
            listener.onDataChanged(shopcarBeanlist);
        }
    }
   public  List<Shoppingcartproducts.ResultBean> getShopcarBeanlist(){
        return shopcarBeanlist;
   }
   //获得已经选择的商品列表
   public  List<Shoppingcartproducts.ResultBean> getSelectedProductBeanList(){
        List<Shoppingcartproducts.ResultBean> selectedList=new ArrayList<>();
        for (Shoppingcartproducts.ResultBean shopcarBean:shopcarBeanlist){
            if (shopcarBean.isProductSelected()){
                selectedList.add(shopcarBean);
            }
        }
        return  selectedList;
   }
   //从缓存中删除已经支付的商品
    public  void removerSelectedProduct(){
        shopcarBeanlist.removeAll(getSelectedProductBeanList());
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListeners){
            listener.onDataChanged(shopcarBeanlist);
            listener.onMeneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }
    public void  setShopcarBeanlist(List<Shoppingcartproducts.ResultBean> shopcarBeanlist){
        this.shopcarBeanlist=shopcarBeanlist;
    }
    //当页面想监听数据的改变就注册一个listener
    public  void  setShopcarDataChangeListerner(IShopcarDataChangeListener listener){
          if (!iShopcarDataChangeListeners.contains(listener)){
              iShopcarDataChangeListeners.add(listener);
          }
    }
    //因为只有两个状态所以改成相反的状态即可
    public void updateProductSelected(int postion){
        Shoppingcartproducts.ResultBean shorcarBean=shopcarBeanlist.get(postion);
        boolean newProducyselected=!shorcarBean.isProductSelected();
        shorcarBean.setProductSelected(newProducyselected);
        //通知UI数据发生改变需要更新UI
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListeners){
            listener.onOneDataChanger(postion,shorcarBean);
            listener.onMeneyChanged(getMoneyValue());
            if (isALLSelected()){
                 listener.onAllSelected(true);
            }else {
                listener.onAllSelected(false);
            }
        }
    }
    private String getMoneyValue() {
        float totalPrice=0;
        for (Shoppingcartproducts.ResultBean shopcarBean:shopcarBeanlist){
            if(shopcarBean.isProductSelected()){
                float productProce=Float.parseFloat(shopcarBean.getProductPrice());
                int producntNum=Integer.parseInt(shopcarBean.getProductNum());
                totalPrice=totalPrice+producntNum*productProce;
            }
        }
        return String.valueOf(totalPrice);
    }
    //更新缓存中商品的数量
    public void updateProductNum(int postion,String newNUm){

        Shoppingcartproducts.ResultBean shopcarBean=shopcarBeanlist.get(postion);
        shopcarBean.setProductNum(newNUm);

         for (IShopcarDataChangeListener listener:iShopcarDataChangeListeners){
             listener.onOneDataChanger(postion,shopcarBean);
             listener.onMeneyChanged(getMoneyValue());
         }

    }
    //更新缓存中商品的数量
    public void updateProductNum(String productId, String newNum) {
        int i = 0;

    }
    private boolean isALLSelected() {
          return true;
    }



    public interface IShopcarDataChangeListener{
         void onDataChanged(List<Shoppingcartproducts.ResultBean> shopcarBeanlist);
         void onOneDataChanger(int pstion,Shoppingcartproducts.ResultBean shopcarBeanlist);
         void  onMeneyChanged(String moneyValue);
         void onAllSelected(boolean isAllSelect);
    }

}
