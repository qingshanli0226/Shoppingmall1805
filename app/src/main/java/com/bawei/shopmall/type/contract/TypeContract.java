package com.bawei.shopmall.type.contract;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.net.MyNetApi;
import com.bawei.net.mode.TypeBean;

public class TypeContract {
    public interface ITypeView extends IView {
        void onType(TypeBean typeBean);
//        void onSkirt(TypeBean typeBean);
//        void onJacket(TypeBean typeBean);
//        void onPants(TypeBean typeBean);
//        void onOvercoat(TypeBean typeBean);
//        void onAccessory(TypeBean typeBean);
//        void onBag(TypeBean typeBean);
//        void onDress(TypeBean typeBean);
//        void onProduct(TypeBean typeBean);
//        void onStationery(TypeBean typeBean);
//        void onDigit(TypeBean typeBean);
//        void onGame(TypeBean typeBean);
    }
    public static abstract class ITypePresenter extends BasePresenter<ITypeView> {
        public abstract MyNetApi type();
    }
}
