package com.example.framework;

import android.content.Context;
import android.content.Intent;

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

    private static CacheManager instance;

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
    //从服务端获取购物车的数据
    private void getShopcarDataFromServer() {
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
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


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

    private void notifyShopcarDataChanged(){
        //遍历这listener列表,去逐个通知页面购物车产品数据变化了
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }
    public List<ShopcarBean> getShopcarBeanList(){
        return shopcarBeanList;
    }

    //获取已经选择的商品列表
    public List<ShopcarBean> getSelectedProductBeanList() {
        List<ShopcarBean> selectedList = new ArrayList<>();
        for(ShopcarBean shopcarBean:shopcarBeanList) {
//            if (shopcarBean.isProductSelected()) {
//                selectedList.add(shopcarBean);
//            }
        }
        return selectedList;
    }
}
