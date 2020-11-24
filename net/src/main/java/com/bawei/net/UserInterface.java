package com.bawei.net;

import com.bawei.net.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserInterface {

    @GET("atguigu/json/HOME_URL.json")
    Observable<HomeBean> getHomeData();

}
