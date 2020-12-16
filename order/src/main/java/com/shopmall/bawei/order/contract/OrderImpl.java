package com.shopmall.bawei.order.contract;


import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.OrderInfoBean;
import com.shopmall.bawei.net.mode.ShopCarBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderImpl extends OrderContract.IOrderPresenter {
    @Override
    public void getOrderInfo(List<ShopCarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for (ShopCarBean shopCarBean:products){
            JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("productName",shopCarBean.getProductName());
                jsonObject.put("productId",shopCarBean.getProductId());
                jsonArray.put(jsonObject);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

        JSONObject object = new JSONObject();
        try{
            object.put("subject","buy");
            object.put("totalPrice", CacheManager.getInstance().getMoneyValue());
            object.put("body",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());

        OkHttpHelper.getApi()
                .getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<OrderInfoBean>,OrderInfoBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<OrderInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderInfoBean orderInfoBean) {
                        iView.onOrderInfo(orderInfoBean);
                        iView.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.hideLoading(false, ExceptionUtil.getErrorBean(e));
                        iView.hideLoading(false,null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
