package com.shopmall.bawei.net;

import com.shopmall.bean.HomeData;
import com.shopmall.bean.Loginbean;
import com.shopmall.bean.Registbean;
import com.shopmall.bean.SortData;
import com.shopmall.bean.TagData;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Https {
    /**
     * home页面数据请求
     * @param url
     * @return
     */
    @GET
    Observable<HomeData> getonser(@Url String url);

    /**
     * 分类页面请求
     */
    @GET
    Observable<SortData> getsort(@Url String url);

    /**
     * 标签数据请求observeale
     * @param url
     * @return
     */
    @GET
    Observable<TagData> getTag(@Url String url);

    /**
     * 登陆
     * @param url
     * @param map
     * @return
     */
    @POST
    Observable<Loginbean> getlogin(@Url String url, HashMap<String,String> map);


    /**
     * 注册
     * @param url
     * @param map
     * @return
     */
    @POST
    Observable<Registbean> getregist(@Url String url, HashMap<String,String> map);


}
