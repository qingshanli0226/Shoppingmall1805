package com.shopmall.bawei.shopcar;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.RemoveManyProductBean;
import com.example.net.bean.SelectAllBean;
import com.example.net.bean.ShopCarBean;
import com.example.net.bean.UpdateProductNumBean;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.util.List;

public interface ShopCarContract {
    interface ShopCarView extends IView{
        void onRemoveManyOk(RemoveManyProductBean bean);
        void onRemoveManyError(ErrorBean bean);
        void onSelectAllOk(SelectAllBean bean);
        void onSelectAllError(ErrorBean bean);
        void onProductNumChangeOk(UpdateProductNumBean bean);
        void onProductNumChangeError(ErrorBean bean);

    }
    public abstract class ShopCarPresenter extends BasePresenter<ShopCarView>{
        public abstract void removeManyProduct(List<ShopCarBean.ResultBean> beans);
        public abstract void selectAllProduct(boolean selected);
        public abstract void productNumChange(String id, int num, String name, String url, String price);
    }
}
