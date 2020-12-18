package com.example.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitcreators {
    private  static INetPresetenterWork iNetPresetenterWork;
    public static INetPresetenterWork getiNetPresetenterWork(){
        if (iNetPresetenterWork == null){
            iNetPresetenterWork = createiNetPreseterWork();
        }
        return iNetPresetenterWork;
    }

    private static INetPresetenterWork createiNetPreseterWork() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .connectTimeout(5000, TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit build = new Retrofit.Builder()
                .baseUrl(Confing.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INetPresetenterWork iNetPresetenterWork = build.create(INetPresetenterWork.class);
        return iNetPresetenterWork;
    }



//    private static Interceptor getheard(){
//        final Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request tokens = request.newBuilder()
//                        .addHeader("token", "1")
//                        .build();
//                Response intercept = chain.proceed(tokens);
//                return intercept;
//            }
//        };
//        return interceptor;
//    }

}
