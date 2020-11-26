package com.example.net;

import com.example.net.bean.AutoLoginBean;
import com.example.net.bean.GoodsBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.MainBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.TagBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface INetWorkApi {
    @GET(Constants.HOME_URL)
    Observable<MainBean> loadMain();

    @GET
    Observable<GoodsBean> showGoods(@Url String url);

    @GET(Constants.TAG_URL)
    Observable<TagBean> showTag();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> register(@FieldMap HashMap<String,String> map);
    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> login(@FieldMap HashMap<String,String> map);
    @POST("autoLogin")
    @FormUrlEncoded
    Observable<AutoLoginBean> autoLogin(@FieldMap HashMap<String,String> map);
}
