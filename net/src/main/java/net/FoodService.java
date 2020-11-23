package net;



import io.reactivex.Observable;
import mode.HomeBean;
import retrofit2.http.GET;

public
interface FoodService {
    @GET("/atguigu/json/HOME_URL.json")
    Observable<HomeBean> getfood();
}
