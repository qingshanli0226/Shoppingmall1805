package com.shopmall.bawei.shopmall1805.shopcar.contract;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.framework.BasePresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;
import com.shopmall.bawei.shopmall1805.net.entity.OrderInfoBean;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;

import java.util.List;

public class ShopcarContract {
    public interface IShopcarView extends IView {
        void onUpdateSelect(String result,int position);
        void onNumberChanger(String result,int position,String newNumber);
        void onAllSelect(String result);
        void onDeleteProducts(String result);
        void onOrderInfoBean(OrderInfoBean orderInfoBean);
    }
    public static abstract class ShopcarPresenter extends BasePresenter<IShopcarView> {
        public abstract void upDateSelect(String id,boolean select,String name,String url,String price,int position);
        public abstract void upNumberChanger(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);
        public abstract void upSelectAll(boolean isSelect);
        public abstract void deleteProducts(List<ShopcarBean> products);
        public abstract void getOrderInfo(List<ShopcarBean> shopcarBeanList);
    }
}
