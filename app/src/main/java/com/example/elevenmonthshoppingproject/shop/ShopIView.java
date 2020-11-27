package com.example.elevenmonthshoppingproject.shop;

import com.example.elevenmonthshoppingproject.base.BasePresenter;
import com.example.elevenmonthshoppingproject.base.IView;
import com.example.net.bean.LoginBean;
import com.example.net.bean.Recommonde;
import com.example.net.bean.RegisterBean;

public class ShopIView {
    public interface IShopView extends IView{
        void onShopview(Recommonde recommonde);
        void onregister(RegisterBean registerBean);
        void onlogin(LoginBean loginBean);
    }
    public static abstract class IShopPresenter extends BasePresenter<IShopView>{
            public abstract void getshop();
            public abstract void onregister(String username,String password);
            public abstract void onlogin(String username,String password);
    }
}
