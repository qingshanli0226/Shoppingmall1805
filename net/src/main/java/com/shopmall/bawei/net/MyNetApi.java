package com.shopmall.bawei.net;

import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyNetApi {

    @GET("atguigu/json/HOME_URL.json")
    Observable<HomeBean> getHomeData();

    
}
