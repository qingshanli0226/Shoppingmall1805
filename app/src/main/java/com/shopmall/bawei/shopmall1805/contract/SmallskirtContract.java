package com.shopmall.bawei.shopmall1805.contract;

import com.example.framework.BaseIPresenter;
import com.example.framework.IVIew;
import com.example.net.ClothesBean;

import java.util.List;

public interface SmallskirtContract {

    interface SmallskirtView extends IVIew {
        void getViewData2(List<ClothesBean.ResultBean> clotheslist);

    }

    abstract class SmallskirtPresenter extends BaseIPresenter<SmallskirtContract.SmallskirtView> {
        public abstract void getData2();
        public abstract void getData1();
        public abstract void getData3();
        public abstract void getData4();
        public abstract void getData5();
        public abstract void getData6();
        public abstract void getData7();
        public abstract void getData8();
        public abstract void getData9();
        public abstract void getData10();
        public abstract void getData11();
        public abstract void getData12();

    }

}
