package com.shopmall.bawei.shopmall1805.app.contract;

import com.shopmall.bawei.shopmall1805.net.entity.ClassifyTagEntity;
import com.shopmall.bawei.shopmall1805.framework.BasePresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;

import java.util.List;

public class ClassifyRightContract {

    public interface FenleiTwoView extends IView {
        void onFenleiRightData(List<ClassifyTagEntity.ResultBean> resultBeanList);
    }
    public static abstract class FenleiTwoPresenter extends BasePresenter<FenleiTwoView> {
        public abstract void getFenLeiRightView();
    }
}
