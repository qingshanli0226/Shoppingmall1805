package com.example.net;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Context context = NetModule.context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);

        Request request = chain.request();
        String token = sharedPreferences.getString("token", "");
        Request newRequest = request.newBuilder().addHeader("token", token).build();


        return chain.proceed(newRequest);
    }
}
