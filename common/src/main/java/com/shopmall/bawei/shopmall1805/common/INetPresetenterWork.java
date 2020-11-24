package com.shopmall.bawei.shopmall1805.common;



import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface INetPresetenterWork {
    //注册 接口
    @POST("register")
    @FormUrlEncoded
    Observable<BaseBean<String>> register(@FieldMap HashMap<String, String> map);
    //登录  接口
    @POST("login")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> login(@FieldMap HashMap<String, String> map);

    //小裙子 接口
    @GET("atguigu/json/SKIRT_URL.json")
    Observable<BaseBean<ClothesBean>> skirt();

    //上衣 接口
    @GET("atguigu/json/JACKET_URL.json")
    Observable<BaseBean<ClothesBean>> jacket();

    //裤子 接口
    @GET("atguigu/json/PANTS_URL.json")
    Observable<BaseBean<ClothesBean>> pants();

    //外衣 接口
    @GET("atguigu/json/OVERCOAT_URL.json")
    Observable<BaseBean<ClothesBean>> overcoat();

    //配件 接口
    @GET("atguigu/json/ACCESSORY_URL.json")
    Observable<BaseBean<ClothesBean>> accessory();

    //包包 接口
    @GET("atguigu/json/BAG_URL.json")
    Observable<BaseBean<ClothesBean>> bag();

    //装扮 接口
    @GET("atguigu/json/DRESS_UP_URL.json")
    Observable<BaseBean<ClothesBean>> dressup();


     //居家宅品 接口
    @GET("atguigu/json/HOME_PRODUCTS_URL.json")
    Observable<BaseBean<ClothesBean>> home_products();

    //办公文具 接口
    @GET("atguigu/json/STATIONERY_URL.json")
    Observable<BaseBean<ClothesBean>> stationery();

    //数码周边 接口
    @GET("atguigu/json/DIGIT_URL.json")
    Observable<BaseBean<ClothesBean>> digit();
    //游戏专区 接口
    @GET("atguigu/json/GAME_URL.json")
    Observable<BaseBean<ClothesBean>> game();

    //主页Fragment路径
    @GET("atguigu/json/HOME_URL.json")
    Observable<HomeBean> home();
    //分类Fragment里面的标签Fragment页面数据
    @GET("TAG_URL.json")
    Observable<BaseBean<ClothesBean>> tag();
    //发现Fragment里面的新帖
    @GET("NEW_POST_URL.json")
    Observable<BaseBean<ClothesBean>> new_post();
    //发现Fragment里面的热帖
    @GET("HOT_POST_URL.json")
    Observable<BaseBean<ClothesBean>> hot_post();

}
