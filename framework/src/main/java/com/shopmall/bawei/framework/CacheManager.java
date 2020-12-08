package com.shopmall.bawei.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.service.ShopmallService;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.RetroCreator;
import com.shopmall.bawei.net.ShopmallApiService;
import com.shopmall.bawei.net.ShopmallObserver;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.net.mode.ShopcarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

//存储购物车数据的类
public class CacheManager {

    //当用户登录成功后，CacheManger会调用服务端接口请求购物车数据。拿到购物车数据后，给shopcarBeanList赋值
    private List<ShopcarBean>  shopcarBeanList = new ArrayList<>();//缓存第一步:,定义单例，并且在单例中使用列表来存储缓存数据
    private List<ShopcarBean>  deleteShopcarBeanList = new ArrayList<>();

    private static CacheManager instance;

    //有多个页面监听数据的变化，所以维护一个监听listener的列表
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList = new ArrayList<>();

    private Context context;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }

    public void init(Context context) {
        this.context = context;
        initService();
    }

    //初始化service,完成自动登录功能
    private void initService() {
         Intent intent = new Intent(context,ShopmallService.class);
         context.startService(intent);//通过start启动service
        //缓存第2步:通过回调监听登录事件，一旦监听到登录成功，立马从服务端获取购物车数据，并且将数据塞到列表的缓存中,初始化缓存
        ShopUserManager.getInstance().registerUserLoginChangeListener(new ShopUserManager.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                getShopcarDataFromServer();//获取购物车数据。
            }

            @Override
            public void onUserLogout() {

            }
        });
    }

    //从服务端获取购物车的数据
    private void getShopcarDataFromServer() {
        RetroCreator.getShopmallApiServie().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<ShopcarBean>>, List<ShopcarBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<List<ShopcarBean>>() {
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

    //缓存第三步：提供修改缓存数据的接口
    public void add(ShopcarBean shopcarBean) {
        shopcarBeanList.add(shopcarBean);//提供接口，添加一个商品到购物车公共数据里
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }

    private void notifyShopcarDataChanged() {
        //遍历这listener列表，去逐个通知页面购物车产品数据变化了
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }

    //缓存第三步：提供方法让别的类可以获取缓存
    public List<ShopcarBean> getShopcarBeanList() {
        return shopcarBeanList;
    }

    //获取已经选择的商品列表
    public List<ShopcarBean> getSelectedProductBeanList() {
        List<ShopcarBean> selectedList = new ArrayList<>();
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                selectedList.add(shopcarBean);
            }
        }
        return selectedList;
    }

    //从缓存中删除已经支付的商品
    public void removeSelectedProducts() {
        shopcarBeanList.removeAll(getSelectedProductBeanList());

        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }

    public void setShopcarBeanList(List<ShopcarBean> shopcarBeanList) {
        this.shopcarBeanList = shopcarBeanList;
    }

    //当页面想监听数据的改变，就注册一个listener
    public void setShopcarDataChangeListener(IShopcarDataChangeListener listener) {
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }

    //因为只有两个状态，所以改成相反的状态即可
    public void updateProductSelected(int position) {
        ShopcarBean shopcarBean = shopcarBeanList.get(position);
        boolean newProductselected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductselected);

        //通知UI，数据发生了改变，需要更新UI
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
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
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
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
                for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
                    listener.onOneDataChanged(i, shopcarBean);
                    listener.onMoneyChanged(getMoneyValue());
                }
                break;
            }
            i++;
        }
    }

    //当页面销毁时，页面不需要再监听数据改变了，我们把它从列表中删除
    public void unSetShopcarDataChangerListener(IShopcarDataChangeListener listener) {
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
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
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
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
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
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
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

        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
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
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }

    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    //定义接口，当购物车数据发生改变时，通过该接口通知页面刷新
    public interface IShopcarDataChangeListener {
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position, ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }
}
