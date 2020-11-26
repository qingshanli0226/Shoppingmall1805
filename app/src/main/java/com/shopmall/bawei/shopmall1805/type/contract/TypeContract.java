package com.shopmall.bawei.shopmall1805.type.contract;

import com.bw.net.bean.AccessoryBean;
import com.bw.net.bean.BagBean;
import com.bw.net.bean.DigitBean;
import com.bw.net.bean.DressBean;
import com.bw.net.bean.GameBean;
import com.bw.net.bean.HomeProductsBean;
import com.bw.net.bean.JacketBean;
import com.bw.net.bean.OverCoatBean;
import com.bw.net.bean.PantsBean;
import com.bw.net.bean.SkirtBean;
import com.bw.net.bean.StationeryBean;
import com.shopmall.bawei.shopmall1805.base.BasePresenter;
import com.shopmall.bawei.shopmall1805.base.IView;

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
