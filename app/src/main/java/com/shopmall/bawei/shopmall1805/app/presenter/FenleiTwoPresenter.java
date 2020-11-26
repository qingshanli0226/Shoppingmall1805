package com.shopmall.bawei.shopmall1805.app.presenter;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.app.contract.FenleiContract;
import com.shopmall.bawei.shopmall1805.app.contract.FenleiVpTwoContract;
import com.shopmall.bawei.shopmall1805.app.model.FenLeiModel;
import com.shopmall.bawei.shopmall1805.app.model.FenLeiTwoModel;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.FenLeiVpTwoEntity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FenleiTwoPresenter extends FenleiVpTwoContract.FenleiTwoPresenter {

    public FenleiTwoPresenter(FenleiVpTwoContract.FenleiTwoView fenleiTwoView) {
        super(fenleiTwoView);
    }
    @Override
    public void setFenTwoData() {
        iModel.getFenData(new Observer<FenLeiVpTwoEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FenLeiVpTwoEntity fenLeiVpTwoEntity) {
                List<FenLeiVpTwoEntity.ResultBean> result = fenLeiVpTwoEntity.getResult();
                if(fenLeiVpTwoEntity!=null){
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
        iModel=new FenLeiTwoModel();
    }
}
