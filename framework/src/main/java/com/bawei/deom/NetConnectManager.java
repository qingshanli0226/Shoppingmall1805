package com.bawei.deom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;

public class NetConnectManager {
    private static  NetConnectManager instance;
    private boolean isConnected;
    private Context context;

    public NetConnectManager() {

    }
    public static NetConnectManager getInstance(){
        if (instance==null){
            instance=new NetConnectManager();
        }
        return  instance;
    }
    public void init(Context applicationContext){
        context=applicationContext;
        getCurrentConnectStaus();
        //网络是变化的，注册广播监听网络的连接情况
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        applicationContext.registerReceiver(connectReceiver,intentFilter);
    }
    private BroadcastReceiver connectReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getCurrentConnectStaus();
                notifyConnectChannged();
            }
        }
    };
    private void notifyConnectChannged() {
        EventBus.getDefault().post(isConnected);
    }
    private void getCurrentConnectStaus() {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();//获取当前网路信息
        if (networkInfo!=null&&networkInfo.isConnected()){
            isConnected=true;
        }else {
            isConnected=false;
        }

    }

   //让别的类去获取当前网络的连接状态
    public boolean isConnected() {
        return isConnected;
    }
}
