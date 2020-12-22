package com.shopmall.bawei.shopmall1805.net;

import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;

import org.json.JSONException;

import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
    }
    @Override
    public void onNext(T t) {
    }
    @Override
    public void onError(Throwable e) {
        if(e == null){
            return;
        }
        if(e instanceof JSONException){
            onRequestError(ShopmallConstant.JSCON_ERROR_CODE,ShopmallConstant.JSON_ERROR_MESSAGE);
        }else if(e instanceof HttpException){
            onRequestError(ShopmallConstant.HTTP_ERROR_CODE,ShopmallConstant.HTTP_ERROR_MESSAGE);
        }else if(e instanceof SocketException){
            onRequestError(ShopmallConstant.SOCKET_TIMEOUT_ERROR_CODE,ShopmallConstant.SOCKET_TIMEOUT_ERROR_MESSAGE);
        }else if (e instanceof NetBusinessException){
            NetBusinessException netBusinessException = (NetBusinessException)e;
            onRequestError(netBusinessException.getErrorCode(), netBusinessException.getErrorMessage());
        }
    }
    @Override
    public void onComplete() {
    }

    public abstract void onRequestError(String errorCold,String errorMsg);
}
