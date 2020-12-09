package com.bawei.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.bawei.common.view.NetConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Context context = NetModule.context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(NetConfig.spName, Context.MODE_PRIVATE);

        Request request = chain.request();
        Request token = request.newBuilder().addHeader("token", sharedPreferences.getString(NetConfig.tokenName, "")).build();
        return chain.proceed(token);
    }
}
