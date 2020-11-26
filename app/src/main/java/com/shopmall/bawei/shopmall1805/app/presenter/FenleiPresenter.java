package com.shopmall.bawei.shopmall1805.app.presenter;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.app.contract.FenleiContract;
import com.shopmall.bawei.shopmall1805.app.model.FenLeiModel;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FenleiPresenter extends FenleiContract.FenleiPresenter {

    public FenleiPresenter(FenleiContract.FenleiView fenleiView) {
        super(fenleiView);
    }

    @Override
    public void getFenSkirt() {
        iModel.getFenData(mView.get().setUrl(), new Observer<ClothesBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ClothesBean clothesBean) {
                List<ClothesBean.ResultBean> result = clothesBean.getResult();
                Log.i("TAG", "onNext: "+result);
                if(result!=null){
                    Log.i("TAG", "onNext: "+result);
                    mView.get().setSkirtData(result);
                }
            }
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    @Override
    protected void createModel() {
        iModel=new FenLeiModel();
    }
}
