package com.example.net;

import com.example.common.ShopMallContants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetorfitCreate {
    public static INetworkserviceimpl iNetworkserviceimpl;


    public static INetworkserviceimpl getiNetworkserviceimpl(){
        if (iNetworkserviceimpl==null){
            iNetworkserviceimpl=createInetworkserviceimpl();
        }
        return iNetworkserviceimpl;
    }

    private static INetworkserviceimpl createInetworkserviceimpl() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(ShopMallContants.Base_Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        INetworkserviceimpl iNetworkserviceimpl = retrofit.create(INetworkserviceimpl.class);
        return iNetworkserviceimpl;
    }
}
