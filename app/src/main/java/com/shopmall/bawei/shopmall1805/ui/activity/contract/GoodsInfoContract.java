package com.shopmall.bawei.shopmall1805.ui.activity.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;

public class GoodsInfoContract {
    public interface IGoodsInfoView extends IView {
        void onCheckOneproduct(String productNum);
        void onAddOneproduct(String addresult);
        void onProductchanged(String result);
    }
    public abstract static class IGoodPresenter extends BasePresenter<IGoodsInfoView> {
        public abstract void CheckOneproduct(String productId,String productNum);
        public abstract void AddOneproduct(String productId,String productNum,String productName,String url,String productPrice);
        public abstract void Productchanged(String productId,String productNum,String productName,String url,String productPrice);
    }
}
