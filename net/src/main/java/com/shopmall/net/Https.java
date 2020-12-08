package com.shopmall.net;


import com.shopmall.net.bean.HomeData;
import com.shopmall.net.bean.LoginBean;
import com.shopmall.net.bean.RegisterBean;
import com.shopmall.net.bean.SortData;
import com.shopmall.net.bean.TagBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Https {
    /**
     * home页面数据请求
     */
    @GET
    Observable<HomeData> getonser(@Url String url);

    /**
     * 分类页面请求
     */
    @GET
    Observable<SortData> getsort(@Url String url);

    @GET
    Observable<TagBean> gettag(@Url String url);

    @POST
    Observable<LoginBean> getLogin(@Url String url, @QueryMap HashMap<String,String> map);

    @POST
    Observable<RegisterBean> getRegister(@Url String url,@QueryMap HashMap<String,String> map);
}
