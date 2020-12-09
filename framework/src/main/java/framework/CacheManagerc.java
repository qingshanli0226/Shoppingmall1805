package framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import net.FoodService;
import net.RxjavaRetortUlis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mode.BaseBean;
import mode.ShopcarBean;
import view.ShopmallConstant;

public
class CacheManagerc {
    private List<ShopcarBean> shopcarBeansList = new ArrayList<>();
    private List<ShopcarBean> deleteShopcarBeanList = new ArrayList<>();

    //这是一个全局属性的类名
    private static CacheManagerc instace;

    //有多个页面，须要监听数据变化，写一个监听列表，列表中有修改查询刷新的方法
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList = new ArrayList<>();

    private Context context;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public CacheManagerc() {

    }

    public static  CacheManagerc getInstance(){
        if (instace==null){
            synchronized (CacheManagerc.class){
                if (instace==null){
                    instace = new CacheManagerc();
                }
            }
        }
        return instace;
    }

    public void init(Context context){
        this.context = context;
        initReceiver();
    }
    //监听当前的登录 调用此类时即开始
    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter(ShopmallConstant.LOGIN_ACTION);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(ShopmallConstant.LOGIN_ACTION)) {
                    getShopcarDataFromServer();
                }
            }
        }, intentFilter);
    }
    private void getShopcarDataFromServer() {
        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);
        foodService.getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<ShopcarBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {
                        shopcarBeansList.addAll(listBaseBean.getResult());
                        notifyShopcarDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void notifyShopcarDataChanged() {

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeansList);
        }
    }
    public void add(ShopcarBean shopcarBean){
        shopcarBeansList.add(shopcarBean);
        for (IShopcarDataChangeListener dataChangeListener :iShopcarDataChangeListenerList){
            dataChangeListener.onDataChanged(shopcarBeansList);
        }
    }
    public List<ShopcarBean> getShopcarBeanList(){
        return shopcarBeansList;
    }

    public List<ShopcarBean> getSelectedPreduceBeanList(){
        List<ShopcarBean> selectedList = new ArrayList<>();
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (shopcarBean.isProductSelected()) {
                selectedList.add(shopcarBean);
            }
        }
        return selectedList;
    }

    public void removeSelectedPrpducte(){
        shopcarBeansList.removeAll(getSelectedPreduceBeanList());

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeansList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }

    public List<ShopcarBean> getShopcarBeansList() {
        return shopcarBeansList;
    }

    public void setShopcarBeansList(List<ShopcarBean> shopcarBeansList) {
        this.shopcarBeansList = shopcarBeansList;
    }

    public void setiShopcarDataChangeListener(IShopcarDataChangeListener listener){
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }

    public String getMoneyValue() {
        float totalPrice = 0;
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (shopcarBean.isProductSelected()) {
                float productPrice = Float.parseFloat(shopcarBean.getProductPrice());
                int productNum = Integer.parseInt(shopcarBean.getProductNum());
                totalPrice = totalPrice + productPrice*productNum;
            }
        }
        return String.valueOf(totalPrice);
    }
    public void updateProductSelected(int position) {
        ShopcarBean shopcarBean = shopcarBeansList.get(position);
        boolean newProductselected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductselected);
        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
            if (isAllSelected()) {
                listener.onAllSelected(true);
            } else {
                listener.onAllSelected(false);
            }
        }
    }

    public boolean isAllSelected() {
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (!shopcarBean.isProductSelected()) {
                return false;
            }
        }
        return true;
    }
    public void updateProductNum(int position, String newNum) {
        ShopcarBean shopcarBean = shopcarBeansList.get(position);
        shopcarBean.setProductNum(newNum);


        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
        }
    }

    public void updateProductNum(String productId, String newNum) {
        int i = 0;
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (shopcarBean.getProductId().equals(productId)) {
                shopcarBean.setProductNum(newNum);
                for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
                    listener.onOneDataChanged(i, shopcarBean);
                    listener.onMoneyChanged(getMoneyValue());
                }
                break;
            }
            i++;
        }
    }
    public void unSetShopcarDataChangerListener(CacheManager.IShopcarDataChangeListener listener) {
        if (iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.remove(listener);
        }
    }

    public ShopcarBean getShopcarBan(String productId) {
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (productId.equals(shopcarBean.getProductId())) {
                return shopcarBean;
            }
        }
        return null;
    }

    public void selectAllProduct(boolean isAllSelect) {
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            shopcarBean.setProductSelected(isAllSelect);
        }

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeansList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(isAllSelect);
        }

    }

    public void addDeleteShopcarBean(ShopcarBean shopcarBean, int position) {
        deleteShopcarBeanList.add(shopcarBean);
        boolean isAllSelect = deleteShopcarBeanList.size() == shopcarBeansList.size();

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            if(isAllSelect) {
                listener.onAllSelected(isAllSelect);
            }
        }
    }

    public boolean isAllSelectInEditMode() {
        return deleteShopcarBeanList.size() == shopcarBeansList.size();
    }

    public void deleteOneShopcarBean(ShopcarBean shopcarBean, int position) {
        deleteShopcarBeanList.remove(shopcarBean);

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onAllSelected(false);
        }
    }

    public void selectAllProductInEditMode(boolean isAllSelect) {
        if (isAllSelect) {
            deleteShopcarBeanList.clear();
            deleteShopcarBeanList.addAll(shopcarBeansList);
        } else {
            deleteShopcarBeanList.clear();
        }

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onAllSelected(isAllSelect);
            listener.onDataChanged(shopcarBeansList);
        }
    }

    public boolean checkIfDataInDeleteShopcarBeanList(ShopcarBean shopcarBean) {
        return deleteShopcarBeanList.contains(shopcarBean);
    }

    public List<ShopcarBean> getDeleteShopcarBeanList() {
        return deleteShopcarBeanList;
    }

    public void processDeleteProducts() {
        shopcarBeansList.removeAll(deleteShopcarBeanList);

        deleteShopcarBeanList.clear();

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeansList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }

    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public interface IShopcarDataChangeListener{
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position,ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyVilue);
        void onAllSelected(boolean isAllSelect);
    }




}
