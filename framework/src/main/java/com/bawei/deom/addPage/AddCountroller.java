package com.bawei.deom.addPage;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;

import java.util.List;

import bean.AutoLoginBeen;
import bean.BaseBean;

public class AddCountroller {
    public interface AddView extends IView {
         void CheckOneProductInventoryView(String productNum);
         void AddShoppingView(String addResult);
        void UpdateProductNumView(String result);
    }
    public abstract static class ADDShow extends BaseAroute<AddView> {
        public abstract void checkOneProductNum(String productId, String productNum);
        public abstract void addOneProduct(String productId, String productNum, String productName, String url, String productPrice);
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice);

    }
}
