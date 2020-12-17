package net;

import android.content.Context;
import android.content.SharedPreferences;

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
import retrofit2.http.Body;
import view.ShopmallConstant;

public
class RxjavaRetortUlis {
    private static RxjavaRetortUlis rxjavaRetortUlis =null;
    private Retrofit build;
    private Context context;
    private String token;
    public static RxjavaRetortUlis getInstance(){
        if (rxjavaRetortUlis == null){
            synchronized (RxjavaRetortUlis.class){
                if (rxjavaRetortUlis == null){
                    rxjavaRetortUlis = new RxjavaRetortUlis();
                }
            }
        }
        
        return rxjavaRetortUlis;
    }

    private RxjavaRetortUlis() {
        InitData();
    }

    public void init(Context application){
        this.context = application;
        token = context.getSharedPreferences(ShopmallConstant.spName, context.MODE_PRIVATE).getString(ShopmallConstant.tokenName, null);
    }

    private void InitData() {
         build = new Retrofit.Builder()
                .baseUrl("http://49.233.0.68:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(CreateClient())
                .build();

    }

    private OkHttpClient CreateClient() {
        OkHttpClient build = new OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(CreateTokenIntereptor())
                .addInterceptor(createToken())
                .build();

        return build;

    }

    private Interceptor createToken() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request token = request.newBuilder()
                        .addHeader("token", context.getSharedPreferences(ShopmallConstant.spName, context.MODE_PRIVATE).getString(ShopmallConstant.tokenName, null)).build();
                return chain.proceed(token);
            }
        };
        return interceptor;
    }

    private Interceptor CreateTokenIntereptor() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor httpLoggingInterceptor1 = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return httpLoggingInterceptor1;
    }


    public <T> T create(Class<T> server){
        return build.create(server);
    }
}
