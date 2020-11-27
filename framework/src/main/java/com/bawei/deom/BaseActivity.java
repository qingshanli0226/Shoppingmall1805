package com.bawei.deom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

;import com.bawei.deom.countroller.UserIMPL;
import com.bawei.deom.view.LoadingPage;


public abstract class BaseActivity<Prine extends IPrine,PView extends IView> extends AppCompatActivity {

     public Prine prine;
      private LoadingPage loadingPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intView();
        inPresone();
        inData();
        setContentView(getLayoutId());
       prine.attach((PView)this);
    }

    protected abstract int getLayoutId();

    protected abstract void inPresone();


    protected abstract void inData();

    protected abstract void intView();

    protected abstract int getlayouview();

}
