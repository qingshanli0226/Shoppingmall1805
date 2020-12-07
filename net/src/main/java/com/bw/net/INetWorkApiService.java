package com.bw.net;

import com.bw.net.bean.AutoLoginBean;
import com.bw.net.bean.HomeFragmentBean;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.RegisterBean;
import com.bw.net.bean.ShopCarBean;
import com.bw.net.bean.SkirtBean;
import com.bw.net.bean.TagBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INetWorkApiService {

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> login(@FieldMap HashMap<String,String> map);

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> register(@FieldMap HashMap<String,String> map);

    @POST("autoLogin")
    @FormUrlEncoded
    Observable<AutoLoginBean> autoLogin(@FieldMap HashMap<String,String> map);

    @GET(Contants.HOME_URL)
    Observable<HomeFragmentBean> getHomeData();

    @GET(Contants.TAG_URL)
    Observable<TagBean> getTag();

    @GET(Contants.SKIRT_URL)
    Observable<SkirtBean> getSkirt();


    @GET(Contants.PANTS_URL)
    Observable<SkirtBean> getPants();

    @GET(Contants.OVERCOAT_URL)
    Observable<SkirtBean> getOverCoat();

    @GET(Contants.ACCESSORY_URL)
    Observable<SkirtBean> getAccessory();

    @GET(Contants.BAG_URL)
    Observable<SkirtBean> getBag();

    @GET(Contants.DRESS_UP_URL)
    Observable<SkirtBean> getDress();

    @GET(Contants.HOME_PRODUCTS_URL)
    Observable<SkirtBean> getHomeProducts();

    @GET(Contants.STATIONERY_URL)
    Observable<SkirtBean> getStationery();

    @GET(Contants.DIGIT_URL)
    Observable<SkirtBean> getDigit();

    @GET(Contants.GAME_URL)
    Observable<SkirtBean> getGame();

    @GET(Contants.JACKET_URL)
    Observable<SkirtBean> getJacket();

    @POST("addOneProduct")
    Observable<ShopCarBean> addProduct(@Body RequestBody requestBody);

}
