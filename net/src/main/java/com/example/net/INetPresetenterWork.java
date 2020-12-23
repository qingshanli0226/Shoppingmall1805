package com.example.net;

import com.example.net.bean.BaseBean;
import com.example.net.bean.Biaobean;
import com.example.net.bean.ClothesBean;
import com.example.net.bean.FindPayBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.IntonVoryBean;
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
    //自动登录接口
    @POST("autoLogin")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> autologin(@FieldMap HashMap<String,String> map);
    //退出登录
    @POST("logout")
    Observable<BaseBean<String>> logotlogin();
    //绑定电话
    @POST("updatePhone")
    @FormUrlEncoded
    Observable<BaseBean<String>> updatePhone(@FieldMap HashMap<String,String> map);
    //绑定地址
    @POST("updateAddress")
    @FormUrlEncoded
    Observable<BaseBean<String>> updateAddress(@FieldMap HashMap<String,String> map);
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

    //更新服务端购物车产品的选择
    @POST("updateProductSelected")
    Observable<BaseBean<String>> updateProdictSelected(@Body RequestBody requestBody);
    //全选服务端购物车产品或者全不选
    @POST("selectAllProduct")
    Observable<BaseBean<String>> selectAllProduct(@Body RequestBody requestBody);
    //从服务端购物车删除多个产品的接口
    @POST("removeManyProduct")
    Observable<BaseBean<String>> deleteManyProduct(@Body RequestBody requestBody);
    //检 查 服 务 端 多 个 产 品 是 否 库 存 充 足
    @POST("checkInventory")
    Observable<BaseBean<List<IntonVoryBean>>> checjInventory(@Body RequestBody requestBody);
    //下单接口
    @POST("getOrderInfo")
    Observable<BaseBean<OrderInfoBean>> getOrderInfo(@Body RequestBody requestBody);
    //是否支付成功
    @POST("confirmServerPayResult")
    Observable<BaseBean<String>> confirmServerPayResult(@Body RequestBody requestBody);
    //查找代付款借口
    @GET("findForPay")
    Observable<BaseBean<List<FindPayBean>>> getFindPay();
    //查找代发货借口
    @GET("findForSend")
    Observable<BaseBean<List<FindSendBean>>> getFindSend();
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
