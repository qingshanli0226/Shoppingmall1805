package com.example.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRetrofitManager {

    private static HttpRetrofitManager httpRetrofitManager;

    public static HttpRetrofitManager getHttpRetrofitManager() {
        if (httpRetrofitManager ==null){
            httpRetrofitManager = new HttpRetrofitManager();
        }
        return httpRetrofitManager;
    }

    private Retrofit retrofit;

    public Retrofit getRetrofit(String path) {
        createRetrofit(path);
        return retrofit;
    }

    private void createRetrofit(String path) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit build = new Retrofit.Builder()
                .baseUrl(path)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


            retrofit = build;
    }



}
