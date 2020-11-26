package com.shopmall.bawei.shopmall1805.api;

import baseurl.SkirstBean;
import baseurl.UrlHelp;
import io.reactivex.Observable;
import baseurl.HomeBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface FirstApi {
    //首页数据
    @GET(UrlHelp.HOME_URL)
    Observable<HomeBean>gethome();

    //分类服饰
    @GET()
    Observable<SkirstBean>getskirt(@Url String url);
}
