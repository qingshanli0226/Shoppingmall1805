package com.shoppmall.common.adapter.error;

import android.accounts.NetworkErrorException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class ExceptionUtil {
    public static ErrorBean getErrorBean(String code,String message){
        ErrorBean errorBean = new ErrorBean();
        errorBean.setErrorCode(code);
        errorBean.setErrorMessage(message);
        return errorBean;
    }
    public static ErrorBean getErrorBean(Throwable e){
        ErrorBean errorBean=new ErrorBean();
        BusinessException businessException;
        if(e instanceof BusinessException){
            businessException= (BusinessException) e;
            errorBean=businessException.getErrorBean();
        }else if(e instanceof NetworkErrorException){
            errorBean.setErrorCode("1000");
            errorBean.setErrorMessage("网络错误");
        }else if(e instanceof JSONException){
            errorBean.setErrorCode("2000");
            errorBean.setErrorMessage("Json解析错误");
        }else if(e instanceof IndexOutOfBoundsException){
            errorBean.setErrorCode("warning");
            errorBean.setErrorMessage("下标越界");
        }else if(e instanceof SocketTimeoutException){
            errorBean.setErrorCode("3000");
            errorBean.setErrorMessage("连接超时");
        }else if(e instanceof ConnectException){
            errorBean.setErrorCode("4000");
            errorBean.setErrorMessage("网络连接错误");
        }else {
            errorBean.setErrorCode("20000");
            errorBean.setErrorMessage("其他错误");
        }
        return errorBean;
    }
}
