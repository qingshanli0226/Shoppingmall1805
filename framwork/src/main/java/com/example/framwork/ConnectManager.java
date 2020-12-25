package com.example.framwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;

public class ConnectManager  {

    private static ConnectManager connectManager;
    private boolean isConnect;
    private Context context;

    public static ConnectManager getInstance(){
        if (connectManager==null){
             connectManager=new ConnectManager();
        }
        return connectManager;
    }

    public void onCheckConnectChange(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()){
            isConnect=true;
        }else {
            isConnect=false;
        }
    }

    public void init(Context application){
       context=application;
        context.registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                    onCheckConnectChange();
                    notifyConnectChange();
                }
        }
    };

    private void notifyConnectChange() {
        EventBus.getDefault().post(isConnect);
    }


    public boolean isConnected(){
        return isConnect;
    }
}
