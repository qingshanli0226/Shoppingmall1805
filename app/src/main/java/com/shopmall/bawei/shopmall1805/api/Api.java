package com.shopmall.bawei.shopmall1805.api;

import baseurl.SkirstBean;
import baseurl.TagBean;
import baseurl.UrlHelp;
import io.reactivex.Observable;
import baseurl.HomeBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    //首页数据
    @GET(UrlHelp.HOME_URL)
    Observable<HomeBean>gethome();

    //分类服饰
    @GET()
    Observable<SkirstBean>getskirt(@Url String url);

    //v标签数据
    @GET(UrlHelp.TAG_URL)
    Observable<TagBean>gettag();
}
