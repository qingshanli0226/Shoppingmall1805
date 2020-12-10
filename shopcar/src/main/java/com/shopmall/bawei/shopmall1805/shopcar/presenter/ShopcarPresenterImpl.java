package com.shopmall.bawei.shopmall1805.shopcar.presenter;


import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;
import com.shopmall.bawei.shopmall1805.net.entity.BaseBean;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;
import com.shopmall.bawei.shopmall1805.shopcar.contract.ShopcarContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShopcarPresenterImpl extends ShopcarContract.ShopcarPresenter {
    @Override
    public void getOrderInfo() {
        RetrofitUtils.getiNetPresetenterWork().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<List<ShopcarBean>>>(){
                    @Override
                    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {
                        super.onNext(listBaseBean);
                        List<ShopcarBean> result = listBaseBean.getResult();
                        iHttpView.onOrderInfo(result);
                    }
                });
    }
}
