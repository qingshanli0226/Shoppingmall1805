package com.bawei.net;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserInterface {

    @GET("getRecommend")
    Observable<RecommendBean> getRecommend();

}
