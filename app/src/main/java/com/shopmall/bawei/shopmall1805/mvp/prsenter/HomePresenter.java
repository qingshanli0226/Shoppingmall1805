package com.shopmall.bawei.shopmall1805.mvp.prsenter;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.mvp.contract.HomeContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import baseurl.HomeBean;
import mvp.presenter.BasePresenter;

public class HomePresenter extends BasePresenter<HomeContract.getmodel, HomeContract.geteview> {
    public HomePresenter(HomeContract.getmodel imoudel, HomeContract.geteview iview) {
        super(imoudel, iview);
    }
    public void inithomedata(){
        Log.i("TAG", "inithomedata: ");
      imoudel.sethome(new Observer<HomeBean>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(HomeBean homeBean) {
              Log.i("TAG", "onNext: "+homeBean);
        iview.getdata(homeBean);

          }

              @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
      });

    }
}
