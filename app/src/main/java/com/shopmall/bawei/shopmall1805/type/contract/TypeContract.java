package com.shopmall.bawei.shopmall1805.type.contract;


import com.bw.common.BasePresenter;
import com.bw.common.IView;
import com.bw.net.bean.SkirtBean;

public abstract class TypeContract {

    public interface TypeView extends IView {
       void onGetSkirtOk(SkirtBean skirtBean);

    }

    public static abstract class ITypePresenter extends BasePresenter<TypeView> {
        public abstract void getSkirt();
        public abstract void getJacket();
        public abstract void getPints();
        public abstract void getOvercoad();
        public abstract void getAccessory();
        public abstract void getBag();
        public abstract void getDress();
        public abstract void getHomeproduct();
        public abstract void getStation();
        public abstract void getDigit();
        public abstract void getGame();
    }

}
