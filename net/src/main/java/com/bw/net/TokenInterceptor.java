package com.bw.net;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Context context = NetModel.context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("1805A.xml", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");


        Request request = chain.request();
        Request newRequest = request.newBuilder().addHeader("token", token).build();


        return chain.proceed(newRequest);
    }
}
