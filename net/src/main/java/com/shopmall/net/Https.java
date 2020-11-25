package com.shopmall.net;


import com.shopmall.net.bean.HomeData;
import com.shopmall.net.bean.SortData;
import com.shopmall.net.bean.TagBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
}
