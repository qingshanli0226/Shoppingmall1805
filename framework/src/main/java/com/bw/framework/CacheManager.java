package com.bw.framework;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.bw.framework.service.AutoLoginService;
import com.bw.net.NetFunction;
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

    private Context context;
    private List<ShopCarBean> shopCarBeans = new ArrayList<>();
    private List<ShopCarBean> deleteShopCarBeans = new ArrayList<>();

    private static CacheManager instance;

    private List<IShopcarDataChangeListener> iShopcarDataChangeListeners = new ArrayList<>();
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

    public void initService() {
        Log.i("---", "initService: 进入：");
        Intent intent = new Intent(context, AutoLoginService.class);
        context.startService(intent);

        ShopUserManager.getInstance().registerUserLoginChangedListener(new ShopUserManager.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                Log.i("---", "onUserLogin: 登录成功"+loginBean.getResult().getToken());
                getShopDataCarService();
            }

            @Override
            public void onUserLoginOut() {

            }
        });
    }

    public void getShopDataCarService() {
        Log.i("---", "getShopDataCarService: ");
        RetraficCreator.getiNetWorkApiService().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<List<ShopCarBean>>,List<ShopCarBean>>())
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

    //修改缓存内数据的方法
    public void add(ShopCarBean shopCarBean){
        shopCarBeans.add(shopCarBean);
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListeners) {
            iShopcarDataChangeListener.onDataChanged(shopCarBeans);
        }
    }

    //获取已经选择的商品集合
    public List<ShopCarBean> getSelectedProductBeanList(){

        List<ShopCarBean> selectProductList = new ArrayList<>();
        for (ShopCarBean shopCarBean : shopCarBeans) {
            if (shopCarBean.isProductSelected()){
                selectProductList.add(shopCarBean);
            }
        }

        return selectProductList;
    }


    //删除缓存已选中商品的方法
    public void removeSelectedProducts(){
        shopCarBeans.removeAll(getSelectedProductBeanList());
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListeners) {
            iShopcarDataChangeListener.onDataChanged(shopCarBeans);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValues());
            iShopcarDataChangeListener.onAllSelected(false);
        }
    }

    //改变商品选中状态
    public void updateProductSelected(int position){

        ShopCarBean shopCarBean = shopCarBeans.get(position);
        boolean newProductSelected = !shopCarBean.isProductSelected();
        shopCarBean.setProductSelected(newProductSelected);

        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListeners) {
            iShopcarDataChangeListener.onOneDataChanged(position,shopCarBean);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValues());
            if (isAllSelected()){
                iShopcarDataChangeListener.onAllSelected(true);
            }else {
                iShopcarDataChangeListener.onAllSelected(false);
            }
        }

    }


    //更新缓存中商品数量
    public void updateProductNum(String productId,String newNum){
        int i = 0;
        for (ShopCarBean shopCarBean : shopCarBeans) {
            if (shopCarBean.getProductId().equals(productId)){
                shopCarBean.setProductNum(newNum);
                //通知UI 刷新数据
                for (IShopcarDataChangeListener listener : iShopcarDataChangeListeners) {
                    listener.onOneDataChanged(i,shopCarBean);
                    listener.onMoneyChanged(getMoneyValues());
                }
                break;
            }
            i++;
        }
    }

    //更新缓存中商品数量
    public void updatePositionProductNum(int productId,String newNum){

        ShopCarBean shopCarBean = shopCarBeans.get(productId);

        shopCarBean.setProductNum(newNum);
        //通知UI 刷新数据
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListeners) {
            listener.onOneDataChanged(productId,shopCarBean);
            listener.onMoneyChanged(getMoneyValues());
        }

    }
    //全选
    public void selectAllProduct(boolean isAllSelect){
        for (ShopCarBean shopCarBean : shopCarBeans) {
            shopCarBean.setProductSelected(isAllSelect);
        }

        //通知页面刷新
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListeners) {
            listener.onMoneyChanged(getMoneyValues());
            listener.onAllSelected(isAllSelect);
            listener.onDataChanged(shopCarBeans);
        }
    }



    //添加到删除的集合中
    public void addDeleteShopCarBean(ShopCarBean shopCarBean,int position){
        deleteShopCarBeans.add(shopCarBean);

        //判断是否是全选删除
        boolean isAllSelect = deleteShopCarBeans.size() == shopCarBeans.size();

        for (IShopcarDataChangeListener listener : iShopcarDataChangeListeners) {
            listener.onOneDataChanged(position,shopCarBean);
            if (isAllSelect){
                listener.onAllSelected(isAllSelect);
            }
        }
    }


    //从删除集合中删除
    public void deleteOneShopCarBean(ShopCarBean shopCarBean,int poistion){
        deleteShopCarBeans.remove(shopCarBean);
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListeners) {
            listener.onOneDataChanged(poistion,shopCarBean);
            listener.onAllSelected(false);
        }
    }

    public boolean isAllSelectInEditMode(){
        return deleteShopCarBeans.size() == shopCarBeans.size();
    }



    public void selectAllProductInEditMode(boolean isAllSelect){
        if (isAllSelect){
            deleteShopCarBeans.clear();
            deleteShopCarBeans.addAll(shopCarBeans);
        }else {
            deleteShopCarBeans.clear();
        }

        for (IShopcarDataChangeListener listener : iShopcarDataChangeListeners) {
            listener.onAllSelected(isAllSelect);
            listener.onDataChanged(shopCarBeans);
        }
    }

    public boolean checkIfDataInDeleteShopcarBeanList(ShopCarBean shopcarBean) {
        return deleteShopCarBeans.contains(shopcarBean);
    }

    public List<ShopCarBean> getDeleteShopcarBeanList() {
        return deleteShopCarBeans;
    }

    public void processDeleteProducts() {
        //首先将删除列表中的数据在购物车缓存张删除
        shopCarBeans.removeAll(deleteShopCarBeans);
        //把删除列表清空
        deleteShopCarBeans.clear();

        //通知UI刷新页面
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListeners) {
            listener.onDataChanged(shopCarBeans);
            listener.onMoneyChanged(getMoneyValues());
            listener.onAllSelected(false);
        }

    }

    public ExecutorService getExecutorService() {
        return executorService;
    }


    //计算钱的方法
    public String getMoneyValues(){
        float totalPrice = 0;
        for (ShopCarBean shopCarBean : shopCarBeans) {
            if (shopCarBean.isProductSelected()){
                float productPrice = Float.parseFloat(shopCarBean.getProductPrice());
                int productNum = Integer.parseInt(shopCarBean.getProductNum());
                totalPrice = productPrice * productNum;
            }
        }
        return String.valueOf(totalPrice);
    }

    public void setShopCarBeans(List<ShopCarBean> shopCarBeans) {
        this.shopCarBeans = shopCarBeans;
    }


    //当页面想监听数据改变时 注册一个监听
    public void setShopCarDataChangerListener(IShopcarDataChangeListener listener){
        if (! iShopcarDataChangeListeners.contains(listener)){
            iShopcarDataChangeListeners.add(listener);
        }
    }

    //当页面销毁时 不需要更改数据 删除监听
    public void unSetShopCarDataChangerListener(IShopcarDataChangeListener listener){
        if (iShopcarDataChangeListeners.contains(listener)){
            iShopcarDataChangeListeners.remove(listener);
        }
    }


    //从缓存中通过Id 获取商品
    public ShopCarBean getShopcarBan(String productId) {
        for(ShopCarBean shopcarBean:shopCarBeans) {
            if (productId.equals(shopcarBean.getProductId())) {
                return shopcarBean;
            }
        }
        return null;
    }




    //获取商品选中状态
    public boolean isAllSelected(){
        for (ShopCarBean shopCarBean : shopCarBeans) {
            if (!shopCarBean.isProductSelected()){
                return false;
            }
        }
        return true;
    }

    //通知刷新页面
    private void notifyShopcarDataChanged() {
        //遍历这listener列表，去逐个通知页面购物车产品数据变化了
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListeners) {
            listener.onDataChanged(shopCarBeans);
        }
    }



    //
    public interface IShopcarDataChangeListener {
        void onDataChanged(List<ShopCarBean> shopCarBeanList);
        void onOneDataChanged(int position,ShopCarBean shopCarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }
}