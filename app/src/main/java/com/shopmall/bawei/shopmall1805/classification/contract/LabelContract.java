package com.shopmall.bawei.shopmall1805.classification.contract;

import com.example.common2.TagBean;


import mvp.view.BasePresenter;
import mvp.view.IView;

public class LabelContract {
    public interface ILabel extends IView{
        void onLabel(TagBean tagBean);
    }

    public static abstract class LabelPresenter extends BasePresenter<ILabel> {
        public abstract void getILabelData();
    }
}
