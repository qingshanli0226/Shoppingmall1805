package com.shopmall.bawei.framework;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.framework.service.ShopmallService;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.ShopMallObserver;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.net.mode.ShopCarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {

    //当用户登录成功后，CacheManger会调用服务端接口请求购物车数据。拿到购物车数据后，给shopcarBeanList赋值
    private List<ShopCarBean> shopCarBeanList = new ArrayList<>();
    private List<ShopCarBean> deleteShopCarBeanList = new ArrayList<>();

    private static CacheManager instance;
    //有多个页面监听数据的变化，所以维护一个监听listener的列表
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList = new ArrayList<>();

    private Context context;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private CacheManager(){
    }

    public static CacheManager getInstance(){
        if(instance == null){
            instance = new CacheManager();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
        initService();
    }
    //初始化service,完成自动登录功能
    private void initService() {
        Intent intent = new Intent(context, ShopmallService.class);
        context.startService(intent);
        //缓存第2步:通过回调监听登录事件，一旦监听到登录成功，立马从服务端获取购物车数据，并且将数据塞到列表的缓存中,初始化缓存
        UserManager.getInstance().registerUserLoginChangeListener(new UserManager.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                getShopCarDataFromServer();
            }

            @Override
            public void onUserLogout() {

            }
        });
    }
    //从服务端获取购物车的数据
    private void getShopCarDataFromServer() {
        OkHttpHelper.getApi().getShortCartProducts()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<ShopCarBean>>,List<ShopCarBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ShopCarBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ShopCarBean> shopCarBeans) {
                        shopCarBeanList.addAll(shopCarBeans);
                        notifyShopCarDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, ExceptionUtil.getErrorBean(e).getErrorMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void notifyShopCarDataChanged() {
        //遍历这listener列表，去逐个通知页面购物车产品数据变化了
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList){
            listener.onDataChanged(shopCarBeanList);
        }
    }
    //缓存第三步：提供修改缓存数据的接口
    public void add(ShopCarBean shopCarBean){
        shopCarBeanList.add(shopCarBean);
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList){
            listener.onDataChanged(shopCarBeanList);
        }
    }

    //缓存第三步：提供方法让别的类可以获取缓存
    public List<ShopCarBean> getShopCarBeanList() {
        return shopCarBeanList;
    }

    //获取已经选择的商品列表
    public List<ShopCarBean> getSelectedProductBeanList(){
        List<ShopCarBean> selectedList = new ArrayList<>();
        for (ShopCarBean shopCarBean : shopCarBeanList){
            if(shopCarBean.isProductSelected()){
                selectedList.add(shopCarBean);
            }
        }
        return selectedList;
    }
    //从缓存中删除已经支付的商品
    public void removeSelectedProducts(){
        shopCarBeanList.removeAll(getSelectedProductBeanList());
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList){
            listener.onDataChanged(shopCarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }

    public void setShopCarBeanList(List<ShopCarBean> shopCarBeanList) {
        this.shopCarBeanList = shopCarBeanList;
    }
    //当页面想监听数据的改变，就注册一个listener
    public void setShopCarDataChangeListener(IShopcarDataChangeListener listener){
        if(!iShopcarDataChangeListenerList.contains(listener)){
            iShopcarDataChangeListenerList.add(listener);
        }
    }
    //因为只有两个状态，所以改成相反的状态即可
    public void updateProductSelected(int position){
        ShopCarBean shopCarBean = shopCarBeanList.get(position);
        boolean newProductSelected = !shopCarBean.isProductSelected();
        shopCarBean.setProductSelected(newProductSelected);
        //通知UI，数据发生了改变，需要更新UI
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListenerList){
            listener.onOneDataChanged(position,shopCarBean);
            listener.onMoneyChanged(getMoneyValue());
            if(isAllSelected()){
                listener.onAllSelected(true);
            }else{
                listener.onAllSelected(false);
            }
        }
    }

    private boolean isAllSelected() {
        for (ShopCarBean shopCarBean:shopCarBeanList){
            if(!shopCarBean.isProductSelected()){
                return false;
            }
        }
        return true;
    }
    //更新缓存中商品的数量
    public void updateProductNum(int position,String newNum){
        ShopCarBean shopCarBean = shopCarBeanList.get(position);
        shopCarBean.setProductNum(newNum);
        //通知UI，数据发生了改变，需要更新UI
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListenerList){
            listener.onOneDataChanged(position,shopCarBean);
            listener.onMoneyChanged(getMoneyValue());
        }
    }

    //更新缓存中商品的数量
    public void updateProductNum(String productId,String newNum){
        int i = 0;
        for (ShopCarBean shopCarBean:shopCarBeanList){
            if(shopCarBean.getProductId().equals(productId)) {
                shopCarBean.setProductNum(newNum);
                for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
                    listener.onOneDataChanged(i, shopCarBean);
                    listener.onMoneyChanged(getMoneyValue());
                }
                break;
            }
            i++;
        }
    }
    //当页面销毁时，页面不需要再监听数据改变了，我们把它从列表中删除
    public void unSetShopCarDataChangeListener(IShopcarDataChangeListener listener){
        if(iShopcarDataChangeListenerList.contains(listener)){
            iShopcarDataChangeListenerList.remove(listener);
        }
    }

    public ShopCarBean getShopCarBan(String productId) {
        for(ShopCarBean shopcarBean:shopCarBeanList) {
            if (productId.equals(shopcarBean.getProductId())) {
                return shopcarBean;
            }
        }
        return null;
    }

    public void selectAllProduct(boolean isAllSelect){
        for (ShopCarBean shopCarBean:shopCarBeanList){
            shopCarBean.setProductSelected(isAllSelect);
        }

        //通知UI更新页面
        //通知UI，数据发生了改变，需要更新UI
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListenerList){
            listener.onDataChanged(shopCarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(isAllSelect);
        }
    }

    //把它加入到删除队列中
    public void addDeleteShopcarBean(ShopCarBean shopcarBean, int position) {
        deleteShopCarBeanList.add(shopcarBean);
        boolean isAllSelect = deleteShopCarBeanList.size() == shopCarBeanList.size();//判断下当前删除队列数据数目和购物车商品数目是否一致，一致代表是全选删除
        //需要更新UI
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            if(isAllSelect) {
                listener.onAllSelected(isAllSelect);
            }
        }
    }

    public boolean isAllSelectInEditMode() {
        return deleteShopCarBeanList.size() == shopCarBeanList.size();
    }

    //从删除队列中删除
    public void deleteOneShopCarBean(ShopCarBean shopcarBean, int position) {
        deleteShopCarBeanList.remove(shopcarBean);
        //需要更新UI
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onAllSelected(false);
        }
    }

    public void selectAllProductInEditMode(boolean isAllSelect) {
        if (isAllSelect) {
            deleteShopCarBeanList.clear();
            deleteShopCarBeanList.addAll(shopCarBeanList);
        } else {
            deleteShopCarBeanList.clear();
        }

        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onAllSelected(isAllSelect);
            listener.onDataChanged(shopCarBeanList);
        }
    }

    public boolean checkIfDataInDeleteShopcarBeanList(short shopcarBean) {
        return deleteShopCarBeanList.contains(shopcarBean);
    }

    public List<ShopCarBean> getDeleteShopcarBeanList() {
        return deleteShopCarBeanList;
    }

    public void processDeleteProducts() {
        //首先将删除列表中的数据在购物车缓存张删除
        shopCarBeanList.removeAll(deleteShopCarBeanList);
        //把删除列表清空
        deleteShopCarBeanList.clear();

        //通知UI刷新页面
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopCarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }

    }

    public ExecutorService getExecutorService() {
        return executorService;
    }




    private String getMoneyValue() {
        float totalPrice = 0;
        for (ShopCarBean shopCarBean:shopCarBeanList){
            float productPrice = Float.parseFloat(shopCarBean.getProductPrice());
            int productNum = Integer.parseInt(shopCarBean.getProductNum());
            totalPrice = totalPrice + productPrice*productNum;
        }
        return String.valueOf(totalPrice);
    }

    //定义接口，当购物车数据发生改变时，通过该接口通知页面刷新
    public interface IShopcarDataChangeListener {
        void onDataChanged(List<ShopCarBean> shopcarBeanList);
        void onOneDataChanged(int position, ShopCarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }

}
