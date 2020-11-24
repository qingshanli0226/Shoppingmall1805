package com.example.net;

import com.example.net.bean.GoodsBean;
import com.example.net.bean.MainBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface INetWorkApi {
    @GET(Constants.BASE_JSON+"HOME_URL.json")
    Observable<MainBean> loadMain();

    @GET
    Observable<GoodsBean> showGoods(@Url String url);
}
