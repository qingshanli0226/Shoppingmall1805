package com.shopmall.bawei.shopmall1805.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.BaseBean;
import com.example.net.bean.ClothesBean;

import java.util.List;

public class ClothesContract {
    public interface SkertView extends IView {
        void onjscket(List<ClothesBean.ResultBean> jscketbean);

    }
    public abstract static class SkertPresenter extends BasePresenter<SkertView> {
        public abstract void getskert();
        public abstract void getjecket();
        public abstract void ononpants();
        public abstract void onovercoat();
        public abstract void onaccessory();
        public abstract void onbag();
        public abstract void ondress();
        public abstract void onproduct();
        public abstract void onstation();
        public abstract void ondigit();
        public abstract void ongame();



    }
}
