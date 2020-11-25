package com.shopmall.bawei.net;

import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.net.mode.TagBean;
import com.shopmall.bawei.net.mode.TypeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyNetApi {

    @GET(UrlHelper.HOME_URL)
    Observable<HomeBean> getHomeData();

    @GET(UrlHelper.SKIRT_URL)
    Observable<TypeBean> getSkirt();//小裙子

    @GET(UrlHelper.TAG_URL)
    Observable<TagBean> getTag();//标签

    @GET(UrlHelper.JACKET_URL)
    Observable<TypeBean> getJacket();//上衣

    @GET(UrlHelper.PANTS_URL)
    Observable<TypeBean> getPants();//裤子

    @GET(UrlHelper.OVERCOAT_URL)
    Observable<TypeBean> getOvercoat();//外套

    @GET(UrlHelper.ACCESSORY_URL)
    Observable<TypeBean> getAccessory();//配件

    @GET(UrlHelper.BAG_URL)
    Observable<TypeBean> getBag();//包包

    @GET(UrlHelper.DRESS_UP_URL)
    Observable<TypeBean> getDress();//装扮

    @GET(UrlHelper.HOME_PRODUCTS_URL)
    Observable<TypeBean> getProduct();//居家宅品

    @GET(UrlHelper.STATIONERY_URL)
    Observable<TypeBean> getStationery();//办公文具

    @GET(UrlHelper.DIGIT_URL)
    Observable<TypeBean> getDigit();//数码周边

    @GET(UrlHelper.GAME_URL)
    Observable<TypeBean> getGame();//游戏专区
    
}
