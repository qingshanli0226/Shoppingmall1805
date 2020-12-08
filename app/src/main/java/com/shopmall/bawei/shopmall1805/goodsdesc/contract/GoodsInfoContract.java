package com.shopmall.bawei.shopmall1805.goodsdesc.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;

public class GoodsInfoContract {
    public interface IGoodsInfoView extends IView{
        void onCheckOneProducts(String productNum);
        void onAddProduct(String addResult);
        void onProductNumChange(String result);
    }
    public static abstract class IGoodsInfoPresenter extends BasePresenter<IGoodsInfoView>{
        public abstract void checkOneProductNum(String productId,String productNum);
        public abstract void addOneProduct(String productId,String productNum,String productName,String url,String productPrice);
        public abstract void updateProductNum(String productId,String productNum,String productName,String url,String productPrice);
    }
}
