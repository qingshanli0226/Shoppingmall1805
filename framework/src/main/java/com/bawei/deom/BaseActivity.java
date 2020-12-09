package com.bawei.deom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

;import com.bawei.deom.countroller.UserIMPL;
import com.bawei.deom.view.LoadingPage;
import com.bawei.deom.view.ToolBar;
import com.shopmall.bawei.framework.R;


public abstract class BaseActivity<Prine extends IPrine,PView extends IView> extends AppCompatActivity {

     public Prine prine;
     private ToolBar toolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        intView();
        inPresone();
        inData();

       prine.attach((PView)this);

    }

    protected abstract int getLayoutId();

    protected abstract void inPresone();


    protected abstract void inData();

    protected abstract void intView();



}
