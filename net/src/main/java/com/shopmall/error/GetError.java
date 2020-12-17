package com.shopmall.error;

import android.accounts.NetworkErrorException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class GetError {

    private static String error;
    public static String getErrorBean(Throwable e){

       if(e instanceof NetworkErrorException) {
            error="1000,网络错误";
        } else if (e instanceof JSONException) {

           error="2000,Json解析错误";
        } else if (e instanceof IndexOutOfBoundsException) {

        } else if (e instanceof SocketTimeoutException) {
            error="3000,连接超时错误";
        } else if (e instanceof ConnectException) {
            error="4000,网络连接错误";
        } else {
           error="20000,"+e.getMessage();
        }
        return  error;
    }
}
