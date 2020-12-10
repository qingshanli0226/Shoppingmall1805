package com.shopmall.bawei.framework.base;

import com.shopmall.bawei.framework.mvp.Presenter;

public abstract class BaseUrl<P extends Presenter> {
      protected P mPresenter;

     protected abstract void createPresenter();

     public BaseUrl(){
          createPresenter();
     }
}
