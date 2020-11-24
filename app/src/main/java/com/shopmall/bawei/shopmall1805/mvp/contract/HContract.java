package com.shopmall.bawei.shopmall1805.mvp.contract;


import java.util.List;

import io.reactivex.Observer;
import com.shopmall.bawei.shopmall1805.bean.HomeBean;
import mvp.moudel.IMoudel;
import mvp.view.IView;

public interface HContract {

    interface getmodel extends IMoudel{
        void sethome(Observer<HomeBean> observer);
    }

    interface geteview extends IView{
        void getdata(HomeBean list);
    }

}
