package com.shopmall.bawei.shopmall1805.activity;

import com.bw.framework.BasePresenter;
import com.bw.framework.IView;

public class DetailContract {

    public interface DetailView extends IView{
        void onOk();
    }

    public abstract static class DetailPresenter extends BasePresenter<IView>{
        public abstract void addProduct();
    }

}
