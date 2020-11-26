package com.shopmall.bawei.shopmall1805.mvp.contract;

import java.util.List;

import baseurl.SkirstBean;
import baseurl.TagBean;
import io.reactivex.Observer;
import mvp.moudel.IMoudel;
import mvp.view.IView;

public interface LabelContract {
    interface getmodel extends IMoudel {
        void sethome( Observer<TagBean> observer);
    }

    interface geteview extends IView {

        void getdata(List<TagBean.ResultBean> list);
    }
}
