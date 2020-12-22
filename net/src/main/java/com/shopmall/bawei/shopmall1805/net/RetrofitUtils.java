package com.shopmall.bawei.shopmall1805.net;

import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
   private static INetPresetenterWork iNetPresetenterWork;
    public static INetPresetenterWork getiNetPresetenterWork() {
        if(iNetPresetenterWork == null){
            iNetPresetenterWork = createRetrofit();
        }
        return iNetPresetenterWork;
    }
    private static INetPresetenterWork createRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(ShopmallConstant.READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(ShopmallConstant.WRITE_TIME,TimeUnit.SECONDS)
                .connectTimeout(ShopmallConstant.CONNECT_TIME,TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUrl.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(INetPresetenterWork.class);
    }
}
