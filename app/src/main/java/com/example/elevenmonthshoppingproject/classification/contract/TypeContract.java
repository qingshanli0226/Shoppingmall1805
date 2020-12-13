package com.example.elevenmonthshoppingproject.classification.contract;

import com.example.framwork.BasePresenter;
import com.example.framwork.IView;
import com.example.net.bean.TypeBean;

import java.util.List;

public class TypeContract {

    public interface  TypeIView extends IView{
        void onType(List<TypeBean.ResultBean> resultBeans);
    }
    public static abstract class TypeIPresenter extends BasePresenter<TypeIView>{
        public abstract void getType();
    }
}
