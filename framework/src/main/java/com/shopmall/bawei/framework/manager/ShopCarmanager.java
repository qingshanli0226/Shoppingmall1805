package com.shopmall.bawei.framework.manager;

import android.util.Log;

import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopCarmanager {

     private volatile static ShopCarmanager shopCarmanager;
     //添加数据的集合
     private List<ShopcarBean.ResultBean> shopcarBeanList=new ArrayList<>();

     //删除数据的集合
    private List<ShopcarBean.ResultBean> deleteshopcarBeanList=new ArrayList<>();

    //购物车选中数据的集合
    private List<ShopcarBean.ResultBean> selectshopcarBeanList=new ArrayList<>();

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

     private ShopCarmanager(){
     }

     //判断勾选结算的数据是否全选
    public boolean isallselect(){
         if (shopcarBeanList.size()==0){
             return false;
         }
        for (ShopcarBean.ResultBean resultBean : shopcarBeanList) {
                 if (!resultBean.isProductSelected()){
                     return false;
                 }
        }
        return true;

    }


   //获取购物车选中的数据
    public List<ShopcarBean.ResultBean> getSelectshopcarBeanList(){
           selectshopcarBeanList.clear();
        for (ShopcarBean.ResultBean resultBean : shopcarBeanList) {
             if (resultBean.isProductSelected()){
                 selectshopcarBeanList.add(resultBean);
             }
        }
        return selectshopcarBeanList;
    }

    //勾选添加删除数据,判断是否存在，不存在添加，存在取消勾选删除
    public void adddeleteshopcarBean(ShopcarBean.ResultBean shopcar,int postion){
                if (!deleteshopcarBeanList.contains(shopcar)){
                    deleteshopcarBeanList.add(shopcar);
                }else {
                    deleteshopcarBeanList.remove(shopcar);
                }
             notifydeleteboolean();
    }

    //判断数据中是否有数据
    public boolean isShopcarBeanList(){
        for (ShopcarBean.ResultBean resultBean : shopcarBeanList) {
             if (resultBean.isProductSelected()){
                 return true;
             }
        }
        return false;
    }


    //切换结算清空删除集合
    public void delteclear(){
         if (deleteshopcarBeanList!=null){
             deleteshopcarBeanList.clear();
         }
         notifydeleteboolean();
    }

    //下订单后，删除缓存选中的数据
    public void deleteselectorder(){
        List<ShopcarBean.ResultBean> selectshopcarBeanList = getSelectshopcarBeanList();
        shopcarBeanList.removeAll(selectshopcarBeanList);
        notifyShopcarDataChanged();
    }


     // 判断删除的数据和总数据是否一致
    public boolean isdelectallselect(){
        if (shopcarBeanList.size()==deleteshopcarBeanList.size()){
            return true;
        }else {
            return false;
        }
    }

    //判断删除集合是否为空
    public boolean isdeleteshopcarlist(){
         if (deleteshopcarBeanList.size()==0){
             return true;
         }else {
             return false;
         }
    }

    //获取删除集合的数据
    public List<ShopcarBean.ResultBean> getDeleteshopcarBeanList(){
             return deleteshopcarBeanList;
    }


    //把选中的数据从购物车删除,并刷新价格选中状态，ui上的数据条目
    public void removeselect(){
          shopcarBeanList.removeAll(deleteshopcarBeanList);
          ShopcarData();
    }


    //点击编辑的全选把值全部存入删除集合
    public void deteallselect(){
         deleteshopcarBeanList.clear();
         deleteshopcarBeanList.addAll(shopcarBeanList);
    }


    //点击编辑的全不选选把值全部清空
    public void clearalldeteshopcar(){
        deleteshopcarBeanList.clear();
    }

    /**
     * 监听数据变化的接口
     */
    public interface IShopcarDataChangeListener{
          void shopcarData(List<ShopcarBean.ResultBean> shopcarBeans);
          void undateshopcar(int positon, ShopcarBean.ResultBean shopcar);
          void getMoney(String money);
          void getallselect(boolean isallselect);
          void getdeleteallselect(boolean isallselect);
    }

    //更改缓存中的购物车产品的数量
    public void updateshopcarnum(ShopcarBean.ResultBean shopcar,int newnum,int positon){
        ShopcarBean.ResultBean resultBean = shopcarBeanList.get(positon);
        if (shopcar!=null){
            resultBean.setProductNum(newnum+"");
        }

        updateshopcarbean(resultBean,positon);

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
     * 获取shopcar数组数据
     */
    public List<ShopcarBean.ResultBean> getShopcarBeanList(){
          if (shopcarBeanList.size()!=0){
              return shopcarBeanList;
          }else {
              return null;
          }
    }


    //更改缓存中的购物车产品的选择状态
    public void updateshopcarselect(ShopcarBean.ResultBean shopcar,int positon){
        ShopcarBean.ResultBean resultBean = shopcarBeanList.get(positon);
        if (shopcar.isProductSelected()){
            resultBean.setProductSelected(false);
        }else {
            resultBean.setProductSelected(true);
        }

        updateshopcarbean(resultBean,positon);

    }

     //通知每个页面，当前位置发生改变，刷新该位置
    public void updateshopcarbean(ShopcarBean.ResultBean shopcar,int postion){
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.undateshopcar(postion,shopcar);
            iShopcarDataChangeListener.getallselect(isallselect());
            iShopcarDataChangeListener.getMoney(getMoney());
        }
    }

    /**
     * 通知刷新数据数据
     */
    public void notifyShopcarDataChanged(){
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
               iShopcarDataChangeListener.shopcarData(shopcarBeanList);
               iShopcarDataChangeListener.getMoney(getMoney());
               iShopcarDataChangeListener.getallselect(isallselect());
        }
    }

   //通知编辑页面删除否全选
    public void notifydeleteboolean(){
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.getdeleteallselect(isdelectallselect());
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
    public String getMoney(){
         float money = 0;
        for (ShopcarBean.ResultBean resultBean : shopcarBeanList) {
                if (resultBean.isProductSelected()){
                    if (resultBean.getProductPrice()!=null){
                        money=money+ Float.parseFloat((String) resultBean.getProductPrice())* Integer.parseInt(resultBean.getProductNum());
                    }
                }
        }

        return String.format("%.2f",money);

    }





    /**
     * 获取购物车信息
     */
    public void ShopcarData(){
        if (shopcarBeanList!=null){
            shopcarBeanList.clear();
        }
        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getShopcar(Constants.GETSHORTCART_PRODUCT)
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
