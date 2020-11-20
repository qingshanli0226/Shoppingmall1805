package com.shopmall.bawei.shopmall1805.net;

import com.shopmall.bawei.shopmall1805.common.AllParameter;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public RetrofitUtils() { }
    private static volatile RetrofitUtils retrofitUtils;
    public static RetrofitUtils getInstance() {
        if(retrofitUtils == null){
            synchronized (RetrofitUtils.class){
                if(retrofitUtils == null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }
    public static Retrofit retrofit;
    public static Retrofit getRetrofit(String head) {
        if(retrofit == null){
            createRetrofit(head);
        }
        return retrofit;
    }
    private static void createRetrofit(String head) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(head)
                .client(createHttp())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit = build;
    }
    private static OkHttpClient createHttp() {
        OkHttpClient build = new OkHttpClient.Builder()
                .readTimeout(AllParameter.READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(AllParameter.WRITE_TIME,TimeUnit.SECONDS)
                .connectTimeout(AllParameter.CONNECT_TIME,TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        return build;
    }
    private static Interceptor createInterceptor() {
        return null;
    }
}
