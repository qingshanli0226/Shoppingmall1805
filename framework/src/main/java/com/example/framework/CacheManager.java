package com.example.framework;

import android.content.Context;
import android.content.Intent;

import com.example.net.LoginBean;
import com.example.net.ShopUserManger;
import com.example.net.ShopcarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    }

    //定义接口，当购物车数据发生改变时，通过该接口通知页面刷新
    public interface IShopcarDataChangeListener {
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position, ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }
}
