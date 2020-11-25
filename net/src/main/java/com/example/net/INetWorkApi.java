package com.example.net;

import com.example.net.bean.GoodsBean;
import com.example.net.bean.MainBean;
import com.example.net.bean.TagBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface INetWorkApi {
    @GET(Constants.HOME_URL)
    Observable<MainBean> loadMain();

    @GET
    Observable<GoodsBean> showGoods(@Url String url);

    @GET(Constants.TAG_URL)
    Observable<TagBean> showTag();
}
