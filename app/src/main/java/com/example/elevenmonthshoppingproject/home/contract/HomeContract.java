package com.example.elevenmonthshoppingproject.home.contract;

import com.example.framwork.BasePresenter;
import com.example.framwork.IView;
import com.example.net.bean.HomeBean;

public class HomeContract {
    public interface HomeIView extends IView{
        void onHomeData(HomeBean homeBean);
    }
    public static abstract class HomePresenter extends BasePresenter<HomeIView>{
        public abstract void getHomeData();
    }
}
