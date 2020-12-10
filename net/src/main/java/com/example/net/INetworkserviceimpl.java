package com.example.net;

import com.example.net.bean.AutoLoginBean;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.TypeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INetworkserviceimpl {

    @GET("atguigu/json/HOME_URL.json")
    Observable<BaseBean<HomeBean>> recommondebean();

    @GET("atguigu/json/SKIRT_URL.json")
    Observable<TypeBean> typebean();

    @POST("register")
    @FormUrlEncoded
    Observable<BaseBean<RegisterBean>> registerbean(@FieldMap HashMap<String,String> map);

    @POST("login")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> loginbean(@FieldMap HashMap<String,String> map);


    @POST("autoLogin")
    @FormUrlEncoded
    Observable<AutoLoginBean> tokenbean(@FieldMap HashMap<String,String> map);


}
