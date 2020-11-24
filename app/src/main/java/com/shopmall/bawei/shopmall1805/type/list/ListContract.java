package com.shopmall.bawei.shopmall1805.type.list;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.GoodsBean;

public interface ListContract {
    interface ListIView extends IView {
        void onListOK(GoodsBean bean);
        void onError(String msg);
    }
    public static abstract class ListPresenter extends BasePresenter<ListIView>{
        public abstract void showGoods(String url);
    }
}
