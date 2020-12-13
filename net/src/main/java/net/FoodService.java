package net;



import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import mode.BaseBean;
import mode.ClothesBean;
import mode.HomeBean;
import mode.InventoryBean;
import mode.LableBean;
import mode.LoginBean;
import mode.OrderInfoBean;
import mode.ReginsterAndLogin;
import mode.RegisterBean;
import mode.ShopcarBean;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static view.Constants.BASE_URL_JSON2;

public
interface FoodService {


    @GET("/atguigu/json/HOME_URL.json")
    Observable<HomeBean> getHome();

    //小裙子
    @GET("/atguigu/json/SKIRT_URL.json")
    Observable<ClothesBean> getSkirt();

    //上衣
    @GET("/atguigu/json/JACKET_URL.json")

    Observable<ClothesBean> getJacket();
    //裤子
    @GET("/atguigu/json/PANTS_URL.json")
    Observable<ClothesBean> getTrouser();
    //外套
    @GET("/atguigu/json/OVERCOAT_URL.json")
    Observable<ClothesBean> getCoat();
    //配件
    @GET("/atguigu/json/ACCESSORY_URL.json")

    Observable<ClothesBean> getAccessories();

    //包
    @GET("/atguigu/json/BAG_URL.json")
    Observable<ClothesBean> getBagUrl();

    //装扮
    @GET("/atguigu/json/DRESS_UP_URL.json")
    Observable<ClothesBean> getDressUpUrl();

    //居家宅品
    @GET("/atguigu/json/HOME_PRODUCTS_URL.json")
    Observable<ClothesBean> getAreLife();

    //办公文具
    @GET("/atguigu/json/STATIONERY_URL.json")
    Observable<ClothesBean> getOfficeSupplies ();

    //数码周边
    @GET("/atguigu/json/DIGIT_URL.json")
    Observable<ClothesBean> getNumericalCode();

    //游戏专区
    @GET("/atguigu/json/ACCESSORY_URL.json")
    Observable<ClothesBean> getTheGameZone();
    @GET(BASE_URL_JSON2+"TAG_URL.json")

    Observable<LableBean> getLable();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> goToRegister(@FieldMap HashMap<String,String> register);

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> goToLogin(@FieldMap HashMap<String,String> login);

    @GET("getShortcartProducts")
    Observable<BaseBean<List<ShopcarBean>>> getShortcartProducts();

    @POST("autoLogin")
    @FormUrlEncoded
    Observable<LoginBean> autoLogin(@FieldMap HashMap<String, String> params);

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
}
