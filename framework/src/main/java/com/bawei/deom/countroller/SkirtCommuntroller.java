package com.bawei.deom.countroller;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;
import com.bawei.deom.view.LoadingPage;

import java.util.List;


import bean.typebean.BugBean;


public class SkirtCommuntroller {
    public interface UsView extends IView {
        void onUserView(List<BugBean.ResultBean> list);
    }
    public abstract static class UsShow extends BaseAroute<UsView> {
        public abstract void UserShow(String url, LoadingPage loadingPage);

    }
}
