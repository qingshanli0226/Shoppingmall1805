package com.bawei.deom;

import java.util.HashMap;
import java.util.List;

import bean.AutoLoginBeen;
import bean.BaseBean;
import bean.ClothesBean;
import bean.HomeBean;
import bean.LoginBean;
import bean.RegisterBean;
import bean.Shoppingcartproducts;
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
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface UserInterface {
    //注册 接口
    @POST("/register")
    @FormUrlEncoded
    Observable<RegisterBean> register(@FieldMap HashMap<String, String> map);
    //登录  接口
    @POST("/login")
    @FormUrlEncoded
    Observable<LoginBean> login(@FieldMap HashMap<String, String> map);
    @POST("/autoLogin")
    @FormUrlEncoded
    Observable<AutoLoginBeen>autoLogin(@FieldMap HashMap<String,String> map);
//    getShortcartProducts
//    updateProductNum
    @GET
    Observable<BugBean> getbug(@Url String url);

    @GET("/getShortcartProducts")
    Observable<Shoppingcartproducts>getShortcartProducts();
//    addOneProduct
    @POST("/addOneProduct")
     Observable<BaseBean<String>>addOneProduct(@Body RequestBody requestBody);

    @POST("/updateProductNum")
    Observable<BaseBean<String>>updateProductNum(@Body RequestBody requestBody);

    @POST("/checkOneProductInventory")
    Observable<BaseBean<String>>checkOneProductInventory(@FieldMap HashMap<String,String> map);


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
