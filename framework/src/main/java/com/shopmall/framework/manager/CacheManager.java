package com.shopmall.framework.manager;

import com.shopmall.common.Constants;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {
    private static  CacheManager instance;

    private List<ShopcarBean.ResultBean> shopCarBeanList = new ArrayList<>();

    private List<ShopcarBean.ResultBean> deleteShopCarBeanList = new ArrayList<>();

    private List<IShopCarDataChangeListener> iShopCarDataChangeListenerList = new ArrayList<>();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private CacheManager(){

    }

    public static CacheManager getInstance(){
        if (instance == null){
            instance = new CacheManager();
        }
        return instance;
    }

    public void getShopCarDataFromServer() {
        if (shopCarBeanList != null){
            shopCarBeanList.clear();
        }
        RetrofitFactory.getInstance().create(Https.class)
                .getShopCar(Constants.GETSHORTCART_PRODUCT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopcarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopcarBean shopcarBean) {
                        shopCarBeanList.addAll(shopcarBean.getResult());
                        notifyShopCarDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void add(ShopcarBean.ResultBean shopcarBean){
        shopCarBeanList.add(shopcarBean);
        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onDataChanged(shopCarBeanList);
        }
    }

    private void notifyShopCarDataChanged() {
        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onDataChanged(shopCarBeanList);
        }
    }

    public List<ShopcarBean.ResultBean> getShopCarBeanList(){
        return shopCarBeanList;
    }

    public List<ShopcarBean.ResultBean> getSelectedProductBeanList(){
        List<ShopcarBean.ResultBean> selectedList = new ArrayList<>();
        for (ShopcarBean.ResultBean shopcarBean:shopCarBeanList){
            if (shopcarBean.isProductSelected()){
                selectedList.add(shopcarBean);
            }
        }
        return selectedList;
    }

    public void removeSelectedProducts(){
        shopCarBeanList.remove(getSelectedProductBeanList());

        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onDataChanged(shopCarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }

    //勾选添加删除数据,判断是否存在，不存在添加，存在取消勾选删除
    public void adddeleteshopcarBean(ShopcarBean.ResultBean shopcar,int postion){
        if (!deleteShopCarBeanList.contains(shopcar)){
            deleteShopCarBeanList.add(shopcar);
        }else {
            deleteShopCarBeanList.remove(shopcar);
        }
        notifyDeleteBoolean();
    }

    // 判断删除的数据和总数据是否一致
    public boolean isDeleteAllSelect(){
        if (shopCarBeanList.size()==deleteShopCarBeanList.size()){
            return true;
        }else {
            return false;
        }
    }

    //切换结算清空删除集合
    public void delteclear(){
        if (deleteShopCarBeanList!=null){
            deleteShopCarBeanList.clear();
        }
        notifyDeleteBoolean();
    }

    //通知知注册页面删除否全选
    public void notifyDeleteBoolean(){
        for (IShopCarDataChangeListener listener : iShopCarDataChangeListenerList) {
            listener.getDeleteAllSelect(isDeleteAllSelect());
        }
    }

    //更改缓存中的购物车产品的选择状态
    public void updateshopcarselect(ShopcarBean.ResultBean shopcar,int positon){
        ShopcarBean.ResultBean resultBean = shopCarBeanList.get(positon);
        if (shopcar.isProductSelected()){
            resultBean.setProductSelected(false);
        }else {
            resultBean.setProductSelected(true);
        }

        updateshopcarbean(resultBean,positon);

    }

    //通知每个页面，当前位置发生改变，刷新该位置
    public void updateshopcarbean(ShopcarBean.ResultBean shopcar,int postion){
        for (IShopCarDataChangeListener listener : iShopCarDataChangeListenerList) {
            listener.onOneDataChanged(postion,shopcar);
            listener.onAllSelected(isAllSelected());
            listener.onMoneyChanged(getMoneyValue());
        }
    }

    public void setShopCarBeanList(List<ShopcarBean.ResultBean> shopCarBeanList){
        this.shopCarBeanList = shopCarBeanList;
    }

    public void setShopCarDataChangeListener(IShopCarDataChangeListener listener){
        if (!iShopCarDataChangeListenerList.contains(listener)){
            iShopCarDataChangeListenerList.add(listener);
        }
    }

    public void updateProductSelected(int position){
        ShopcarBean.ResultBean shopcarBean = shopCarBeanList.get(position);
        boolean newProductSelected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductSelected);

        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onOneDataChanged(position,shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
            if (isAllSelected()){
                listener.onAllSelected(true);
            }else {
                listener.onAllSelected(false);
            }
        }
    }

    public String getMoneyValue() {
        float totalPrice = 0;
        for (ShopcarBean.ResultBean shopcarBean:shopCarBeanList){
            if (shopcarBean.isProductSelected()){
                float productPrice = Float.parseFloat((String) shopcarBean.getProductPrice());
                int productNum = Integer.parseInt(shopcarBean.getProductNum());
                totalPrice = totalPrice+productPrice*productNum;
            }
        }
        return String.valueOf(totalPrice);
    }

    public void updateProductNum(int position,String newNum){
        ShopcarBean.ResultBean shopcarBean = shopCarBeanList.get(position);
        shopcarBean.setProductNum(newNum);

        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onOneDataChanged(position,shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
        }
    }

    public void updateProductNum(String productId, String newNum){
        int i = 0;
        for (ShopcarBean.ResultBean shopcarBean:shopCarBeanList){
            if (shopcarBean.getProductId().equals(productId)){
                shopcarBean.setProductNum(newNum);
                for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
                    listener.onOneDataChanged(i,shopcarBean);
                    listener.onMoneyChanged(getMoneyValue());
                }
                break;
            }
            i++;
        }
    }

    public void unSetShopCarDataChangerListener(IShopCarDataChangeListener listener){
        if (iShopCarDataChangeListenerList.contains(listener)){
            iShopCarDataChangeListenerList.remove(listener);
        }
    }

    public ShopcarBean.ResultBean getShopCarBean(String productId){
        for (ShopcarBean.ResultBean shopcarBean:shopCarBeanList){
            if (productId.equals(shopcarBean.getProductId())){
                return shopcarBean;
            }
        }
        return null;
    }

    public boolean isAllSelected(){
        for (ShopcarBean.ResultBean shopcarBean:shopCarBeanList){
            if (!shopcarBean.isProductSelected()){
                return false;
            }
        }
        return true;
    }

    public void selectAllProduct(boolean isAllSelect){
        for (ShopcarBean.ResultBean shopcarBean:shopCarBeanList){
            shopcarBean.setProductSelected(isAllSelect);
        }

        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onDataChanged(shopCarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(isAllSelect);
        }
    }

    public void addDeleteShopCarBean(ShopcarBean.ResultBean shopcarBean,int position){
        deleteShopCarBeanList.add(shopcarBean);
        boolean isAllSelect = deleteShopCarBeanList.size() == shopCarBeanList.size();

        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onOneDataChanged(position,shopcarBean);
            if (isAllSelect){
                listener.onAllSelected(isAllSelect);
            }
        }
    }

    public boolean isAllSelectInEditMode(){
        return deleteShopCarBeanList.size() == shopCarBeanList.size();
    }

    public void deleteOneShopCarBean(ShopcarBean.ResultBean shopcarBean,int position){
        deleteShopCarBeanList.remove(shopcarBean);

        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onOneDataChanged(position,shopcarBean);
            listener.onAllSelected(false);
        }
    }

    public void selectAllProductInEditMode(boolean isAllSelect){
        if (isAllSelect){
            deleteShopCarBeanList.clear();
            deleteShopCarBeanList.addAll(shopCarBeanList);
        }else {
            deleteShopCarBeanList.clear();
        }

        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onAllSelected(false);
            listener.onDataChanged(shopCarBeanList);
        }
    }

    public boolean checkIfDataInDeleteShopCarBeanList(ShopcarBean.ResultBean shopcarBean){
        return deleteShopCarBeanList.contains(shopcarBean);
    }

    public List<ShopcarBean.ResultBean> getDeleteShopCarBeanList(){
        return deleteShopCarBeanList;
    }

    public void processDeleteProducts(){
        //删除列表中购物车的缓存数据
        shopCarBeanList.removeAll(deleteShopCarBeanList);
        //清空删除列表
        deleteShopCarBeanList.clear();

        for (IShopCarDataChangeListener listener:iShopCarDataChangeListenerList){
            listener.onDataChanged(shopCarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }

    public ExecutorService getExecutorService(){
        return executorService;
    }

    //定义接口，当购物车数据发生改变，通过接口通知页面刷新
    public interface IShopCarDataChangeListener{
        void onDataChanged(List<ShopcarBean.ResultBean> shopCarBeanList);
        void onOneDataChanged(int position,ShopcarBean.ResultBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
        void getDeleteAllSelect(boolean isAllSelect);
    }
}
