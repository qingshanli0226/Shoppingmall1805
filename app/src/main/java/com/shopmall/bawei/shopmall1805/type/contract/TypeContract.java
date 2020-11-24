package com.shopmall.bawei.shopmall1805.type.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.MyNetApi;
import com.shopmall.bawei.net.TypeBean;

public class TypeContract {
    public interface ITypeView extends IView {
        void onType(TypeBean typeBean);
    }
    public static abstract class ITypePresenter extends BasePresenter<ITypeView>{
        public abstract MyNetApi type();
    }
}
