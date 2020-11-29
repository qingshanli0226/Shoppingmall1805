package com.bawei.net;

import android.util.Log;

import com.bawei.common.view.NetConfig;

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
    public void onError(Throwable e) {
        Log.d("LQS error message-----", e.getMessage());
        if (e instanceof JSONException) {
            onRequestError(NetConfig.JSCON_ERROR_CODE, NetConfig.JSON_ERROR_MESSAGE);
        } else if (e instanceof HttpException) {
            onRequestError(NetConfig.HTTP_ERROR_CODE, NetConfig.HTTP_ERROR_MESSAGE);
        } else if (e instanceof SocketTimeoutException) {
            onRequestError(NetConfig.SOCKET_TIMEOUT_ERROR_CODE, NetConfig.SOCKET_TIMEOUT_ERROR_MESSAGE);
        } else if (e instanceof NetBusinessException) {
            NetBusinessException netBusinessException = (NetBusinessException) e;
            onRequestError(netBusinessException.getErrorCode(), netBusinessException.getErrorMessage());
        } else if (e instanceof SecurityException) {

        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onRequestError(String errorCode, String errorMessage);
}
