package com.shopmall.bawei.shopmall1805.api;

import baseurl.UrlHelp;
import io.reactivex.Observable;
import com.shopmall.bawei.shopmall1805.bean.HomeBean;
import retrofit2.http.GET;

public interface FirstApi {
    //首页数据
    @GET(UrlHelp.HOME_URL)
    Observable<HomeBean>gethome();
}
