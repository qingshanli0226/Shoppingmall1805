package framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public
class NetContentManager {
    //application 的上下文
    private Context applicationContext;
    // 网络连接状态
    private boolean netConnetctStatus = false;
    // 连接管理
    private ConnectivityManager connectivityManager;
    // 链表 可能有多个页面须要网络监听
    private List<INetConnectListener> iNetConnectListenerList = new LinkedList<>();

    /**
     * 单例
     */

    //私有化对象
    private static NetContentManager netContentManager = null;
    //私有化构造
    private NetContentManager(){

    }
    // 对外提供对象
    public static  NetContentManager getInstance(){
        if (netContentManager == null){
            synchronized (NetContentManager.class){
                if (netContentManager==null){
                    netContentManager = new NetContentManager();
                }
            }
        }
        return netContentManager;
    }

    //初始化
    public void init(Context applicationContext){
        this.applicationContext = applicationContext;
        //获取网络的连接状态
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            netConnetctStatus = true;
        }else {
            netConnetctStatus = false;
        }


        // 注册广播去监听房钱网络连接的变化
        IntentFilter intentFilter = new IntentFilter();
        // 监听系统广播
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        // 监听
        applicationContext.registerReceiver(broadcastReceiver,intentFilter);
    }

    // 注册网络监听
    public void registerNetConnectLisenter(INetConnectListener netConnectListener){
        if (!iNetConnectListenerList.contains(netConnectListener)){
            iNetConnectListenerList.add(netConnectListener);
        }
    }

    public void unRegisterNetConnectLisenter(INetConnectListener netConnectListener){
        if (iNetConnectListenerList.contains(netConnectListener)){
            iNetConnectListenerList.remove(netConnectListener);
        }
    }
    private void notifyContontChanged(){
            for (INetConnectListener iNetConnectListener : iNetConnectListenerList){
            if (netConnetctStatus){
                Log.i("====","11111");
                iNetConnectListener.onConnected();
            }else {
                Log.i("====","22222");
                iNetConnectListener.onDisConnected();
            }
        }
    }
    // 通过广播来监听网络
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null && networkInfo.isConnected()){
                netConnetctStatus = true;
            }else {
                netConnetctStatus = false;
            }
            notifyContontChanged();
        }
    };
    // 注册监听
    public interface INetConnectListener {

        void onConnected();

        void onDisConnected();

    }
}
