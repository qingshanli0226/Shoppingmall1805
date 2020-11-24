package com.shopmall.bawei.shopmall1805.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.net.bean.BaseBean;
import com.example.net.bean.ClothesBean;
import com.example.net.bean.HomeBean;


public class PrimereContract {
    public interface SkerakView extends IView {
        void onskerk(HomeBean homeBeanList);
    }
    public abstract static  class SlerakPresenter extends BasePresenter<SkerakView>{
        public abstract void getskerak();
    }
}
