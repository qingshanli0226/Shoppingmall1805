package com.shopmall.bawei.shopmall1805.Classification.contract;


import com.example.common2.SkirstBean;
import com.shopmall.bawei.shopmall1805.home.comtract.HomeContract;

import java.util.List;


import io.reactivex.Observer;
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
