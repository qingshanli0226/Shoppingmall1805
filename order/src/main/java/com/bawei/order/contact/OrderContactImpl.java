package com.bawei.order.contact;

import com.bawei.common.view.ExceptionUtil;
import com.bawei.framework.CacheManager;
import com.bawei.net.NetFunction;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.ShopmallObserver;
import com.bawei.net.mode.BaseBean;
import com.bawei.net.mode.OrderInfoBean;
import com.bawei.net.mode.ShopcarBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderContactImpl extends OrderContact.OrderPresenter {

    @Override
    public void getOrderInfo(List<ShopcarBean> list) {
        JSONArray jsonArray = new JSONArray();
        for (ShopcarBean shopcarBean : list) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productName", shopcarBean.getProductName());
                jsonObject.put("productId", shopcarBean.getProductId());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject object = new JSONObject();
        try {
            object.put("subject", "buy");
            object.put("totalPrice", CacheManager.getInstance().getMoneyValue());
            object.put("body", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());

        RetrofitCreate.getApi().getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<OrderInfoBean>, OrderInfoBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new ShopmallObserver<OrderInfoBean>() {
                    @Override
                    public void onNext(OrderInfoBean orderInfoBean) {
                        iView.onOrderInfo(orderInfoBean);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iView.hideLoading(false, ExceptionUtil.getErrorBean(errorCode, errorMessage));
                    }
                });
    }
}
