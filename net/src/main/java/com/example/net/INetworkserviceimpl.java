package com.example.net;

import com.example.net.bean.AutoLoginBean;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.MessageBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.ShopcarBean;
import com.example.net.bean.TypeBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
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
    Observable<BaseBean<LoginBean>> tokenbean(@FieldMap HashMap<String,String> map);

    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<BaseBean<String>> checkOneProductInventory(@FieldMap HashMap<String, String> params);

    @POST("addOneProduct")
    Observable<BaseBean<String>> addOneProduct(@Body RequestBody requestBody);

    @GET("getShortcartProducts")
    Observable<BaseBean<List<ShopcarBean>>> getShortcartProducts();

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

    @POST("updateAddress")
    @FormUrlEncoded
    Observable<BaseBean<String>> getMessage(@FieldMap HashMap<String,String> map);
}
