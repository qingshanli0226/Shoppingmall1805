package com.shopmall.bawei.shopmall1805.app.contract;

import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.framework.IModel;
import com.shopmall.bawei.shopmall1805.framework.IPresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;

import java.util.List;

import io.reactivex.Observer;

public interface ClassifyLeftContract {
    interface FenLeiModel extends IModel {
        void getFenData(String url,Observer<ClothesBean> observer);
    }
    abstract class FenleiPresenter extends IPresenter<FenLeiModel,FenleiView> {
        public FenleiPresenter(FenleiView fenleiView) {
            super(fenleiView);
        }
       public abstract void getFenSkirt();
    }
    interface FenleiView extends IView {
        void setSkirtData(List<ClothesBean.ResultBean> resultBeanList);
        String setUrl();
    }
}
