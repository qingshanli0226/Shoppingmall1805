package com.example.detail.detailpage.detail;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.AddProductBean;
import com.example.net.bean.UpdateProductNumBean;

public interface DetailContract {
    interface IDetailView extends IView{
        void onAddOk(AddProductBean bean);
        void onAddError(String msg);
        void onUpDataOk(UpdateProductNumBean bean);
        void onUpDataError(String msg);
    }
    public abstract class DetailPresenter extends BasePresenter<IDetailView>{
        public abstract void addProduct(String id,int num,String name,String url,String price);
        public abstract void UpData(String id,int num,String name,String url,String price);
    }
}
