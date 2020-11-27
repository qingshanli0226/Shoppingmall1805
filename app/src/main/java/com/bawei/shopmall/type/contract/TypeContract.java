package com.bawei.shopmall.type.contract;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.net.MyNetApi;
import com.bawei.net.mode.TypeBean;

public class TypeContract {
    public interface ITypeView extends IView {
        void onType(TypeBean typeBean);
    }
    public static abstract class ITypePresenter extends BasePresenter<ITypeView> {
        public abstract MyNetApi type();
    }
}
