package com.example.net;

import com.example.net.bean.BaseBean;
import com.example.net.bean.Biaobean;
import com.example.net.bean.ClothesBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
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
    //自动登录接口
    @POST("autoLogin")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> autologin(@FieldMap HashMap<String,String> map);
    //检查服务端一个产品库存情况的接口
    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<BaseBean<String>> checkoneproductInventory(@FieldMap HashMap<String,String> map);

    //向服务端购物车添加一个产品的接口
    @POST("addOneProduct")
    Observable<BaseBean<String>> addOneProduct(@Body RequestBody requestBody);

    //获取服务端购物车产品信息的接口
     @GET("getShortcartProducts")
     Observable<BaseBean<List<ShopcarBean>>> getShortcartProducts();

    //更新服务端购物车产品的数量的接口
    @POST("updateProductNum")
    Observable<BaseBean<String>> updateProductNum(@Body RequestBody requestBody);

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

}
