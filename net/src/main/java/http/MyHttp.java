package http;

import com.example.common2.UrlHelp;

import java.util.concurrent.TimeUnit;



import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyHttp {

    private static  ShopmallApiService shopmallApiService;


    public static  ShopmallApiService getShopmallApiService(){
        if (shopmallApiService==null){
            shopmallApiService= retrofit();
        }
        return shopmallApiService;
    }
    public static ShopmallApiService retrofit(){
        //  打印日志,添加请求头
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //请求时间
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                //打印日志
//                .addInterceptor(new TokenInterceptor())
                .addInterceptor(httpLoggingInterceptor);

        Retrofit build = new Retrofit.Builder()
                .baseUrl(UrlHelp.BASE)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return build.create(ShopmallApiService.class);
    }



}
