package com.bawei.deom;

import java.util.HashMap;
import java.util.List;

import bean.BaseBean;
import bean.ClothesBean;
import bean.HomeBean;
import bean.LoginBean;
import bean.TAGBean;
import bean.typebean.BugBean;
import bean.typebean.DigitBean;
import bean.typebean.DressupBean;
import bean.typebean.GameBean;
import bean.typebean.HomproductsBean;
import bean.typebean.JackBean;
import bean.typebean.OverCoat;
import bean.typebean.Pants;
import bean.typebean.SkirtBean;
import bean.typebean.StationeryBean;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface UserInterface {
    //注册 接口
    @POST("register")
    @FormUrlEncoded
    Observable<BaseBean<String>> register(@FieldMap HashMap<String, String> map);
    //登录  接口
    @POST("login")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> login(@FieldMap HashMap<String, String> map);

//    //小裙子 接口
//    @GET("/atguigu/json/SKIRT_URL.json")
//    Observable<SkirtBean> skirt();
//
//    //上衣 接口
//    @GET("atguigu/json/JACKET_URL.json")
//    Observable<JackBean> jacket();
//
//    //裤子 接口
//    @GET("atguigu/json/PANTS_URL.json")
//    Observable<Pants> pants();
//
//    //外衣 接口
//    @GET("atguigu/json/OVERCOAT_URL.json")
//    Observable<OverCoat> overcoat();
//
//    //配件 接口
//    @GET("atguigu/json/ACCESSORY_URL.json")
//    Observable<BaseBean<ClothesBean>> accessory();
    @GET
    Observable<BugBean> getbug(@Url String url);
//    //包包 接口
//    @GET("atguigu/json/BAG_URL.json")
//    Observable<BugBean> bag();
//
//    //装扮 接口
//    @GET("atguigu/json/DRESS_UP_URL.json")
//    Observable<DressupBean> dressup();
//
//
//    //居家宅品 接口
//    @GET("atguigu/json/HOME_PRODUCTS_URL.json")
//    Observable<HomproductsBean> home_products();
//
//    //办公文具 接口
//    @GET("atguigu/json/STATIONERY_URL.json")
//    Observable<StationeryBean> stationery();
//
//    //数码周边 接口
//    @GET("atguigu/json/DIGIT_URL.json")
//    Observable<DigitBean> digit();
//
//    //游戏专区 接口
//    @GET("atguigu/json/GAME_URL.json")
//    Observable<GameBean> game();

    //主页Fragment路径
    @GET("atguigu/json/HOME_URL.json")
    Observable<BaseBean<HomeBean>> home();
    //分类Fragment里面的标签Fragment页面数据
    @GET("atguigu/json/TAG_URL.json")
    Observable<TAGBean> tag();
    //发现Fragment里面的新帖
    @GET("NEW_POST_URL.json")
    Observable<BaseBean<ClothesBean>> new_post();
    //发现Fragment里面的热帖
    @GET("HOT_POST_URL.json")
    Observable<BaseBean<ClothesBean>> hot_post();
}
