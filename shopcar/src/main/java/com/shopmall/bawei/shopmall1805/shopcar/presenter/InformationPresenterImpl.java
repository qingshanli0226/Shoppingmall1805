package com.shopmall.bawei.shopmall1805.shopcar.presenter;


import android.util.Log;

import com.shopmall.bawei.shopmall1805.common.ErrorBean;
import com.shopmall.bawei.shopmall1805.common.ExceptionUtils;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;
import com.shopmall.bawei.shopmall1805.net.entity.BaseBean;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;
import com.shopmall.bawei.shopmall1805.shopcar.contract.InformationContract;
import com.shopmall.bawei.shopmall1805.shopcar.contract.ShopcarContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class InformationPresenterImpl extends InformationContract.InformationPresenter {

    @Override
    public void UpDataPhone(String phone) {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",phone);
        RetrofitUtils.getiNetPresetenterWork()
                .upDataPhoneIn(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<String>>() {
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        Log.i("TAG", "onNext: "+stringBaseBean);
                        iHttpView.onUpDataPhone(stringBaseBean.getResult());
                    }
                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {
                  }
          });
    }

    @Override
    public void UpDataAddress(String address) {
        HashMap<String, String> map = new HashMap<>();
        map.put("address",address);
        RetrofitUtils.getiNetPresetenterWork()
                .upDataAddressIn(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<String>>() {
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        Log.i("TAG", "onNext: "+stringBaseBean);
                        iHttpView.onAddressUpData(stringBaseBean.getResult());
                    }
                    @Override
                    public void onRequestError(String errorCold, String errorMsg) {

                    }
                });
    }
}
