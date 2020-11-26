package com.shopmall.bawei.shopmall1805.contract;

import com.example.framework.BaseIPresenter;
import com.example.framework.IVIew;
import com.example.net.JavaBean;

import java.util.List;

public interface ClassfiyContract {

    interface ClassfiyView extends IVIew {
        void getViewData1(JavaBean clotheslist);
    }

    abstract class ClassfiyPresenter extends BaseIPresenter<ClassfiyContract.ClassfiyView> {
        public abstract void getData1();
    }

}
