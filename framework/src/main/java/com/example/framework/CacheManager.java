package com.example.framework;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.net.BaseBean;
import com.example.net.ConfigUrl;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.example.net.LoginBean;
import com.example.net.ShopUserManger;
import com.example.net.ShopcarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {

    //当用户登录成功后 ,CacheManager会调用服务端接口请求购物车数据,拿到购物车数据后给shopcarBeanList赋值
    private List<ShopcarBean> shopcarBeanList = new ArrayList<>();
    private List<ShopcarBean> deleteShopcarBeanList = new ArrayList<>();
    private List<LoginBean> loginBeansList = new ArrayList<>();

    private static CacheManager instance;
    private LoginBean loginBean = new LoginBean();

    //有多个页面监听数据的变化,所以维护一个监听listener的列表
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList = new ArrayList<>();

    private Context context;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private CacheManager(){
    }

    public static CacheManager getInstance(){
        if (instance==null){
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

        Intent intent = new Intent(context,LoginService.class);
        context.startService(intent);
        //缓存第二步,通过回调监听登录事件,一旦监听到登陆成功,立马从服务端获取购物车数据,并且把数据塞到缓存里,初始化缓存
        ShopUserManger.getInstance().requesterUserLoginChangeListener(new ShopUserManger.IUserLoginChangeListener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                getShopcarDataFromServer();
            }

            @Override
            public void onUserlogout() {

            }
        });
    }

    public ShopcarBean getShopId(String Id){
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if (Id.equals(shopcarBean.getProductId())){
                return shopcarBean;
            }
        }
        return null;
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
    public boolean checkIfDataInDeleteShopcarBeanList(ShopcarBean shopcarBean) {
        return deleteShopcarBeanList.contains(shopcarBean);
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

    //从缓存中删除已经支付的商品
    public void removeSelectedProducts() {
        shopcarBeanList.removeAll(getSelectedProductBeanList());

        for(CacheManager.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }
    public boolean isAllSelectInEditMode() {
        return deleteShopcarBeanList.size() == shopcarBeanList.size();
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


    //从删除队列中删除
    public void deleteOneShopcarBean(ShopcarBean shopcarBean, int position) {
        deleteShopcarBeanList.remove(shopcarBean);
        //需要更新UI
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onAllSelected(false);
        }
    }

    //更新缓存中商品的数量
    public void updateProductNum(int position,String newNum){
        ShopcarBean shopcarBean = shopcarBeanList.get(position);
        shopcarBean.setProductNum(newNum);

        for (IShopcarDataChangeListener listener:iShopcarDataChangeListenerList){
            listener.onOneDataChanged(position,shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
        }
    }



    //从服务端获取购物车的数据
    public void getShopcarDataFromServer() {
        if (shopcarBeanList!=null){
            shopcarBeanList.clear();
        }
        Log.i("suibian", "onNext: ");
        new HttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<ShopcarBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {
                        shopcarBeanList.addAll(listBaseBean.getResult());
                        Log.i("oneshuju", "onNext: "+listBaseBean.getResult().get(0).getProductName());
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

    //当页面想监听数据的改变，就注册一个listener
    public void setShopcarDataChangeListener(IShopcarDataChangeListener listener) {
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }

    //定义接口，当购物车数据发生改变时，通过该接口通知页面刷新
    public interface IShopcarDataChangeListener {
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position, ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }

    //缓存第三步:提供修改缓存数据的接口
    public void add(ShopcarBean shopcarBean){
        shopcarBeanList.add(shopcarBean);

        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }

    //改变缓存中用户的phone和address
    public void setLoginBeanPhone(String phone){
        Log.i("TAG", "setLoginBeanPhone: "+phone);
            loginBean.setPhone(phone);
    }
    public void setLoginBeanAddress(String address){
            loginBean.setAddress(address);
            Log.i("phone", "setLoginBeanAddress: "+loginBean.getPhone()+"");
    }
    public Object getPhone(){

        return loginBean.getPhone();
    }

    public Object getAddress(){

        return loginBean.getAddress();
    }


    //总价
    public String getMoneyValue() {
        float totalPrice = 0;
        for(ShopcarBean shopcarBean:shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                float productPrice = Float.parseFloat((String) shopcarBean.getProductPrice());
                int productNum = Integer.parseInt(shopcarBean.getProductNum());
                totalPrice = totalPrice + productPrice*productNum;
            }
        }
        return String.valueOf(totalPrice);
    }



    public void upDataNum(String dataId,String dataNum){
        int o = 0;
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if (shopcarBean.getProductId().equals(dataId)){
                shopcarBean.setProductNum(dataNum);
                for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
                    listener.onOneDataChanged(o,shopcarBean);
                }
                break;
            }
            o++;
        }
    }

    private void notifyShopcarDataChanged(){
        //遍历这listener列表,去逐个通知页面购物车产品数据变化了
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }
    public List<ShopcarBean> getShopcarBeanList(){
        return shopcarBeanList;
    }

    public List<ShopcarBean> getDeleteShopcarBeanList() {
        return deleteShopcarBeanList;
    }

    public List<LoginBean> getLoginBeansList(){
        return loginBeansList;
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

    public boolean isAllSelected(){
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
    public void updateProductSelected(int position) {
        ShopcarBean shopcarBean = shopcarBeanList.get(position);
        boolean newProductselected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductselected);

        //通知UI，数据发生了改变，需要更新UI
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
            if (isAllSelected()) {
                listener.onAllSelected(true);
            } else {
                listener.onAllSelected(false);
            }
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

}
