package com.bw.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;

public class NetContentManager {

    private static NetContentManager instance;
    private Context context;
    private boolean isConnected;


    private NetContentManager(){

    }

    public static NetContentManager getInstance(){
        if (instance == null){
            instance = new NetContentManager();
        }
        return instance;
    }


    public void init(Context context){
        this.context = context;
        getCurrentConnectState();

        //注册广播 监听网络连接情况
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(connectReceiver,intentFilter);
    }

    private BroadcastReceiver connectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getCurrentConnectState();
                notifyConnectChanged();
            }
        }


    };

    private void getCurrentConnectState() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            isConnected = true;
        }else {
            isConnected = false;
        }
    }


    private void notifyConnectChanged() {
        EventBus.getDefault().post(isConnected);
    }

    public boolean isConnected() {
        return isConnected;
    }
}
