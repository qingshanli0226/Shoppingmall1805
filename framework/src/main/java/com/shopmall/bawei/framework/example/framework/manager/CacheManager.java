package com.shopmall.bawei.framework.example.framework.manager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.net.NetFunction;
import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.framework.example.framework.service.MyService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {

    private List<ShopcarBean> shopcarBeanList = new ArrayList<>();
    private List<ShopcarBean> deleteShopcarBeanList = new ArrayList<>();


    private static CacheManager instance;

    //有多个页面监听数据的变化，所以维护一个监听listener的列表
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList = new ArrayList<>();

    private Context context;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private CacheManager(){

    }

    public static CacheManager getInstance() {
        if (instance == null){
            instance = new CacheManager();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
        initService();
    }

    private void initService() {

        Intent intent = new Intent(context, MyService.class);
        context.startService(intent);
        Log.i("---", "initService: "+"进入");

        UserManage.getInstance().registerUserLoginChangeListener(new UserManage.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                Log.i("---", "onUserLogin: "+loginBean.getToken());
                getShopcarDataFroServer();//获取购物车数据
            }

            @Override
            public void onUserLogout() {

            }
        });


    }

    //从服务端获取购物车的数据
    private void getShopcarDataFroServer() {

        Retrofitcreators.getiNetPresetenterWork().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<ShopcarBean>>,List<ShopcarBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ShopcarBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ShopcarBean> shopcarBeans) {
                        shopcarBeanList.addAll(shopcarBeans);
                        Log.i("---", "onNext: getShopCarProductNum："+shopcarBeans.size());
                        notifyShopcarDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("----", "onError: getshopcarProduct："+e.getMessage());
                        Toast.makeText(context, "请求购物车数据错误",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    private void notifyShopcarDataChanged() {
        //遍历这listener列表，去逐个通知页面购物车产品数据变化了
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }

    //缓存第三步：提供修改缓存数据的接口
    public void add(ShopcarBean shopcarBean) {
        Log.e("---","添加成功");
        shopcarBeanList.add(shopcarBean);//提供接口，添加一个商品到购物车公共数据里
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
        Log.e("---","isProductSelected: "+shopcarBean.isProductSelected());
        boolean newProductselected = !shopcarBean.isProductSelected();
        Log.e("---","newProductselected: "+newProductselected);
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

    public String getMoneyValue() {
        float totalPrice = 0;
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                Log.e("---", "getMoneyValue: productNum："+shopcarBean.getProductNum());
                Log.e("---", "getMoneyValue: productPrice"+shopcarBean.getProductPrice());
                float productPrice = Float.parseFloat(shopcarBean.getProductPrice());

                int productNum = Integer.parseInt(shopcarBean.getProductNum());
                totalPrice = totalPrice + productPrice * productNum;
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
        //首先将删除列表中的数据在购物车缓存中删除
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

    //获取购物车选中后的商品列表
    public List<ShopcarBean> getselectproductList(){
        List<ShopcarBean> selectProductList = new ArrayList<>();
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if (shopcarBean.isProductSelected()){
                selectProductList.add(shopcarBean);
            }
        }
        return selectProductList;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    //定义接口，当购物车数据发生改变时，通过该接口通知页面刷新
    public interface IShopcarDataChangeListener{
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position, ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }

}
