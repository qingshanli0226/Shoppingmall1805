package com.shopmall.bawei.shopmall1805.order.contract;

import com.shopmall.bawei.shopmall1805.framework.BasePresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;
import com.shopmall.bawei.shopmall1805.net.entity.FindForBean;

import java.util.List;

public class ForPayContract {

    public interface ForPayView extends IView{
        void onFindFor(List<FindForBean.ResultBean> list);
    }

    public static abstract class ForPayPresenter extends BasePresenter<ForPayView>{
        public abstract void getFindFor();
    }
}
