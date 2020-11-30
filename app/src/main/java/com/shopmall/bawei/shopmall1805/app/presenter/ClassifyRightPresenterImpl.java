package com.shopmall.bawei.shopmall1805.app.presenter;

import com.shopmall.bawei.shopmall1805.app.contract.ClassifyRightContract;

import com.shopmall.bawei.shopmall1805.common.ClassifyTagEntity;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;


import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
public class ClassifyRightPresenterImpl extends ClassifyRightContract.FenleiTwoPresenter {

    @Override
    public void getFenLeiRightView() {
        RetrofitUtils.getiNetPresetenterWork()
                .tag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ClassifyTagEntity>(){
                    @Override
                    public void onNext(ClassifyTagEntity classifyTagEntity) {
                        if(classifyTagEntity!=null){
                            List<ClassifyTagEntity.ResultBean> result = classifyTagEntity.getResult();
                            iHttpView.onFenleiRightData(result);
                 }
         }});
    }
}