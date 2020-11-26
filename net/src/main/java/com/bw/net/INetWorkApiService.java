package com.bw.net;

import com.bw.net.bean.AccessoryBean;
import com.bw.net.bean.BagBean;
import com.bw.net.bean.DigitBean;
import com.bw.net.bean.DressBean;
import com.bw.net.bean.GameBean;
import com.bw.net.bean.HomeFragmentBean;
import com.bw.net.bean.HomeProductsBean;
import com.bw.net.bean.JacketBean;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.OverCoatBean;
import com.bw.net.bean.PantsBean;
import com.bw.net.bean.RegisterBean;
import com.bw.net.bean.SkirtBean;
import com.bw.net.bean.StationeryBean;
import com.bw.net.bean.TagBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INetWorkApiService {

    @POST("login")
    Observable<LoginBean> login();

    @POST("register")
    Observable<RegisterBean> register();

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

}
