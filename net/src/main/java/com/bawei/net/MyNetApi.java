package com.bawei.net;


import com.bawei.common.view.NetConfig;
import com.bawei.net.mode.HomeBean;
import com.bawei.net.mode.LoginBean;
import com.bawei.net.mode.RegisterBean;
import com.bawei.net.mode.TagBean;
import com.bawei.net.mode.TypeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyNetApi {

    @GET(NetConfig.HOME_URL)
    Observable<HomeBean> getHomeData();

    @GET(NetConfig.SKIRT_URL)
    Observable<TypeBean> getSkirt();//小裙子

    @GET(NetConfig.TAG_URL)
    Observable<TagBean> getTag();//标签

    @GET(NetConfig.JACKET_URL)
    Observable<TypeBean> getJacket();//上衣

    @GET(NetConfig.PANTS_URL)
    Observable<TypeBean> getPants();//裤子

    @GET(NetConfig.OVERCOAT_URL)
    Observable<TypeBean> getOvercoat();//外套

    @GET(NetConfig.ACCESSORY_URL)
    Observable<TypeBean> getAccessory();//配件

    @GET(NetConfig.BAG_URL)
    Observable<TypeBean> getBag();//包包

    @GET(NetConfig.DRESS_UP_URL)
    Observable<TypeBean> getDress();//装扮

    @GET(NetConfig.HOME_PRODUCTS_URL)
    Observable<TypeBean> getProduct();//居家宅品

    @GET(NetConfig.STATIONERY_URL)
    Observable<TypeBean> getStationery();//办公文具

    @GET(NetConfig.DIGIT_URL)
    Observable<TypeBean> getDigit();//数码周边

    @GET(NetConfig.GAME_URL)
    Observable<TypeBean> getGame();//游戏专区

    @POST(NetConfig.USER_REGISTER_URL)
    @FormUrlEncoded
    Observable<RegisterBean> registerUser(@FieldMap HashMap<String, String> map);

    @POST(NetConfig.USER_LOGIN_URL)
    @FormUrlEncoded
    Observable<LoginBean> loginUser(@FieldMap HashMap<String, String> map);
}
