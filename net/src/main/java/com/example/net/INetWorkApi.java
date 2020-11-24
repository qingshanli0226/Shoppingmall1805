package com.example.net;

import com.example.net.bean.MainBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface INetWorkApi {
    @GET(Constants.BASE_URL_JSON+"HOME_URL.json")
    Observable<MainBean> loadMain();

}
