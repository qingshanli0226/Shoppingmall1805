package com.example.framwork;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.framwork.service.LoginService;
import com.example.net.NetFunction;
import com.example.net.RetorfitCreate;
import com.example.net.ShopMallObserver;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

//存储购物车
public class CacheManager {
    private List<ShopcarBean> shopcarBeanList=new ArrayList<>();
    private List<ShopcarBean> deleteShopcarBeanList = new ArrayList<>();

    private List<IShopCarChangeLinsterner> iShopcarDataChangeListenerList = new ArrayList<>();
    private static  CacheManager cacheManager;



    private Context context;

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (cacheManager == null) {
            cacheManager = new CacheManager();
        }

        return cacheManager;
    }

    public void init(Context context) {
        this.context = context;
        initService();
    }
    //第二步
    private void initService() {
        getShopcarDataFromServer();//获取购物车数据。
    }
    //获取购物车的数据
    private void getShopcarDataFromServer() {
        RetorfitCreate.getiNetworkserviceimpl().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<ShopcarBean>>, List<ShopcarBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<List<ShopcarBean>>() {
                    @Override
                    public void onNext(List<ShopcarBean> shopcarBeans) {
                        shopcarBeanList.addAll(shopcarBeans);//初始化购物车的公共数据
                        //通知页面去刷新UI
                        notifyShopcarDataChanged();
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        Toast.makeText(context, "请求购物车数据错误"+errorCode+errorMessage,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //通知数据改变
    private void notifyShopcarDataChanged() {
        for (IShopCarChangeLinsterner iShopCarChangeLinsterner : iShopcarDataChangeListenerList) {
            iShopCarChangeLinsterner.onDataChanged(shopcarBeanList);
        }
    }
    //增
    public void add(ShopcarBean shopcarBean) {
        shopcarBeanList.add(shopcarBean);
        for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }

    public List<ShopcarBean> getShopcarBeanList() {
        return shopcarBeanList;
    }

    public List<ShopcarBean> getSelectedProductBeanList() {
        List<ShopcarBean> selectedList = new ArrayList<>();
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                selectedList.add(shopcarBean);
            }
        }
        return selectedList;
    }

    public void removeSelectedProducts() {
        shopcarBeanList.removeAll(getSelectedProductBeanList());

        for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }

    public void setShopcarBeanList(List<ShopcarBean> shopcarBeanList) {
        this.shopcarBeanList = shopcarBeanList;
    }

    //当页面想监听数据的改变，就注册一个listener
    public void setShopcarDataChangeListener(IShopCarChangeLinsterner listener) {
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }

    public void updateProductSelected(int position) {
        ShopcarBean shopcarBean = shopcarBeanList.get(position);
        boolean newProductselected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductselected);

        //通知UI，数据发生了改变，需要更新UI
        for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
            if (isAllSelected()) {
                listener.onAllSelected(true);
            } else {
                listener.onAllSelected(false);
            }
        }
    }

    //获取购物车已选商品的总价
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

    //更新缓存中商品的数量
    public void updateProductNum(int position, String newNum) {
        ShopcarBean shopcarBean = shopcarBeanList.get(position);
        shopcarBean.setProductNum(newNum);

        //通知UI，数据发生了改变，需要更新UI
        for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
        }
    }

    //更新缓存中商品的数量
    public void updateProductNum(String productId, String newNum) {
        int i = 0;
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (shopcarBean.getProductId().equals(productId)) {
                //通知UI，数据发生了改变，需要更新UI
                shopcarBean.setProductNum(newNum);
                for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
                    listener.onOneDataChanged(i, shopcarBean);
                    listener.onMoneyChanged(getMoneyValue());
                }
                break;
            }
            i++;
        }
    }

    //当页面销毁时，页面不需要再监听数据改变了，我们把它从列表中删除
    public void unSetShopcarDataChangerListener(IShopCarChangeLinsterner listener) {
        if (iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.remove(listener);
        }
    }

    public ShopcarBean getShopcarBan(String productId) {
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (productId.equals(shopcarBean.getProductId())) {
                return shopcarBean;
            }
        }
        return null;
    }

    public boolean isAllSelected() {
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (!shopcarBean.isProductSelected()) {
                return false;
            }
        }
        return true;
    }

    public void selectAllProduct(boolean isAllSelect) {
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            shopcarBean.setProductSelected(isAllSelect);
        }

        //通知UI更新页面
        //通知UI，数据发生了改变，需要更新UI
        for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(isAllSelect);
        }

    }

    //把它加入到删除队列中
    public void addDeleteShopcarBean(ShopcarBean shopcarBean, int position) {
        deleteShopcarBeanList.add(shopcarBean);
        boolean isAllSelect = deleteShopcarBeanList.size() == shopcarBeanList.size();//判断下当前删除队列数据数目和购物车商品数目是否一致，一致代表是全选删除
        //需要更新UI
        for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            if(isAllSelect) {
                listener.onAllSelected(isAllSelect);
            }
        }
    }

    public boolean isAllSelectInEditMode() {
        return deleteShopcarBeanList.size() == shopcarBeanList.size();
    }

    //从删除队列中删除
    public void deleteOneShopcarBean(ShopcarBean shopcarBean, int position) {
        deleteShopcarBeanList.remove(shopcarBean);
        //需要更新UI
        for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onAllSelected(false);
        }
    }

    public void selectAllProductInEditMode(boolean isAllSelect) {
        if (isAllSelect) {
            deleteShopcarBeanList.clear();
            deleteShopcarBeanList.addAll(shopcarBeanList);
        } else {
            deleteShopcarBeanList.clear();
        }

        for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
            listener.onAllSelected(isAllSelect);
            listener.onDataChanged(shopcarBeanList);
        }
    }

    public boolean checkIfDataInDeleteShopcarBeanList(ShopcarBean shopcarBean) {
        return deleteShopcarBeanList.contains(shopcarBean);
    }

    public List<ShopcarBean> getDeleteShopcarBeanList() {
        return deleteShopcarBeanList;
    }

    public void processDeleteProducts() {
        //首先将删除列表中的数据在购物车缓存张删除
        shopcarBeanList.removeAll(deleteShopcarBeanList);
        //把删除列表清空
        deleteShopcarBeanList.clear();

        //通知UI刷新页面
        for(IShopCarChangeLinsterner listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }

    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    //购物车数据改变刷新
    public interface IShopCarChangeLinsterner{
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position, ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }


}
