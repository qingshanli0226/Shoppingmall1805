package com.shopmall.bawei.shopmall1805.mvp.contract;


import java.util.List;

import baseurl.HomeBean;
import baseurl.SkirstBean;
import io.reactivex.Observer;
import mvp.moudel.IMoudel;
import mvp.view.IView;

public interface ClassificationContract {

    interface getmodel extends IMoudel{
        void sethome(String url,Observer<SkirstBean> observer);
    }

    interface geteview extends IView{
            String url();
        void getdata(List<SkirstBean.ResultBean> list);
    }

}
