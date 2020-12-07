package com.shopmall.bawei.shopmall1805.detail;

import com.bw.framework.BasePresenter;
import com.bw.framework.IView;

public class DetailContract {

    public interface DetailView extends IView{
        void onAddProductOk(String addResult);
    }

    public abstract static class DetailPresenter extends BasePresenter<DetailView>{
        public abstract void addProduct(String productId, String productNum, String productName, String url, String productPrice);
    }

}
