package com.shopmall.bawei.shopmall1805.app.contract;

import com.shopmall.bawei.shopmall1805.common.ClothesBean;

import com.shopmall.bawei.shopmall1805.framework.BasePresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;

import java.util.List;

public class ClassifyLeftContract {
    public interface FenleiView extends IView {
        void onFenleiData(List<ClothesBean.ResultBean> resultBeanList);
    }
    public static abstract class FenleiPresenter extends BasePresenter<FenleiView> {
        public abstract void getFenLeiView(String url);
    }
}
