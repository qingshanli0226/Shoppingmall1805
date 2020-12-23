package com.bawei.deom.selectedordelete;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;

import java.util.List;

import bean.FindForSendBean;
import bean.GetOrderInfo;
import bean.Shoppingcartproducts;

public class FindForContract {
    public interface FindForView extends IView{
        void onFindForView(List<FindForSendBean.ResultBean> list);
    }
    public static abstract  class   FindForShow extends BaseAroute<FindForView> {
        public abstract void findforshow();
    }
}
