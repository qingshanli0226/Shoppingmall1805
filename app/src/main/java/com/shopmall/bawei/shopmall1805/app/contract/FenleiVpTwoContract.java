package com.shopmall.bawei.shopmall1805.app.contract;

import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.FenLeiVpTwoEntity;
import com.shopmall.bawei.shopmall1805.framework.IModel;
import com.shopmall.bawei.shopmall1805.framework.IPresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;

import java.util.List;

import io.reactivex.Observer;

public interface FenleiVpTwoContract {
    interface FenLeiTwoModel extends IModel {
        void getFenData( Observer<FenLeiVpTwoEntity> observer);
    }
    abstract class FenleiTwoPresenter extends IPresenter<FenLeiTwoModel,FenleiTwoView> {
        public FenleiTwoPresenter(FenleiTwoView fenleiTwoView) {
            super(fenleiTwoView);
        }
        public abstract void setFenTwoData();
    }
    interface FenleiTwoView extends IView {
        void setSkirtData(List<FenLeiVpTwoEntity.ResultBean> resultBeanList);
    }
}
