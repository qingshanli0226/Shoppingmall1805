package com.bawei.deom;

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

public  class ClassInterface {
    public static UserInterface userInterface;

    public static UserInterface getUserInterface(){
        if (userInterface==null){
            userInterface=getUserface();
        }
        return userInterface;
    }

    private static UserInterface getUserface() {
        OkHttpClient build = new OkHttpClient.Builder()
                .writeTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
//                .addInterceptor(getheard(""))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUser.BASE_URL)
                .client(build)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        UserInterface userInterface = retrofit.create(UserInterface.class);

        return userInterface;
    }



    private static Interceptor getheard(final String token) {
         Interceptor interceptor=new Interceptor() {
             @Override
             public Response intercept(Chain chain) throws IOException {
                 Request request = chain.request();
                 Request tokens=request.newBuilder()
                         .addHeader("token",token)
                         .build();
                 return null;
             }
         };
         return  interceptor;
    }
}
