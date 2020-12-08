package com.example.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Context context = NetModel.context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(Confing.spName, Context.MODE_PRIVATE);
        Request request = chain.request();
//        Log.e("###",""+sharedPreferences.getString("token", ""));
        Request newbuilder = request.newBuilder().addHeader(Confing.token, sharedPreferences.getString(Confing.token, "")).build();
        return chain.proceed(newbuilder);
    }
}
