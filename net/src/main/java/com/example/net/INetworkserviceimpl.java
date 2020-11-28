package com.example.net;

import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.Recommonde;
import com.example.net.bean.RegisterBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INetworkserviceimpl {

    @GET("atguigu/json/HOME_URL.json")
    Observable<BaseBean<Recommonde>> recommondebean();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> registerbean(@FieldMap HashMap<String,String> map);

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> loginbean(@FieldMap HashMap<String,String> map);

}
