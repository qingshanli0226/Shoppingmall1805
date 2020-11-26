package net;



import io.reactivex.Observable;
import mode.ClothesBean;
import mode.HomeBean;
import mode.javabean;
import retrofit2.http.GET;

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
    Observable<ClothesBean> getjacket();
    //下装
    @GET("/atguigu/json/PANTS_URL.json")
    Observable<ClothesBean> getTrouser();
    //外套
    @GET("/atguigu/json/OVERCOAT_URL.json")
    Observable<ClothesBean> getCoat();
    //配件
    @GET("/atguigu/json/ACCESSORY_URL.json")
    Observable<ClothesBean> getAccessries();

    @GET(BASE_URL_JSON2+"TAG_URL.json")
    Observable<javabean> getLabel();

}
