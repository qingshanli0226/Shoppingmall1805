package http;

import java.util.concurrent.TimeUnit;

import baseurl.UrlHelp;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyHttp {

    private static volatile MyHttp instance;
    public static synchronized MyHttp getInstance(){
        if (instance==null){
            instance=new MyHttp();
        }
        return instance;
    }
    public Retrofit retrofit(){
        //  打印日志,添加请求头
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //请求时间
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                //打印日志
                .addInterceptor(httpLoggingInterceptor);
        Retrofit.Builder builder1 = new Retrofit.Builder();
        builder1
                .baseUrl(UrlHelp.BASE)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return builder1.build();
    }



}
