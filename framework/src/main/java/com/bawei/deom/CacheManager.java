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
        for(Shoppingcartproducts.ResultBean shopcarBean:shopcarBeanlist){
            if (shopcarBean.getProductId().equals(productId)){
                shopcarBean.setProductNum(newNum);
                for (IShopcarDataChangeListener listener: iShopcarDataChangeListeners){
                    listener.onOneDataChanger(i,shopcarBean);
                    listener.onMeneyChanged(getMoneyValue());
                }
                break;
            }
            i++;
        }

    }
    //当页面销毁时页面不需要监听数据改变了我们把它从列标中删除
    public  void unSetShopcarDataChangerListener(IShopcarDataChangeListener listener){
        if (iShopcarDataChangeListeners.contains(listener)){
            iShopcarDataChangeListeners.remove(listener);
        }
    }
    public  Shoppingcartproducts.ResultBean getShopcarBan(String prpductId){
        for (Shoppingcartproducts.ResultBean shopcarBean:shopcarBeanlist){
            if (prpductId.equals(shopcarBean.getProductId())){
                return  shopcarBean;
            }
        }
        return  null;
    }

    private boolean isALLSelected() {
          for ( Shoppingcartproducts.ResultBean shopcarBean:shopcarBeanlist){
              if (!shopcarBean.isProductSelected()){
                  return  false;
              }
          }
          return true;
    }
   public  void selectAllProduct(boolean isAllSelected){
        for (Shoppingcartproducts.ResultBean shopcarBean:shopcarBeanlist){
            shopcarBean.setProductSelected(isAllSelected);
        }
        //通知UI更改界面
       //通知UI数据发生了改变需要更细UI
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListeners){
            listener.onDataChanged(shopcarBeanlist);
            listener.onMeneyChanged(getMoneyValue());
            listener.onAllSelected(isAllSelected);
        }
   }
   //把它加入到删除队列
   private void  addDeleteShopcarBean(Shoppingcartproducts.ResultBean shopcarBean,int postion){
        deleteshocarBeanlist.add(shopcarBean);
        boolean isAllselect=deleteshocarBeanlist.size()== shopcarBeanlist.size();//判断当前删除队列数据数目和购物车数目是否一致，一致代表全选删除
       for ( IShopcarDataChangeListener listener:iShopcarDataChangeListeners){
           listener.onOneDataChanger(postion,shopcarBean);
           if (isAllselect){
               listener.onAllSelected(isAllselect);
           }
       }
   }
   public  boolean isALLSelectedInEditMode(){
        return  deleteshocarBeanlist.size()==shopcarBeanlist.size();
   }
   //从删除队列中删除
   public void deleteOneShopcarBean(Shoppingcartproducts.ResultBean shopcarBean,int postion ){
        deleteshocarBeanlist.remove(shopcarBean);
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListeners){
            listener.onOneDataChanger(postion,shopcarBean);
            listener.onAllSelected(false);
        }
   }
   public void selectAllProductInEditMode(boolean isAllselect){
        if (isAllselect){
            deleteshocarBeanlist.clear();
            deleteshocarBeanlist.addAll(shopcarBeanlist);
        }else {
            deleteshocarBeanlist.clear();
        }
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListeners){
            listener.onAllSelected(isAllselect);
            listener.onDataChanged(shopcarBeanlist);
        }
   }
   public  boolean checkIfDataInDeleteShopcarBeanList(Shoppingcartproducts.ResultBean shopcarBean){
        return  deleteshocarBeanlist.contains(shopcarBean);
   }
    public  List<Shoppingcartproducts.ResultBean> getDeleteshocarBeanlist(){
        return  deleteshocarBeanlist;
    }
    public  void processDeleteProducts(){
        //首先将删除列表中的数据在购物车缓存中删除
        shopcarBeanlist.removeAll(deleteshocarBeanlist);
//b1删除列表清空
        deleteshocarBeanlist.clear();
        //通知UI刷新页面
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListeners){
            listener.onDataChanged(shopcarBeanlist);
            listener.onMeneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }
//    public ExecutorService getExecutorService(){
//        return exec
//    }
    //定义接口当购物车数据发生改变是，通过改接口通知页面刷新
    public interface IShopcarDataChangeListener{
         void onDataChanged(List<Shoppingcartproducts.ResultBean> shopcarBeanlist);
         void onOneDataChanger(int pstion,Shoppingcartproducts.ResultBean shopcarBeanlist);
         void  onMeneyChanged(String moneyValue);
         void onAllSelected(boolean isAllSelect);
    }

}
