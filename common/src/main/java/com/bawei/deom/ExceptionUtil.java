package com.bawei.deom;

import android.accounts.NetworkErrorException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class ExceptionUtil {
    public static ErrorBen getErrorBean(Throwable e){
        ErrorBen errorBen=new ErrorBen();
        BusinessExecption businessExecption;
        if (e instanceof BusinessExecption){
            businessExecption=(BusinessExecption)e;
            errorBen=businessExecption.getErrorBean();
        }else  if(e instanceof NetworkErrorException) {
            errorBen.setErrorCode("1000");
            errorBen.setErrorMessage("网络错误");
        } else if (e instanceof JSONException) {
            errorBen.setErrorCode("2000");
            errorBen.setErrorMessage("Json解析错误");
        } else if (e instanceof IndexOutOfBoundsException) {

        } else if (e instanceof SocketTimeoutException) {
            errorBen.setErrorCode("3000");
            errorBen.setErrorMessage("连接超时错误");
        } else if (e instanceof ConnectException) {
            errorBen.setErrorCode("4000");
            errorBen.setErrorMessage("网络连接错误");
        } else {
            errorBen.setErrorCode("20000");
            errorBen.setErrorMessage("其他错误");
        }
        return  errorBen;
    }
}
