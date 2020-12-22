package com.shopmall.bawei.shopmall1805.net;



import com.shopmall.bawei.shopmall1805.net.entity.BaseBean;
import com.shopmall.bawei.shopmall1805.net.entity.ClassifyTagEntity;
import com.shopmall.bawei.shopmall1805.net.entity.ClothesBean;
import com.shopmall.bawei.shopmall1805.net.entity.HomeBean;
import com.shopmall.bawei.shopmall1805.net.entity.InventoryBean;
import com.shopmall.bawei.shopmall1805.net.entity.LoginBean;
import com.shopmall.bawei.shopmall1805.net.entity.OrderInfoBean;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface INetPresetenterWork {
    //注册 接口
    @POST("register")
    @FormUrlEncoded
    Observable<BaseBean<String>> register(@FieldMap HashMap<String, String> map);
    //登录  接口
    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> login(@FieldMap HashMap<String, String> map);

    //自动登录
    @POST("autoLogin")
    @FormUrlEncoded
    Observable<LoginBean> autoLogin(@FieldMap HashMap<String, String> params);

    //检查商品
    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<BaseBean<String>> checkOneProductInventory(@FieldMap HashMap<String, String> params);
    //添加一个商品
    @POST("addOneProduct")
    Observable<BaseBean<String>> addOneProduct(@Body RequestBody requestBody);//添加一个商品


    @POST("updatePhone")
    Observable<BaseBean<String>> upDataPhoneIn(@QueryMap HashMap<String,String> map);//更新手机号


    @POST("updateAddress")
    Observable<BaseBean<String>> upDataAddressIn(@QueryMap HashMap<String,String> map);//更新地址

    @POST("getOrderInfo")
    Observable<BaseBean<OrderInfoBean>> getOrderInfo(@Body RequestBody requestBody);//向服务端下订单接口


    @GET("getShortcartProducts")
    Observable<BaseBean<List<ShopcarBean>>> getShortcartProducts();//获取数据接口

    @POST("updateProductSelected")
    Observable<BaseBean<String>> updateProductSelected(@Body RequestBody requestBody);//更新选中状态接口

    @POST("updateProductNum")
    Observable<BaseBean<String>> updateProductNum(@Body RequestBody requestBody);//更新数量接口

    @POST("selectAllProduct")
    Observable<BaseBean<String>> selectAllProduct(@Body RequestBody requestBody);//全选状态接口


    @POST("removeManyProduct")
    Observable<BaseBean<String>> removeManyProduct(@Body RequestBody requestBody);
    //小裙子 接口
    @GET()
    Observable<ClothesBean> skirt(@Url String url);

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
    @GET("atguigu/json/TAG_URL.json")
    Observable<ClassifyTagEntity> tag();
    //发现Fragment里面的新帖
    @GET("NEW_POST_URL.json")
    Observable<BaseBean<ClothesBean>> new_post();
    //发现Fragment里面的热帖
    @GET("HOT_POST_URL.json")
    Observable<BaseBean<ClothesBean>> hot_post();

}
