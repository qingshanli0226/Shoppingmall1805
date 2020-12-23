package com.shopmall.bawei.order.shoporder;

import com.example.net.RetrofitCreater;
import com.example.net.bean.ConfirmServerPayResultBean;
import com.shopmall.bawei.pay.PayResult;
import com.shoppmall.common.adapter.error.ExceptionUtil;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OrderPresenterImpl extends OrderContract.OrderPresenter {

    @Override
    public void confirmServerPayResult(String outTradeNo, PayResult result, Boolean clientPayResult) {
        JSONObject object = new JSONObject();
        try {
            object.put("outTradeNo",outTradeNo);
            object.put("result",result.getResult());
            object.put("clientPayResult",clientPayResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        RetrofitCreater.getiNetWorkApi().confirmServerPayResult(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConfirmServerPayResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConfirmServerPayResultBean bean) {
                        iview.onConfirmServerPayResultOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.hideLoading(false, ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
