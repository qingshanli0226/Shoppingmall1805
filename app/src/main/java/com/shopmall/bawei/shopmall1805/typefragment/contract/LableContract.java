package com.shopmall.bawei.shopmall1805.typefragment.contract;

import com.example.net.bean.Biaobean;
import com.shopmall.bawei.framework.example.framework.BasePresenter;
import com.shopmall.bawei.framework.example.framework.IView;

import java.util.List;

public class LableContract {
    public interface biaoView extends IView {
        void onbiao(List<Biaobean.ResultBean> beans);
    }
    public abstract static class biaoPresenter extends BasePresenter<biaoView> {
        public abstract void getbiao();
    }
}
