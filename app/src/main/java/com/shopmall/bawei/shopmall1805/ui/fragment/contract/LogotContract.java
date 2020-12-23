package com.shopmall.bawei.shopmall1805.ui.fragment.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.FindSendBean;
import com.example.net.bean.FindPayBean;

import java.util.List;

public class LogotContract {
    public interface ILogotView extends IView {
        void onLogotSucess(String result);

    }
    public abstract static class ILogotPresenter extends BasePresenter<ILogotView>{
        public abstract void logotUser();

    }
}
