package com.shopmall.bawei.shopmall1805.home.contract;

import android.view.View;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.RecommendedBeen;
import com.shopmall.bawei.net.typebean.BugBean;

import java.util.List;

public
class TypeContract {
    public interface ITypeView extends IView {
        void UserView(List<BugBean.ResultBean.ChildBean> list);
        void UserRe(List<BugBean.ResultBean.HotProductListBean> list);
    }
    public static abstract class TypePresenter extends BasePresenter<TypeContract.ITypeView> {
        public abstract void UserShow(String url);
        public abstract void UserReShow(String url);
    }
}
