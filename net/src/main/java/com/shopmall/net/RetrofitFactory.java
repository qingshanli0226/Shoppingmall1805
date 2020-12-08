package com.shopmall.net;

import com.shopmall.net.manager.ShopUserManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static volatile RetrofitFactory instance = null;
    private RetrofitFactory(){
        initRetrofit();
    }
    public static RetrofitFactory getInstance(){
        if (null == instance){
            synchronized (RetrofitFactory.class){
                if (null == instance){
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }

    private Retrofit retrofit;

    private void initRetrofit() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(" http://49.233.0.68:8080")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                //.addInterceptor(createTokenAndDeviceCodeInterceptor())
                .addNetworkInterceptor(createNetWorkInterceptor())
                .build();
        return client;
    }

    private Interceptor createNetWorkInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private Interceptor createTokenAndDeviceCodeInterceptor() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        .addHeader("token", ShopUserManager.getInstance().getToken())
                        .build();
                Response proceed = chain.proceed(newRequest);
                return proceed;
            }
        };
        return interceptor;
    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
