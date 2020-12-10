package com.example.net;


import com.example.common.ShopMallContants;

import org.json.JSONException;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class ShopMallObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }
    @Override
    public abstract void onNext(T t);
    @Override
    public void onError(Throwable e) {
            if (e instanceof JSONException){
                    onRequestError(ShopMallContants.JSON_ERROR_CODE,ShopMallContants.JSON_ERROR_MESSAGE);
            }else if (e instanceof HttpException){
                onRequestError(ShopMallContants.HTTP_ERROR_CODE,ShopMallContants.HTTP_ERROR_MESSAGE);
            }else if (e instanceof SocketTimeoutException){
                onRequestError(ShopMallContants.SOCKET_TIME_ERROR_CODE,ShopMallContants.SOCKET_TIME_ERROR_MESSAGE);
            }else if (e instanceof NetBusinessException){
                NetBusinessException netBusinessException= (NetBusinessException) e;
                onRequestError(netBusinessException.getErrorCode(),netBusinessException.getErrorMessage());
            }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onRequestError(String errorCode,String errorMessage);
}
