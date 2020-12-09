package com.example.framework;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {
    private static CacheManager instance;
    //当用户登录成功后，CacheManager会调用服务端接口请求数据，拿到数据之后，给shopcarBeanlist赋值
    //缓存第一步，定义单例，并且将单例中使用列表缓存数据
    private List<ShopcarBean> shopcarBeans = new ArrayList<>();


    //有多个页面要进行刷新，所以维护一个listerter监听列表
    private List<IShopcarDataCharListerter> listerters = new ArrayList<>();
    private Context context;
    public CacheManager(){

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
    //初始化sercice判断登录状态
    private void initService() {
        Intent intent = new Intent();
        intent.setClass(context, MyService.class);
        context.startService(intent);
        //缓存第二部，通过回调监听登录事件，一旦监听到登录成功，就立马从服务端获得数据，并且将数据赛道集合当中
        ShopUsermange.getInstance().registerUserLoginChangeListenter(new ShopUsermange.IUserLoginChangeLiestener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                getshopCardateserver();//获取购物车数据
            }

            @Override
            public void onUserlogout() {

            }
        });
    }
    //从服务端获取购物车数据
    public void getshopCardateserver(){
        Retrofitcreators.getiNetPresetenterWork().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<ShopcarBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {
                        if (listBaseBean.getCode().equals("200")){
                            shopcarBeans.addAll(listBaseBean.getResult());
                            nofity();
                        }else {
                            Toast.makeText(context, "请求购物车数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void nofity(){//通过接口刷新
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.ondataChanged(shopcarBeans);
        }
    }


    //缓存第三部，提供修改缓存数据的接口
    public void add(ShopcarBean shopcarBean){
        shopcarBeans.add(shopcarBean);//提供接口，添加一个数据到服务器当中
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.ondataChanged(shopcarBeans);
        }
    }

    //遍历这个listenter集合，让他逐步去通知刷新购物车数据变化
    public void notifyShopCarDateChanged(){
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.ondataChanged(shopcarBeans);
        }
    }
    //缓存第三部，提供方法，可以让别的类获取到缓存
    public List<ShopcarBean> getShopcarList(){
        return shopcarBeans;
    }
    //当页面想监听数据的时候，就注册一个listenter
    public void setshopcarChangedListenter(IShopcarDataCharListerter listerter){
        if (!listerters.contains(listerter)){
            listerters.add(listerter);
        }

    }
    public ShopcarBean getShopcarBan(String productId){
        for (ShopcarBean shopcarBean : shopcarBeans) {
            if (productId.equals(shopcarBean.getProductId())){
                return shopcarBean;
            }
        }
        return null;
    }
    //更新缓存中购物车的商品的数量
    public void updateProductNum(String productId,String newNum){
        int i = 0;
        for (ShopcarBean shopcarBean : shopcarBeans) {
            if (productId.equals(shopcarBean.getProductId())){
                //通知Ui页面,商品数量发生了改变
                shopcarBean.setProductNum(newNum);
                for (IShopcarDataCharListerter listerter : listerters) {
                    //使用接口回调
                    listerter.onOneChanged(i,shopcarBean);

                }
                break;
            }
            i++;
        }
    }
    //定义接口，当数据发生改变的时候，通过接口来通知ui刷新
    public interface IShopcarDataCharListerter{
        void ondataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneChanged(int position,ShopcarBean shopcarBean);
        void onManeyvhanged(String moneyValue);
        void onAllselected(boolean isAllSelect);
    }
}
