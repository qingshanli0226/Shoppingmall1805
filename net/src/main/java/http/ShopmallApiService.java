package http;




import com.example.common2.GetShopCarBean;
import com.example.common2.HomeBean;
import com.example.common2.LoginBean;
import com.example.common2.RegisterBean;
import com.example.common2.SkirstBean;
import com.example.common2.TagBean;
import com.example.common2.UrlHelp;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

//认为是IMode
public interface ShopmallApiService {

    //首页数据
    @GET(UrlHelp.HOME_URL)
    Observable<BaseBean<HomeBean>>gethome();

    //分类服饰
    @GET()
    Observable<BaseBean<SkirstBean>>getskirt(@Url String url);

    //v标签数据
    @GET(UrlHelp.TAG_URL)
    Observable<TagBean>gettag();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> register(@FieldMap HashMap<String, String> params);

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> login(@FieldMap HashMap<String, String> params);

    @POST("autoLogin")
    @FormUrlEncoded
    Observable<LoginBean> autologin(@FieldMap HashMap<String, String> params);


    @POST("addOneProduct")
    Observable<BaseBean<String>> addOneProduct(@Body RequestBody requestBody);

    @GET("getShortcartProducts")
    Observable<BaseBean<List<GetShopCarBean>>> getShortcartProducts();




    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<BaseBean<String>> checkOneProductInventory(@FieldMap HashMap<String, String> params);


    @POST("updateProductNum")
    Observable<BaseBean<String>> updateProductNum(@Body RequestBody requestBody);

    @POST("updateProductSelected")
    Observable<BaseBean<String>> updateProductSelected(@Body RequestBody requestBody);

    @POST("selectAllProduct")
    Observable<BaseBean<String>> selectAllProduct(@Body RequestBody requestBody);

    @POST("removeManyProduct")
    Observable<BaseBean<String>> removeManyProduct(@Body RequestBody requestBody);

    @POST("checkInventory")
    Observable<BaseBean<List<InventoryBean>>> checkInventory(@Body RequestBody requestBody);

    @POST("getOrderInfo")
    Observable<BaseBean<OrderInfoBean>> getOrderInfo(@Body RequestBody requestBody);
}
