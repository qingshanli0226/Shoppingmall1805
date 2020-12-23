package com.shopmall.bawei.net;

import com.shopmall.bean.Checkinven;
import com.shopmall.bean.HomeData;
import com.shopmall.bean.Loginbean;
import com.shopmall.bean.OrderBean;
import com.shopmall.bean.OrderPaybean;
import com.shopmall.bean.Registbean;
import com.shopmall.bean.ShopcarBean;
import com.shopmall.bean.SortData;
import com.shopmall.bean.TagData;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
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

    /**
     * 标签数据请求observeale
     * @param url
     * @return
     */
    @GET
    Observable<TagData> getTag(@Url String url);

    /**
     * 登陆
     * @param url
     * @param map
     * @return
     */

    @POST
    Observable<Loginbean> getlogin(@Url String url,@QueryMap HashMap<String,String> map);


    /**
     * 注册
     * @param url
     * @param map
     * @return
     */

    @POST
    Observable<Registbean> getregist(@Url String url,@QueryMap HashMap<String,String> map);

    /**
     * 获取服务端购物车信息
     * @param url
     * @return
     */
    @GET
    Observable<ShopcarBean> getShopcar(@Url String url);

    /**
     * 向服务端购物车添加一个产品
     */
    @POST
    Observable<Registbean> getaddOneProduct(@Url String url,@Body RequestBody requestBody);
    /**
     * 验证服务端产品库存情况
     */
    @POST
    Observable<Registbean> getcheckOneProductInventory(@Url String url,@QueryMap HashMap<String,String> map);
    /**
     * 更新服务端购物车产品的选择
     */
    @POST
    Observable<Registbean> getupdateProductSelected(@Url String url,@Body RequestBody requestBody);
    /**
     * 全选服务端购物车产品或者全不选
     */
    @POST
    Observable<Registbean> getselectAllProduct(@Url String url,@Body RequestBody requestBody);
    /**
     * 从服务端购物车删除一个产品
     */
    @POST
    Observable<Registbean> getremoveManyProduct(@Url String url,@Body RequestBody requestBody);
    /**
     * 更新服务端购物车产品的数量
     */
    @POST
    Observable<Registbean> getupdateProductNum(@Url String url,@Body RequestBody requestBody);
    /**
     * 检查服务端多个产品是否库存充足
     */
    @POST
    Observable<Checkinven> getcheckInventory(@Url String url, @Body RequestBody requestBody);
    /**
     * 更新用户绑定的电话
     */
    @POST
    Observable<Registbean> getupdatePhone(@Url String url, @QueryMap HashMap<String,String> map);
    /**
     * 更新用户收货地址
     */
    @POST
    Observable<Registbean> getupdateAddress(@Url String url, @QueryMap HashMap<String,String> map);
    /**
     * 更新用户收货地址
     */
    @POST
    Observable<OrderBean> getOrderInfo(@Url String url, @Body RequestBody requestBody);
    /**
     * 请求服务端，是否支付成功
     */
    @POST
    Observable<Registbean> getconfirmServerPayResult(@Url String url, @Body RequestBody requestBody);
    /**
     * 查找待支付的订单
     */
    @GET
    Observable<OrderPaybean> getfindForPay(@Url String url);

}
