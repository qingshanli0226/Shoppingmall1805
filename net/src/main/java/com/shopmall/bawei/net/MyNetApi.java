package com.shopmall.bawei.net;

import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.net.mode.AutoLogBean;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.net.mode.InventoryBean;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.net.mode.OrderInfoBean;
import com.shopmall.bawei.net.mode.PayBean;
import com.shopmall.bawei.net.mode.ShopCarBean;
import com.shopmall.bawei.net.mode.TagBean;
import com.shopmall.bawei.net.mode.TypeBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyNetApi {

    @POST(UrlHelper.REGISTER)
    @FormUrlEncoded
    Observable<BaseBean<String>> register(@FieldMap HashMap<String,String> map);

    @POST(UrlHelper.LOGIN)
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> login(@FieldMap HashMap<String,String> map);

    @POST(UrlHelper.AUTO_LOGIN)
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> autoLogin(@Field("token") String token);

    @GET("getShortcartProducts")
    Observable<BaseBean<List<ShopCarBean>>> getShortCartProducts();

    @POST("logout")
    Observable<BaseBean<String>> logout();

    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<BaseBean<String>> checkOneProductInventory(@FieldMap HashMap<String,Integer> map);

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
    Observable<BaseBean<String>> bindPhone(@Field("phone") String phone);

    @POST("updateAddress")
    @FormUrlEncoded
    Observable<BaseBean<String>> bindAddress(@Field("address") String address);

    @POST("confirmServerPayResult")
    Observable<BaseBean<Boolean>> confirmServerPayResult(@Body RequestBody requestBody);

    @GET("findForPay")
    Observable<BaseBean<List<PayBean>>> findForPay();

    @GET(UrlHelper.HOME_URL)
    Observable<BaseBean<HomeBean>> getHomeData();

    @GET(UrlHelper.TAG_URL)
    Observable<BaseBean<List<TagBean>>> getTag();//标签

    @GET(UrlHelper.SKIRT_URL)
    Observable<BaseBean<List<TypeBean>>> getSkirt();//小裙子

    @GET(UrlHelper.JACKET_URL)
    Observable<BaseBean<List<TypeBean>>> getJacket();//上衣

    @GET(UrlHelper.PANTS_URL)
    Observable<BaseBean<List<TypeBean>>> getPants();//裤子

    @GET(UrlHelper.OVERCOAT_URL)
    Observable<BaseBean<List<TypeBean>>> getOvercoat();//外套

    @GET(UrlHelper.ACCESSORY_URL)
    Observable<BaseBean<List<TypeBean>>> getAccessory();//配件

    @GET(UrlHelper.BAG_URL)
    Observable<BaseBean<List<TypeBean>>> getBag();//包包

    @GET(UrlHelper.DRESS_UP_URL)
    Observable<BaseBean<List<TypeBean>>> getDress();//装扮

    @GET(UrlHelper.HOME_PRODUCTS_URL)
    Observable<BaseBean<List<TypeBean>>> getProduct();//居家宅品

    @GET(UrlHelper.STATIONERY_URL)
    Observable<BaseBean<List<TypeBean>>> getStationery();//办公文具

    @GET(UrlHelper.DIGIT_URL)
    Observable<BaseBean<List<TypeBean>>> getDigit();//数码周边

    @GET(UrlHelper.GAME_URL)
    Observable<BaseBean<List<TypeBean>>> getGame();//游戏专区
    
}
