package com.shopmall.bawei.framework.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class NetConnectManager {
         private boolean isconnect;
         private Context context;
         private volatile static NetConnectManager netConnectManager;
         public static NetConnectManager getInstance(){
            if (null==netConnectManager){
               synchronized (NetConnectManager.class){
                if (netConnectManager==null){
                 netConnectManager=new NetConnectManager();
                }
               }
            }
            return netConnectManager;
         }

         @SuppressLint("ServiceCast")
         public void init(Context application){
             this.context=application;

          ConnectivityManager systemService = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
          if (activeNetworkInfo!=null && activeNetworkInfo.isConnected()){
                isconnect=true;
          }else {
                isconnect=false;
          }

          IntentFilter intentFilter=new IntentFilter();
          intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
          context.registerReceiver(new netconnect(),intentFilter);


         }


         private class netconnect extends BroadcastReceiver{

          @Override
          public void onReceive(Context context, Intent intent) {
            @SuppressLint("ServiceCast")
            ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
           if (activeNetworkInfo!=null && activeNetworkInfo.isConnected()){
             isconnect=true;
           }else {
             isconnect=false;
           }
           Log.e("net",""+isconnect);
           EventBus.getDefault().post(isconnect);
          }

         }

         //获取网络连接状态
         public boolean getisconnect(){
             return isconnect;
         }
}
