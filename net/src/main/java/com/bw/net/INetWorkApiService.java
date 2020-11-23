package com.bw.net;

import com.bw.net.bean.HomeFragmentBean;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.RegisterBean;
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
}
