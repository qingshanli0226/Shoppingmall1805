package com.shopmall.bawei.shopmall1805.app.presenter;

import com.shopmall.bawei.shopmall1805.app.contract.ClassifyRightContract;
import com.shopmall.bawei.shopmall1805.app.model.ClassifyRightModel;
import com.shopmall.bawei.shopmall1805.common.FenLeiVpTwoEntity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ClassifyRightPresenter extends ClassifyRightContract.FenleiTwoPresenter {

    public ClassifyRightPresenter(ClassifyRightContract.FenleiTwoView fenleiTwoView) {
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
        iModel=new ClassifyRightModel();
    }
}
