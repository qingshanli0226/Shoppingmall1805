package com.shopmall.bawei.shopmall1805.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.Biaobean;

import java.util.List;

public class BiaoContract {
    public interface biaoView extends IView {
        void onbiao(List<Biaobean.ResultBean> beans);
    }
    public abstract static class biaoPresenter extends BasePresenter<biaoView> {
        public abstract void getbiao();
    }
}
