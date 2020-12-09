package view.contract;

import java.util.List;

import framework.Mvp.Iview;
import mode.InventoryBean;
import mode.OrderInfoBean;
import mode.ShopcarBean;

public
interface ShopcarContractc {
    interface  IShopcarView extends Iview{
        void onProductNumChange(String result, int position, String newNum);
        void onProductSelected(String result, int position);
        void onAllSelected(String result);
        void onDeleteProducts(String result);
        void onInventory(List<InventoryBean> inventoryBean);
        void onOrderInfo(OrderInfoBean orderInfoBean);
    }

    abstract class  ShopcarPresenter extends BasePresenter<IShopcarView>{
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);
        public abstract void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, int position);
        public abstract void selectAllProduct(boolean isAllSelect);
        public abstract void deleteProducts(List<ShopcarBean> products);
        public abstract void checkInventory(List<ShopcarBean> products);
        public abstract void getOrderInfo(List<ShopcarBean> products);
    }
}
