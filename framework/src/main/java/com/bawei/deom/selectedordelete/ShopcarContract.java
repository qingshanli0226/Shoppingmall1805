package com.bawei.deom.selectedordelete;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;

import java.util.List;

import bean.GetOrderInfo;
import bean.InventoryBean;
import bean.Shoppingcartproducts;

public class ShopcarContract {
    public interface SelectedandDeletedCountrollerView extends IView {
          void onProductNumChange(String result, int position, String newNum);//更新服务端购物车产品的数量的接口
          void onAllSelected(String request);
          void ononProductSelected(String result,int postion);
          void onDeleteProducts(String result);
          void onInventory(List<InventoryBean> inventoryBean);
          void onOrderInfo(GetOrderInfo orderInfoBean);
    }
   public static abstract  class   SelectedandDeletedCountrollerShow extends BaseAroute<SelectedandDeletedCountrollerView>{
       public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);
       public abstract void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, int position);
       public abstract void selectAllProduct(boolean isAllSelect);
       public abstract void deleteProducts(List<Shoppingcartproducts.ResultBean> products);
       public abstract void checkInventory(List<Shoppingcartproducts.ResultBean> products);
       public abstract void getOrderInfo(List<Shoppingcartproducts.ResultBean> products);
   }
}
