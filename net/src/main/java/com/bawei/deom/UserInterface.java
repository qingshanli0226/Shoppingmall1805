package com.bawei.deom;

import java.util.HashMap;

import bean.BaseBean;
import bean.ClothesBean;
import bean.GetOrderInfo;
import bean.HomeBean;
import bean.InventoryBean;
import bean.LoginBean;
import bean.RegisterBean;
import bean.Shoppingcartproducts;
import bean.TAGBean;
import bean.typebean.BugBean;
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
    Observable<LoginBean>autoLogin(@FieldMap HashMap<String,String> map);
//    getShortcartProducts
//    updateProductNum
    @GET
    Observable<BugBean> getbug(@Url String url);

    @GET("/getShortcartProducts")
    Observable<Shoppingcartproducts>getShortcartProducts();
//    addOneProduct
    @POST("/addOneProduct")

     Observable<BaseBean<String>>addOneProduct(@Body RequestBody requestBody);

    @POST("/updateProductNum")//更新服务端购物车产品的数量的接口
    Observable<BaseBean<String>>updateProductNum(@Body RequestBody requestBody);

    @POST("/checkOneProductInventory")
    @FormUrlEncoded
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

    //全选或者全不选
    @POST("/selectAllProduct")
    Observable<BaseBean<String>>selectAllProduct(@Body RequestBody body);
    @POST("/updateProductSelected")//更新服务端购物车产品的选择
    Observable<BaseBean<String>>updateProductSelected(@Body RequestBody body);

    @POST("/removeOneProduct")//从服务端购物车删除一个产品的接口
    Observable<BaseBean<String>>removeOneProduct(@Body RequestBody body);
    //从服务端购物车删除一个产品的接口
    @POST("/removeManyProduct")
    Observable<BaseBean<String>>removeManyProduct(@Body RequestBody body);
    //检查服务端多个产品是否库存充足
    @POST("/checkInventory")
    Observable<BaseBean<InventoryBean>>checkInventory(@Body RequestBody body);
    //向服务端下订单接口
    @POST("/getOrderInfo")
    Observable<BaseBean<GetOrderInfo>>getOrderInfo(@Body RequestBody body);


}
