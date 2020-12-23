package com.example.net;

import com.example.net.bean.AddProductBean;
import com.example.net.bean.AutoLoginBean;
import com.example.net.bean.CheckInventoryBean;
import com.example.net.bean.ConfirmServerPayResultBean;
import com.example.net.bean.FindForPayBean;
import com.example.net.bean.FindForSendBean;
import com.example.net.bean.GetOrderInfoBean;
import com.example.net.bean.GoodsBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.MainBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.RemoveManyProductBean;
import com.example.net.bean.SelectAllBean;
import com.example.net.bean.ShopCarBean;
import com.example.net.bean.TagBean;
import com.example.net.bean.UpDateAddressBean;
import com.example.net.bean.UpdatePhoneBean;
import com.example.net.bean.UpdateProductNumBean;
import com.example.net.bean.UpdateProductSelectedBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface INetWorkApi {
    @GET(Constants.HOME_URL)
    Observable<MainBean> loadMain();
    @GET
    Observable<GoodsBean> showGoods(@Url String url);
    @GET(Constants.TAG_URL)
    Observable<TagBean> showTag();
    @GET("getShortcartProducts")
    Observable<ShopCarBean> getShopCar();
    @GET("findForSend")
    Observable<FindForSendBean> findForSend();
    @GET("findForPay")
    Observable<FindForPayBean> findForPay();
    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> register(@FieldMap HashMap<String,String> map);
    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> login(@FieldMap HashMap<String,String> map);
    @POST("autoLogin")
    @FormUrlEncoded
    Observable<AutoLoginBean> autoLogin(@FieldMap HashMap<String,String> map);
    @POST("addOneProduct")
    Observable<AddProductBean> addOneProduct(@Body RequestBody body);
    @POST("removeManyProduct")
    Observable<RemoveManyProductBean> removeManyProduct(@Body RequestBody body);
    @POST("updateProductNum")
    Observable<UpdateProductNumBean> updateProductNum(@Body RequestBody body);
    @POST("updateProductSelected")
    Observable<UpdateProductSelectedBean> updateProductSelected(@Body RequestBody body);
    @POST("selectAllProduct")
    Observable<SelectAllBean> selectAllProduct(@Body RequestBody body);
    @POST("updatePhone")
    @FormUrlEncoded
    Observable<UpdatePhoneBean> updatePhone(@FieldMap HashMap<String,String> map);
    @POST("updateAddress")
    @FormUrlEncoded
    Observable<UpDateAddressBean> updateAddress(@FieldMap HashMap<String,String> map);
    @POST("checkInventory")
    Observable<CheckInventoryBean> checkInventory(@Body RequestBody body);
    @POST("getOrderInfo")
    Observable<GetOrderInfoBean> getOrderInfo(@Body RequestBody body);
    @POST("confirmServerPayResult")
    Observable<ConfirmServerPayResultBean> confirmServerPayResult(@Body RequestBody body);

}
