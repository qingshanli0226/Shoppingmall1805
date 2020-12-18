package com.example.net;

import com.example.net.bean.BaseBean;
import com.example.net.bean.Biaobean;
import com.example.net.bean.ClothesBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShopcarBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INetPresetenterWork {
    //注册 接口
    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> register(@FieldMap HashMap<String, String> map);
    //登录  接口
    @POST("login")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> login(@FieldMap HashMap<String, String> map);

    //自动登录
    @POST("autoLogin")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> autoLogin(@FieldMap HashMap<String,String> map);

    //小裙子 接口
    @GET("atguigu/json/SKIRT_URL.json")
    Observable<ClothesBean> skirt();

    //上衣 接口
    @GET("atguigu/json/JACKET_URL.json")
    Observable<ClothesBean> jacket();

    //裤子 接口
    @GET("atguigu/json/PANTS_URL.json")
    Observable<ClothesBean> pants();

    //外衣 接口
    @GET("atguigu/json/OVERCOAT_URL.json")
    Observable<ClothesBean> overcoat();

    //配件 接口
    @GET("atguigu/json/ACCESSORY_URL.json")
    Observable<ClothesBean> accessory();

    //包包 接口
    @GET("atguigu/json/BAG_URL.json")
    Observable<ClothesBean> bag();

    //装扮 接口
    @GET("atguigu/json/DRESS_UP_URL.json")
    Observable<ClothesBean> dressup();


     //居家宅品 接口
    @GET("atguigu/json/HOME_PRODUCTS_URL.json")
    Observable<ClothesBean> home_products();

    //办公文具 接口
    @GET("atguigu/json/STATIONERY_URL.json")
    Observable<ClothesBean> stationery();

    //数码周边 接口
    @GET("atguigu/json/DIGIT_URL.json")
    Observable<ClothesBean> digit();

    //游戏专区 接口
    @GET("atguigu/json/GAME_URL.json")
    Observable<ClothesBean> game();




    //主页Fragment路径
    @GET("atguigu/json/HOME_URL.json")
    Observable<BaseBean<HomeBean>> home();
    //分类Fragment里面的标签Fragment页面数据
    @GET("atguigu/json/TAG_URL.json")
    Observable<Biaobean> tag();
    //发现Fragment里面的新帖
    @GET("NEW_POST_URL.json")
    Observable<BaseBean<ClothesBean>> new_post();
    //发现Fragment里面的热帖
    @GET("HOT_POST_URL.json")
    Observable<BaseBean<ClothesBean>> hot_post();


    @GET("getShortcartProducts")
    Observable<BaseBean<List<ShopcarBean>>> getShortcartProducts();

    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<BaseBean<String>> checkOneProductInventoryBean(@FieldMap HashMap<String,String> map);

    @POST("addOneProduct")
    Observable<BaseBean<String>> addOneProduct(@Body RequestBody requestBody);

    @POST("updateProductNum")
    Observable<BaseBean<String>> updateProductNum(@Body RequestBody requestBody);

    @POST("updateProductSelected")
    Observable<BaseBean<String>> updateProductSelected(@Body RequestBody requestBody);

    @POST("selectAllProduct")
    Observable<BaseBean<String>> selectAllProduct(@Body RequestBody requestBody);

    @POST("removeManyProduct")
    Observable<BaseBean<String>> removeManyProduct(@Body RequestBody requestBody);

    @POST("checkInventory")
    Observable<BaseBean<List<InventoryBean>>> checkInventory(@Body RequestBody requestBody);

    @POST("getOrderInfo")
    Observable<BaseBean<OrderInfoBean>> getOrderInfo(@Body RequestBody requestBody);

    @POST("updatePhone")
    @FormUrlEncoded
    Observable<BaseBean<String>> updatePhone(@FieldMap HashMap<String,String> map);

    @POST("updateAddress")
    @FormUrlEncoded
    Observable<BaseBean<String>> updateAddress(@FieldMap HashMap<String,String> map);


}
