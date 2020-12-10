package com.bawei.deom.addPage;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;

public class AddCountroller {
    public interface AddView extends IView {
         void onCheckOneProductInventoryView(String productNum);
         void onAddShoppingView(String addResult);
        void onProductNumChange(String result);
    }
    public abstract static class ADDShow extends BaseAroute<AddView> {
        public abstract void CheckOneProductNum(String productId, String productNum);
        public abstract void AddOneProduct(String productId, String productNum, String productName, String url, String productPrice);
        public abstract void UpdateProductNum(String productId, String productNum, String productName, String url, String productPrice);

    }
}
