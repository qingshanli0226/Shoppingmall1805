package com.shopmall.bawei.shopcar.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.IntonVoryBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;

import java.util.List;

public class ShopCarContract {
    public interface IShopCarView extends IView {
        void onProductChangeNum(String result,int position,String newNum);//更新购物车商品数量
        void onProductSelected(String result,int position);               //更改服务端购物车的选择状态
        void onProductAllSelected(String result);                         //全选购物车的商品或者全部不选择
        void onDeleteProduct(String result);                              //删除服务端购物车的商品

    }
    public abstract static class IShopCarPresenter extends BasePresenter<IShopCarView>{
        public abstract void updateProductChangeNum(String productId,String productNum,String productName,String url,String productPrice,int position,String newNum);
        public abstract void updateSelected(String productId,boolean productSelected,String productName,String productUrl,String ProductPrice,int position);
        public abstract void selectedAllProduct(boolean isSelected);
        public abstract void deleteProduct(List<ShopcarBean> shopcarBeanList);
    }
}
