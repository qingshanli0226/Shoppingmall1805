package com.bawei.deom.countroller;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;

import java.util.List;

import bean.BaseBean;
import bean.ClothesBean;
import bean.HomeBean;
import bean.TAGBean;
import bean.typebean.BugBean;
import bean.typebean.DigitBean;
import bean.typebean.DressupBean;
import bean.typebean.GameBean;
import bean.typebean.HomproductsBean;
import bean.typebean.JackBean;
import bean.typebean.OverCoat;
import bean.typebean.Pants;
import bean.typebean.SkirtBean;
import bean.typebean.StationeryBean;

public class SkirtCommuntroller {
    public interface UsView extends IView {
        void UserView(List<BugBean.ResultBean> list);
    }
    public abstract static class UsShow extends BaseAroute<UsView> {
        public abstract void UserShow(String url);

    }
}
