package com.shopmall.bawei.shopmall1805.mvp.contract;


import io.reactivex.Observer;
import baseurl.HomeBean;
import mvp.moudel.IMoudel;
import mvp.view.IView;

public interface HomeContract {

    interface getmodel extends IMoudel{
        void sethome(Observer<HomeBean> observer);
    }

    interface geteview extends IView{
        void getdata(HomeBean list);
    }

}
