package com.shopmall.bawei.net;

import com.shopmall.bean.HomeData;
import com.shopmall.bean.SortData;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
}
