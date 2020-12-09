package com.shopmall.bawei.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.net.mode.NetModule;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

//通过拦截器，在网络请求头上添加一些自己的东西
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Context context = NetModule.context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(UrlHelper.spName,Context.MODE_PRIVATE);

        Request request = chain.request();
        Request newRequest = request.newBuilder().addHeader("token", sharedPreferences.getString(UrlHelper.tokenName,"")).build();
        return chain.proceed(newRequest);
    }
}
