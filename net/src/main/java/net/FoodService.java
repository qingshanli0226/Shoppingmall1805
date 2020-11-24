package net;



import io.reactivex.Observable;
import mode.ClothesBean;
import mode.HomeBean;
import mode.javabean;
import retrofit2.http.GET;

import static view.Constants.BASE_URL_JSON2;
import static view.Constants.TAG_URL;

public
interface FoodService {
    @GET("/atguigu/json/HOME_URL.json")
    Observable<HomeBean> getfood();

    //小裙子
    @GET("/atguigu/json/SKIRT_URL.json")
    Observable<ClothesBean> getsharFood();

    //上衣
    @GET("/atguigu/json/JACKET_URL.json")
    Observable<ClothesBean> getsharFoodshang();
    //上衣
    @GET("/atguigu/json/PANTS_URL.json")
    Observable<ClothesBean> getsharFoodxiq();
    //夏装
    @GET("/atguigu/json/OVERCOAT_URL.json")
    Observable<ClothesBean> getsharFoodwai();
    //配件
    @GET("/atguigu/json/ACCESSORY_URL.json")
    Observable<ClothesBean> getsharFoodpei();

    @GET(BASE_URL_JSON2+"TAG_URL.json")
    Observable<javabean> getbiao();

}
