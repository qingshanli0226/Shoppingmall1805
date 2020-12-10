package com.shopmall.bawei.shopmall1805.classification.contract;


import com.example.common2.SkirstBean;


import mvp.view.BasePresenter;
import mvp.view.IView;

public class ClassificationContract {

        public interface IClassification extends IView{
            void onClassification(SkirstBean skirstBean);
        }

        public static abstract class ClassificationPresenter extends BasePresenter<IClassification>{
            public abstract void getIClassificationData(String url);
        }


}
