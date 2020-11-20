package com.shopmall.bawei.net;

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

public class HttpsFactory {

      private HttpsFactory httpsFactory=null;

      public HttpsFactory getHttpsFactory(){
          if (httpsFactory==null){
              httpsFactory=new HttpsFactory();
          }
            return httpsFactory;
      }

      private HttpsFactory(){
          ctreateRetorfit();
      }
     private Retrofit retrofit;

    /**
     * 创建Retrofit
     */
    private void ctreateRetorfit() {

        retrofit=new Retrofit.Builder()
                 .baseUrl("")
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createclient())
                .build();
    }

    /**
     * 创建客户端
     * @return
     */
    private OkHttpClient createclient() {
        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addNetworkInterceptor(createhttplogging())
                .addInterceptor(createceptor())
                .build();
        return build;
    }

    /**
     * 添加token
     * @return
     */
    private Interceptor createceptor() {
          Interceptor interceptor=new Interceptor() {
              @Override
              public Response intercept(Chain chain) throws IOException {
                  Request request = chain.request();
                  request.newBuilder()
                          .addHeader("","")
                          .build();

                  Response proceed = chain.proceed(request);
                  return proceed;
              }
          };

          return interceptor;
    }

    /**
     * 网络拦截
     * @return
     */
    private Interceptor createhttplogging() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return httpLoggingInterceptor;
    }


    public <T> T getinstance(Class<T> service){
          return retrofit.create(service);
    }
}
