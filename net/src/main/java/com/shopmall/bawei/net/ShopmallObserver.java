package com.shopmall.bawei.net;

import android.util.Log;

import com.shopmall.bawei.common.ShopmallConstant;

import org.json.JSONException;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

//自定义一个observer，让presenter实现类更清晰，干净
public abstract class ShopmallObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable e) {//该函数可以扩展处理各种类型的错误
        Log.d("LQS error message-----", e.getMessage());
        if (e instanceof JSONException) {
            onRequestError(ShopmallConstant.JSCON_ERROR_CODE, ShopmallConstant.JSON_ERROR_MESSAGE);
        } else if (e instanceof HttpException) {
            onRequestError(ShopmallConstant.HTTP_ERROR_CODE, ShopmallConstant.HTTP_ERROR_MESSAGE);
        } else if (e instanceof SocketTimeoutException) {
            onRequestError(ShopmallConstant.SOCKET_TIMEOUT_ERROR_CODE, ShopmallConstant.SOCKET_TIMEOUT_ERROR_MESSAGE);
        } else if (e instanceof NetBusinessException) {
            NetBusinessException netBusinessException = (NetBusinessException)e;
            onRequestError(netBusinessException.getErrorCode(), netBusinessException.getErrorMessage());
        } else if (e instanceof SecurityException) {

        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onRequestError(String errorCode, String errorMessage);
}
