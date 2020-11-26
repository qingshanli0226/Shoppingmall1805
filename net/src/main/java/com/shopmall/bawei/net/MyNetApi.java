package com.shopmall.bawei.net;

import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.net.mode.TagBean;
import com.shopmall.bawei.net.mode.TypeBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyNetApi {

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
