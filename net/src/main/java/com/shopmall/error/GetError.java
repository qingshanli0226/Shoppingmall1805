package com.shopmall.error;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.net.ConnectException;

public class GetError {


      public static void errormasg(Throwable e){

           if (e instanceof NetworkErrorException){
               Log.e("wk","网络错误");
           }else if (e instanceof ConnectException){
               Log.e("wk","连接超时");
           }
      }
}
