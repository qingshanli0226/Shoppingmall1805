package com.bawei.deom.countroller;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;
import com.bawei.deom.view.LoadingPage;

import java.util.List;

import bean.BaseBean;
import bean.ClothesBean;
import bean.HomeBean;
import bean.TAGBean;
import bean.typebean.SkirtBean;

public class UserCountroller {
//    RecommendInfoBean
    public interface UserView extends IView {

    void onskerk(HomeBean homeBeanList);
     void onTagBiew(List<TAGBean.ResultBean> resultBeanList);

    }
    public abstract static class UserShow extends BaseAroute<UserView>{

        public abstract void getskerak(LoadingPage loadingPage);
        public  abstract  void  TagShow(LoadingPage loadingPage);

    }
}
