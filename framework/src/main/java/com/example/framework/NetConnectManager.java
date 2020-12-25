package com.example.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

//这是一个网络管理单列的类
public class NetConnectManager {
    private static NetConnectManager instance;
    private Context context;
    private boolean isContact;
    private NetConnectManager(){

    }
    public static NetConnectManager getInstance(){
        if (instance == null){
            instance = new NetConnectManager();
        }
        return instance;
    }
    /**
     * 获取当前网络信息
     */
    private void getCurrentConnectStaus(){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取当前网络连接信息
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()){
            isContact = true;
        }else {
            isContact = false;
        }
    }
    public void init(Context applactioncontext){
        context = applactioncontext;
        //获取当前网络信息
        getCurrentConnectStaus();
        //网络连接时候是变化的，所以要注册广播监听网络连接情况
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        applactioncontext.registerReceiver(registerReceiver,intentFilter);

    }
    private BroadcastReceiver registerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getCurrentConnectStaus();
                //使用EventBus更新
                notifyConnectChanged();
                Log.e("Zld","1111111111");
            }
        }
    };

    private void notifyConnectChanged() {
        EventBus.getDefault().post(isContact);
    }
    /**
     * 提供一个方法，让别的类能够拿到你的网络连接状态
     */
    public boolean getisconnect(){
        return isContact;
    }
}
