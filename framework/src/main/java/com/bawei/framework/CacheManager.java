package com.bawei.framework;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.ExceptionUtil;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.mode.BaseBean;
import com.bawei.net.mode.LoginBean;
import com.bawei.net.mode.ShopcarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {
    //登录成功后，调用服务端接口请求购物车数据，拿到数据后赋值
    private List<ShopcarBean> shopcarBeanList = new ArrayList<>();
    private List<ShopcarBean> deleteShopcarBeanList = new ArrayList<>();

    private static CacheManager instance;

    //有多个页面监听数据变化，维护一个监听listener的列表
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

    private void getShopcarDataFromServer() {
        RetrofitCreate.getApi().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<ShopcarBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {
                        List<ShopcarBean> result = listBaseBean.getResult();
                        shopcarBeanList.addAll(result);
                        notifyShopcarDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorBean errorBean = ExceptionUtil.getErrorBean(e);
                        Toast.makeText(context, "请求购物车数据错误" + errorBean.getErrorCode() + errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void add(ShopcarBean shopcarBean) {
        shopcarBeanList.add(shopcarBean);
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopcarBeanList);
        }
    }

    private void notifyShopcarDataChanged() {
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopcarBeanList);
        }
    }

    public List<ShopcarBean> getShopcarBeanList() {
        return shopcarBeanList;
    }

    public List<ShopcarBean> getSelectedProductBeanList() {
        List<ShopcarBean> selectedList = new ArrayList<>();
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                selectedList.add(shopcarBean);
            }
        }
        return selectedList;
    }

    public void removeSelectedProducts() {
        shopcarBeanList.removeAll(getSelectedProductBeanList());
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopcarBeanList);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
            iShopcarDataChangeListener.onAllSelected(false);
        }
    }

    public String getMoneyValue() {
        float totalPrice = 0;
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                float productPrice = Float.parseFloat(shopcarBean.getProductPrice());
                int productNum = Integer.parseInt(shopcarBean.getProductName());
                totalPrice = totalPrice + productPrice * productNum;
            }
        }
        return String.valueOf(totalPrice);
    }

    public void setShopcarDataChangeListener(IShopcarDataChangeListener listener) {
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }

    public void updateProductSelected(int position) {
        ShopcarBean shopcarBean = shopcarBeanList.get(position);
        boolean newProductSelected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductSelected);

        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onOneDataChanged(position, shopcarBean);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
            if (isAllSelected()) {
                iShopcarDataChangeListener.onAllSelected(true);
            } else {
                iShopcarDataChangeListener.onAllSelected(false);
            }
        }
    }

    public boolean isAllSelected() {
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if (!shopcarBean.isProductSelected()) {
                return false;
            }
        }
        return true;
    }

    public void selectAllProduct(boolean isAllSelect) {
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            shopcarBean.setProductSelected(isAllSelect);
        }

        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopcarBeanList);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
            iShopcarDataChangeListener.onAllSelected(isAllSelect);
        }
    }

    public void updateProductNum(int position, String newNum) {
        ShopcarBean shopcarBean = shopcarBeanList.get(position);
        shopcarBean.setProductNum(newNum);

        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onOneDataChanged(position, shopcarBean);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
        }
    }

    public void updateProductNum(String productId, String newNum) {
        int i = 0;
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if (shopcarBean.getProductId().equals(productId)) {
                shopcarBean.setProductNum(newNum);
                for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
                    iShopcarDataChangeListener.onOneDataChanged(i, shopcarBean);
                    iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
                }
                break;
            }
            i++;
        }
    }

    public void unSetShopcarDataChangerListener(IShopcarDataChangeListener listener) {
        if (iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.remove(listener);
        }
    }

    public void selectAllProductInEditMode(boolean isAllSelect) {
        if (isAllSelect) {
            deleteShopcarBeanList.clear();
            deleteShopcarBeanList.addAll(shopcarBeanList);
        } else {
            deleteShopcarBeanList.clear();
        }

        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onAllSelected(isAllSelect);
            iShopcarDataChangeListener.onDataChanged(shopcarBeanList);
        }
    }

    public boolean isAllSelectInEditMode() {
        return deleteShopcarBeanList.size() == shopcarBeanList.size();
    }

    public void processDeleteProducts() {
        shopcarBeanList.removeAll(deleteShopcarBeanList);
        deleteShopcarBeanList.clear();

        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onDataChanged(shopcarBeanList);
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
            iShopcarDataChangeListener.onAllSelected(false);
        }
    }

    public List<ShopcarBean> getDeleteShopcarBeanList() {
        return deleteShopcarBeanList;
    }

    private void initService() {
        Intent intent = new Intent(context, ShopmallService.class);
        context.startService(intent);
        ShopUserManager.getInstance().registerUserLoginChangeListener(new ShopUserManager.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                getShopcarDataFromServer();
            }

            @Override
            public void onUserLogout() {

            }
        });
    }

    public void addDeleteShopcarBean(ShopcarBean shopcarBean, int position) {
        deleteShopcarBeanList.add(shopcarBean);
        boolean isAllSelect = deleteShopcarBeanList.size() == shopcarBeanList.size();
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            if (isAllSelect) {
                listener.onAllSelected(isAllSelect);
            }
        }
    }

    public void deleteOneShopcarBean(ShopcarBean shopcarBean, int position) {
        deleteShopcarBeanList.remove(shopcarBean);
        for (IShopcarDataChangeListener iShopcarDataChangeListener : iShopcarDataChangeListenerList) {
            iShopcarDataChangeListener.onOneDataChanged(position, shopcarBean);
            iShopcarDataChangeListener.onAllSelected(false);
        }
    }

    public boolean checkIfDataInDeleteShopcarBeanList(ShopcarBean shopcarBean) {
        return deleteShopcarBeanList.contains(shopcarBean);
    }

    public interface IShopcarDataChangeListener {
        void onDataChanged(List<ShopcarBean> shopcarBeanList);

        void onOneDataChanged(int postion, ShopcarBean shopcarBean);

        void onMoneyChanged(String moneyValue);

        void onAllSelected(boolean isAllSelect);
    }
}
