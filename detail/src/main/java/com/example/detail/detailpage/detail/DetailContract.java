package com.example.detail.detailpage.detail;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.AddProductBean;

public interface DetailContract {
    interface IDetailView extends IView{
        void onOk(AddProductBean bean);
        void onError(String msg);

    }
    public abstract class DetailPresenter extends BasePresenter<IDetailView>{
        public abstract void addProduct(String id,int num,String name,String url,String price);
    }
}
